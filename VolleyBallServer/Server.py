import socket
import re
import collections
import _thread
import Helper

LISTEN_PORT = 2019

data_base = {}

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
    
    data_base = ""
    count_users = 0
    while True:
        # new conversation socket
        client_soc, client_address = listening_sock.accept()
        # from now on, the client and server are connected

        try:
            _thread.start_new_thread(manage_conversation, (client_soc, data_base))
            count_users += 1


        except ConnectionResetError as e:
            print(e)
            print("Error: [WinError 10054] An existing connection was forcibly closed by the remote host")
    listening_sock.close()  # isn't necessary because the server will be closed manually


def manage_conversation():
    pass