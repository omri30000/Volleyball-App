class Score:
    def __init__(self):
        
        self.team1 = 0
        self.team2 = 0

    def __init__(self,team1,team2):
        self.team1 = team1
        self.team2 = team2
    def inc_team1(self):
        self.team1 += 1
    def inc_team2(self):
        self.team2 += 1 
    def dec_team1(self):
        if self.team1  >0:
            self.team1 -= 1
    def dec_team2(self):
        if self.team1 >0:
            self.team2 -= 1