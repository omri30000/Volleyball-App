import teams_building

class Training:
    training_num = 0
    def __init__(self,date,location):
        training_num +=1
        self.id = training_num
        self.games = []
        self.date = date
        #self.register_date = 
        self.location = location
        self.players = []
        self.teams =[]
        #self.file = 
    def add_game(self,game):
        self.games.append(game)
    def add_player(self,player):
        #check if player in data base if it is
        if player not in self.players:
            self.players.append(player)
    def build_teams(self):
        self.teams = teams_building.divide_teams_smart(self.players)