import json

players = {1:1, 2:2, 3:3}

def get_users_num(file_name):
    return len(read_file_to_dict())
    
def write_dict_to_file(players, file_name):
    '''
    the function will get the dict of players and write it into a file
    input: dict of players and name of file
    output: none
    '''
    with open(file_name,'w') as file:
        file.write(str(players))


def read_file_to_dict(file_name):
    '''
    the function will get the file name and read a dict of players from inside it
    input: dict of players and name of file
    output: dict of players
    '''
    #print("--rftd--")
    #print("name: " + file_name)
    with open(file_name, 'r') as file:
        if not check_if_file_empty(file): # file not empty
            players = eval(file.read()) 
        else:
            players = {}

    return players

def check_if_file_empty(my_file):
    my_file.seek(0) #ensure you're at the start of the file..
    first_char = my_file.read(1) #get the first character
    if not first_char:
        return True
    else:
        my_file.seek(0) #first character wasn't empty, return to start of file.
        return False

def from_dict_to_list(dictt):
    parts = []
    for i in dictt.items():
        parts += [i]
    return parts


def from_list_to_dict(listt):
    dictt ={}
    for dou in listt:
        dictt[dou[0]] = dou[1]
    return dictt