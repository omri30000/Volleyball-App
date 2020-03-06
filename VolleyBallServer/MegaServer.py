import socket
import re
import collections
import _thread
import Helper
import build_teams
import threading
import Mail
import os
import MsgHandler

LISTEN_PORT = 2019
VALID_PLAYERS_FILE_NAME = "Players.txt"

def main():
    print("Welcome to Volleyball server made by Omri and Ofir\n")
    print("**Entering details of next training**")
    
    create_new_event()

    l_socket = build_server()
    manage_server(l_socket)



def create_new_event(day, month, year):
    attending_players_file_name = day + '_' + month + '_' + year + ".txt"
    #open a new file if it doens't exist
    with open(attending_players_file_name,'w') as file:
        pass

def build_server():
    # parse data base file to internal dict data base
    #data_base = Helper.read_file_to_dict("players.txt")
    
    # Create a TCP/IP socket
    listening_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Connecting to remote computer with port 2019
    server_address = ('', LISTEN_PORT)
    listening_sock.bind(server_address)
    return listening_sock
    

def manage_server(listening_sock):    
    # start listening
    listening_sock.listen(1)
 
    threads = []
    i = 0
    while True:

        # new conversation socket
        client_soc, client_address = listening_sock.accept()
        # from now on, the client and server are connected

        try:
            t = threading.Thread(target=manage_conversation, args=(client_soc, i))
            i = i + 1
            t.setDaemon(True)  # tells the thread to be detached
            t.start()

            threads.append(t)  # add to threads list


        except ConnectionResetError as e:
            print(e)
            print("Error: [WinError 10054] An existing connection was forcibly closed by the remote host")



    listening_sock.close()  # isn't necessary because the server will be closed manually

    #teams = build_teams.divide_teams(Helper.read_file_to_dict(ATTENDING_EVENT_FILE_NAME))

    #print(teams)
    #Mail.manage_mail(teams[0],teams[1])
    #delete_file()



def manage_conversation(client_soc, user_id):

    print("start converstion with user")
    client_soc.sendall("$400$\n".encode())

    finish = False
    while not finish:
        try:
            client_msg = client_soc.recv(2048).decode()
            msg_code = client_msg[1:4]  # between $$ - $100$ for example




    


    player_name = client_msg[client_msg.find("#") + 1:]
    player_name = player_name[0: player_name.find("$")]

    if len(player_name.split("_")) != 2:
        client_soc.sendall("$300#INVALID_USER$\n".encode())
    else:
        player_name = player_name[0:player_name.find("_")] + ' '  + player_name[player_name.find("_") + 1:]

        print(player_name)

        if "$100#" in client_msg:
            if check_if_user_known(player_name, VALID_PLAYERS_FILE_NAME):
                attending_players = Helper.read_file_to_dict(ATTENDING_EVENT_FILE_NAME)
                valid_players = Helper.read_file_to_dict(VALID_PLAYERS_FILE_NAME)

                attending_players[player_name] = valid_players[player_name]
                Helper.write_dict_to_file(attending_players, ATTENDING_EVENT_FILE_NAME)
                client_soc.sendall("$200#OK$\n".encode())

            else:
                client_soc.sendall("$300#INVALID_USER$\n".encode())

def check_if_user_known(user_name, database_file):
    """
    the function will check if a player is recognized (appears in players file)
    input: the name of the player and the file of valid players
    output: boolean if player is valid or not
    """
    valid_players = Helper.read_file_to_dict(database_file) # dict of players

    if user_name.lower() in valid_players.keys():
        return True
    else:
        return False

def delete_file():
    global ATTENDING_EVENT_FILE_NAME
    key = input("Would you want to delete the file for the event "+ ATTENDING_EVENT_FILE_NAME +"Y/N")
    if key.upper() =='Y':
        os.remove(ATTENDING_EVENT_FILE_NAME)


if __name__ == '__main__':
    main()