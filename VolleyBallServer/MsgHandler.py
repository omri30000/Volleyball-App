import Helper
import FireBaseHandler as fBase
import datetime

VALID_PLAYERS_FILE_NAME = "Players.txt"



def enroll_to_training(enroll_msg):  # response to 100
    """
    the function enrolls a player to a specific training
    :param enroll_msg: msg of type 100 from client (Example: "$100#1234#omri_zaiman$\n")
    :type enroll_msg: string(includes training ID and player name)
    :return: message of type 101/102
    :rtype: string 
    """

    #seperate training ID
    training_id = enroll_msg[enroll_msg.find("#") + 1:]
    training_id = training_id[0: training_id.find('#')]

    #seperate player name
    player_name = enroll_msg[enroll_msg.find("#") + 1:]
    player_name = player_name[player_name.find("#") + 1: player_name.find("$")]


    if len(player_name.split("_")) != 2:
        return "$102#invalid_player_name$\n"
    else:
        player_name = player_name[0:player_name.find("_")] + ' '  + player_name[player_name.find("_") + 1:]


        if fBase.add_player_to_training(player_name, training_id):
            return "$101$\n"
        else:#unnecessary
            return "$102#invalid_player_name$\n"

def provide_available_training_list(msg):  # response to 200
    """
    the function creates msg of type 201 with the list of trainings (responses to msg type 200 which has no parameters)
    :return: message of type 201 (Example: "$201#1234,25.2.2020,13:40,Goshen$\n")
    """
    now = datetime.datetime.now()
    values =[]
    msg = "$201"
    trainings = fBase.get_trainings_list()
    for training_key,training_details in trainings.items():
        print(training_details['date'] +' '+training_details['time'])
        if datetime.datetime.strptime(training_details['date'] +' '+training_details['time'],'%d.%m.%Y %H:%M')  > now:
            msg += '#' + training_key+','+training_details['date']+','+training_details['time']+','+training_details['place']
    msg += '$\n'
    return msg

def provide_my_nearest_training(msg):
    player = Helper.decode_name(msg.split('#')[1][:-1]) #join(' ')
    my_trainings = fBase.get_my_trainings(player)
    listt = []
    for id_val,values in my_trainings.items():
        listt.append({id_val:values})
    listt.sort(key = sort_by_date)
    for part in listt:
        #print(part)
        vals = list(part.values())[0]
        print(vals)
        if datetime.datetime.strptime(vals['date'] +' '+vals['time'],'%d.%m.%Y %H:%M') >datetime.datetime.now():
            #return "$251#" +
            msg = "$251#" + list(part.keys())[0]
            for key,val in vals.items():
                if(key != "enrolledPlayers"):
                    msg+=',' + val
            msg += '#' + get_names(vals["enrolledPlayers"])+"$\n"
            return msg
    return "$252$\n"

def sort_by_date(training):
    #print(training)
    values = list(training.values())[0]
    #print(values) 
    return datetime.datetime.strptime(values['date'] +' '+values['time'],'%d.%m.%Y %H:%M')

def provide_teams_for_training(teams_msg):  # response to 300
    """
    the function will response to msg 300 and create teams msg
    :param teams_msg: msg of type 300 with training id (Example: "$300#1234$\n")
    :return: msg of type 301/302 
    """
    id = teams_msg.split('#')[1][:-1]
    training_data = fBase.get_training_details(id)
    if training_data == {} or datetime.datetime.strptime(training_data['date'] +' '+training_data['time'],'%d.%m.%Y %H:%M')  < datetime.datetime.now():
        return "$302$\n"
    return "$301#" + get_names(training_data["team1"]) + '#'+get_names(training_data["team2"]) + '$\n'


def provide_players_for_training(players_msg):  # response to 350
    """
    the function will response to msg 350 and create players msg 351 (all signed players for specific training)
    :param teams_msg: msg of type 350 with training id (Example: "$350#1234$\n")
    :return: msg of type 351 
    """
    id = players_msg.split('#')[1][:-1]
    training_data = fBase.get_training_details(id)
    players = []
    try:
        players = get_names(training_data["enrolledPlayers"])
    except Exception as e:
        players = []

    return "$351#" + get_names(players)+'$\n'


def get_names(players):
    """
    This function gets dict of {name:level} and returns all names
    Input: dict of names and levels
    Output:list of all names in dict
    """
    """
    names =[]
    for name in players.keys():
        names += name
    return names
    """ 
    names = ""
    for name in players.keys():
        if names != "":
            names += ','
        names += Helper.encode_name(name)
    return names
#def delete_file(file_name):
   # os.remove(file_name)


code_to_func = {100: enroll_to_training, 200: provide_available_training_list,250:provide_my_nearest_training, 
                300: provide_teams_for_training, 400: provide_players_for_training}



def main():
    #print(provide_available_training_list("$200$"))
    print(code_to_func[200]("$200$"))

if __name__ == "__main__":
    main()    
#"""