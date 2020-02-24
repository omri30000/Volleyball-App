import Helper
import build_teams
players = Helper.read_file_to_dict("Play.txt")

team1,team2 = build_teams.divide_teams_smart(players)
print("team1 = ",build_teams.from_dict_to_sorted_list(team1),"\n\n\n")
print("team2 = ",build_teams.from_dict_to_sorted_list(team2))
