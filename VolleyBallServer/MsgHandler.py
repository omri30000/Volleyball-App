import Helper


VALID_PLAYERS_FILE_NAME = "Players.txt"

code_to_func = {100: enroll_to_training, 200: provide_available_training_list, 
                300: provide_teams_for_training, 400: provide_players_for_training}

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


        if check_if_user_known(player_name, VALID_PLAYERS_FILE_NAME):
            # todo: add player name to attending file by training ID instead of the code here in comment
            '''
            attending_players = Helper.read_file_to_dict(ATTENDING_EVENT_FILE_NAME)
            valid_players = Helper.read_file_to_dict(VALID_PLAYERS_FILE_NAME)

            attending_players[player_name] = valid_players[player_name]
            Helper.write_dict_to_file(attending_players, ATTENDING_EVENT_FILE_NAME)
            '''
            return "$101$\n"
        else:
            return "$102#invalid_player_name$\n"


def provide_available_training_list(msg):  # response to 200
    """
    the function creates msg of type 201 with the list of trainings (responses to msg type 200 which has no parameters)
    :return: message of type 201 (Example: "$201#[1234,25.2.2020,13:40,Goshen]$\n")
    """
    pass


def provide_teams_for_training(teams_msg):  # response to 300
    """
    the function will response to msg 300 and create teams msg
    :param teams_msg: msg of type 300 with training id (Example: "$300#1234$\n")
    :return: msg of type 301/302 
    """
    pass


def provide_players_for_training(players_msg):  # response to 350
    """
    the function will response to msg 300 and create players msg 351 (all signed players for specific training)
    :param teams_msg: msg of type 350 with training id (Example: "$350#1234$\n")
    :return: msg of type 351 
    """

    pass
    return "$351"



def check_if_user_known(user_name, database_file):
    """
    the function will check if a player is recognized (appears in players file)
    input: the name of the player and the file of valid players
    output: boolean if player is valid or not
    """
    valid_players = Helper.read_file_to_dict(database_file) # dict of players

    if user_name.lower() in valid_players.keys():
        return True
    else:
        return False


def delete_file(file_name):
    os.remove(file_name)