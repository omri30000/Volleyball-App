import Score
class Game:
    def __init__(self):
        self.score = Score()
        self.team1_players = []
        self.team2_players = []
    def get_winner(self):
        if self.score.team1 > self.score.team2:
            return  self.team1_players
        return self.team2_players