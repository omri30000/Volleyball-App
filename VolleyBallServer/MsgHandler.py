

def enroll_to_training(enroll_msg):  # response to 100
    """
    the function enrolls a player to a specific training
    :param enroll_msg: msg of type 100 from client (Example: "$100#1234#omri_zaiman$\n")
    :type enroll_msg: string(includes training ID and player name)
    :return: message of type 101/102
    :rtype: string 
    """
    pass


def provide_available_training_list():  # response to 200
    """
    the function creates msg of type 201 with the list of trainings (responses to msg type 200 which has no parameters)
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
