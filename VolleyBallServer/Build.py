import json

players = {1:1, 2:2, 3:3}

with open("add.txt",'w') as file:
    file.write(str(players))
input()
with open("add.txt", 'r') as file:
    players = eval(file.read()) 

print(players)
print(type(players)) 
