from firebase import firebase

def get_valid_players():
    """
    the function will return the valid players that are known in the system (maybe depeneds on connection to email that has the database)
    input: none
    output: dict of valid players ({playerName: level})
    """
    data_base = firebase.FirebaseApplication("https://volleyball2020-fd3c0.firebaseio.com/", None)
    try:
        result = data_base.get('/Players', '')
    except Exception as e:
        result = {}
    return result

def get_trainings_list():
    """
    the function will return all the trainings in the system
    input:none
    output:dict of all trainings
    """
    data_base = firebase.FirebaseApplication("https://volleyball2020-fd3c0.firebaseio.com/", None)
    try:
        result = data_base.get('/Trainings','')
    except Exception as e:
        result = {} 
    return result

def add_player_to_training(player_name, training_id):
    """
    the function will add a player to a specific training (add to fire base)
    input: player name and training ID
    output: true or false if adding went successfuly
    """
    if check_if_user_known(player_name):
        valid_players = get_valid_players()  # dict of valid players and levels
        player_level = valid_players[player_name]
        
        data_base = firebase.FirebaseApplication("https://volleyball2020-fd3c0.firebaseio.com/", None)
        try:
            training_details = get_training_details(training_id)
            if training_details != None:
                path = '/Trainings/' + str(training_id) + '/enrolledPlayers/' 
                result = data_base.put(path, player_name, player_level)
                # print (result)
                return True
            else:
                return False # isn't if unnecessary?
        except Exception as e:
            return False
    
    return False


def check_if_user_known(user_name):
    """
    the function will check if a player is recognized (appears in players file)
    input: the name of the player and the file of valid players
    output: boolean if player is valid or not
    """
    valid_players = get_valid_players()  # dict of valid players and levels

    if user_name.lower() in valid_players.keys():
        return True
    else:
        return False


def get_training_details(training_id):
    """
    the function will provide the tarining's details (players, place, time etc...)
    --Example = {'date': '13.2.2020', 'enrolledPlayers': {'aviv mizrahi': 5, 'matan hof': 3}, 'place': 'Goshen Stadium', 'time': '13:40'}
    input: training id
    output: training's details (if training not exists, returns None)
    """
    data_base = firebase.FirebaseApplication("https://volleyball2020-fd3c0.firebaseio.com/", None)
    try:
        path = '/Trainings/' + str(training_id)
        result = data_base.get(path, '')
        # print(path)
    except Exception as e:
        # print(e)
        result = {}
    return result

def get_my_trainings(name):
    """
    This function returns all the trainings of a player
    Input:name of player
    Output:dict of all the player's trainings
    """
    my_trainings = {}
    all = get_trainings_list()
    for id,values in all.items():
        if name in values["enrolledPlayers"]:
            my_trainings[id] = values
    return my_trainings

def main():
    # print(get_training_details(1321))
    print(add_player_to_training('ofir shapira', 1234))

if __name__ == '__main__':
    main()
    