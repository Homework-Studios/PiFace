import numpy as np
import pandas as pd
import pickle
import socket

# import RPi.GPIO as GPIO

print("PiFace Started")


def load_string_from_file(filepath):
    with open(filepath) as f:
        for val in f.readlines():
            val = val
        return val


information = load_string_from_file("C:/Users/timon/Desktop/Coding_files/PiFace/python/information.txt").split("%%%%%")

ip = information[0].split(":")[0]
port = information[0].split(":")[1]

key = information[1]

print("Connecting to server...")
print("ip: " + ip + " port: " + port)
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((ip, int(port)))  # connect to the server

s.send(key.encode("utf-8"))


async def reciev_data():
    while True:
        data = s.recv(1024)
        print("Data recieved: " + data)
        if data.decode("utf-8") == "ConnectionApproved":
            global ConnectionApproved
            ConnectionApproved = True
            print("Connection Approved")

threading.Thread(target=reciev_data).start()
print("now recieving data and waiting for connection approval")
