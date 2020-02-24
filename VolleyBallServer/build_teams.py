import Helper
#shufle
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

def divide_teams_smart(players):
    parts = from_dict_to_sorted_list(players)
    return fill_teams(players,[],[])



def fill_teams(players,team1,team2):
    if len(players) == 0:
        return abs(get_team_level(team2) - get_team_level(team1))


    if len(team1) > len(team2) + len(players) +1: # is it bigger than one
        raise "erorr mate!"

    
    cond1,cond2 = True,True

    team1_copy = copy_list(team1)
    team2_copy = copy_list(team2)
    player = players[0]
    del players[0]
    #option one -> team 1
    #team
    team1_copy += [player]
    try:
        val1,team1_copy1,team2_copy1 = fill_teams(players,team1_copy,team2_copy)
    except:
        cond1 = False        


    #option two -> team 2
    del team1_copy[-1]
    team2_copy += [player]
    try:
        val2, team1_copy2,team2_copy2 = fill_teams(players,team1_copy,team2_copy)

        del team2_copy[-1]
    except:
        cond2 = False


    if cond1 and cond2:
        if val1<val2:
            return val1,team1_copy1,team2_copy1 
        else:
            return val2,team1_copy2,team2_copy2
    elif cond1:
        return val1,team1_copy1,team2_copy1 
    elif cond2:
         return val2,team1_copy2,team2_copy2

    
    else:
        raise "error mate"

    
    
    team2[player[0]] = player[1] 
    del team1[player[0]]

def get_team_level(team):
    sum = 0
    for val in team:
        sum += val[i]
    return sum 


def copy_list(team):
    team_copy = []
    for i in team:
        team_copy += [i]
    return team_copy
