import socket
import re
import sys

SERVER_IP = '127.0.0.1'
SERVER_PORT = 2019


def main():
    # Connect to server
    try:
        sock = connect_to_server()
    except Exception as e:
        print("Error: ", e)
        sys.exit()

    # from now on, the client and server are connected
    try:
        print(sock.recv(2048).decode())  # print welcome message (400)
    except ConnectionResetError as e:
        print("ERROR- " + str(e))

    username = "roy_shalev"
    
    msg = "$100#" + username + "$"
    print(msg)
    sock.sendall(msg.encode())


def connect_to_server():
    """
    the function will connect the client with the server
    :return: a conversation socket
    :rtype: socket.socket
    """
    # Create a TCP/IP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Connecting to remote computer 2019
    server_address = (SERVER_IP, SERVER_PORT)
    sock.connect(server_address)
    return sock



if __name__ == '__main__':
    main()
    