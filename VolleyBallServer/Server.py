import socket
import re
import collections
import _thread
import Helper

LISTEN_PORT = 2019
VALID_PLAYERS_FILE_NAME = "Players.txt"
ATTENDING_EVENT_FILE_NAME = "20_2_2020.txt"

def main():
    pass

def build_server():
    # parse data base file to internal dict data base
    data_base = Helper.read_file_to_dict("players.txt")
    
    # Create a TCP/IP socket
    listening_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Connecting to remote computer with port 2019
    server_address = ('', LISTEN_PORT)
    listening_sock.bind(server_address)
    return listening_sock
    

def manage_server(listening_sock):    
    # start listening
    listening_sock.listen(1)
    
    count_users = 0
    while True:
        # new conversation socket
        client_soc, client_address = listening_sock.accept()
        # from now on, the client and server are connected

        try:
            _thread.start_new_thread(manage_conversation, client_soc)
            count_users += 1


        except ConnectionResetError as e:
            print(e)
            print("Error: [WinError 10054] An existing connection was forcibly closed by the remote host")
    listening_sock.close()  # isn't necessary because the server will be closed manually


def manage_conversation(client_soc):
    client_msg = client_soc.recv(2048).decode()

    player_name = client_msg[client_msg.find("#") + 1:]
    player_name = player_name[0:player_name.find("_")] + ' '  + player_name[player_name.find("_") + 1:]
    player_name = player_name[0: player_name.find("$")]

    print(player_name)

    if check_if_user_known(player_name, VALID_PLAYERS_FILE_NAME):
        pass


    #client_soc.sendall("your input is invalid, try again!".encode())
    pass

def check_if_user_known(user_name, database_file):
    """
    the function will check if a player is recognized (appears in players file)
    input: the name of the player and the file of valid players
    output: boolean if player is valid or not
    """
    valid_players = Helper.read_file_to_dict(database_file) # dict of players

    if user_name in valid_players.keys():
        return True
    else:
        return False


if __name__ == '__main__':
    main()
    