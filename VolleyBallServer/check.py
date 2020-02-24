import Helper
import build_teams
players = Helper.read_file_to_dict("Players.txt")

team1,team2 = build_teams.divide_teams(players)
print("team1 = ",build_teams.from_dict_to_sorted_list(team1),"\n\n\n")
print("team2 = ",build_teams.from_dict_to_sorted_list(team2))
