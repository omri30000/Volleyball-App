import Helper

def divide_teams(players): #dict of { players : level }
    team_one ={}
    team_two = {}
    players_num = 1
    team = 0
    parts =from_dict_to_sorted_list(players)
    for part in parts:
        team = team %2
        if team == 0:
            team_one[part[0]] = part[1]
        else:
            team_two[part[0]] = part[1]
        players_num+=1
        if players_num > 1 :
            players_num = 0
            team +=1
    return (team_one,team_two)

def by_value(tup):
    return tup[1]

def from_dict_to_sorted_list(players):
    parts =Helper.from_dict_to_list(players)
    return sorted(parts,key = by_value,reverse= True)



"""
def add_next(players,team1,team2):
    if len(players) == 0:
        return abs(get_team_level(team2) - get_team_level(team1))
   

    team1_copy = {}
    team2_copy = {}

    if num > num2:
        raise "erorr mate!"
    player = players.items()[0]
    del players[player[0]]
   
    #option one -> team 1
    team1[player[0]] =player[1] 
    val1 = add_next(players,team1,team2)
    #option two -> team 2
    del team1[player[0]]
    team2[player[0]] = player[1] 
    del team1[player[0]]

def get_team_level(team):
    sum = 0
    for val in team.values():
        sum += val
    return sum 


def copy_dict(team):
    team_copy = {}
    for i in team.items():
        team_copy[i[0]] = i[1]
    return team_copy
    """
