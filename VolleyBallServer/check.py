import Helper
import build_teams
import Mail
import time
players = Helper.read_file_to_dict("Players.txt")


print(players)
print("Shuffle way")
team1,team2 = build_teams.divide_teams(players)
print("team1 = ",team1,build_teams.get_team_level(build_teams.from_dict_to_sorted_list(team1)))#build_teams.from_dict_to_sorted_list(team1),"\n\n\n")
print("\n\nteam2 = ",team2,build_teams.get_team_level(build_teams.from_dict_to_sorted_list(team2)))#build_teams.from_dict_to_sorted_list(team2))


print("New way\n")
start_time = time.time()
val,team1,team2 = build_teams.divide_teams_smart(players)
print (time.time() - start_time, " seconds")

print("team1 = ",team1,build_teams.get_team_level(team1))#build_teams.from_dict_to_sorted_list(team1),"\n\n\n")
print("\n\nteam2 = ",team2,build_teams.get_team_level(team2))#build_teams.from_dict_to_sorted_list(team2))
