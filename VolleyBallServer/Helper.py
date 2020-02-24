import json

players = {1:1, 2:2, 3:3}

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
    with open(file_name, 'r') as file:
        players = eval(file.read()) 
    return players


def from_dict_to_list(dict_val):
    list_val = []
    for part in dict_val.items():
        list_val.append(tuple(part))
    return list_val