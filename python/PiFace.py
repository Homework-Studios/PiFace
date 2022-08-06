import asyncio
import threading
import socket

# import RPi.GPIO as GPIO
import time

print("PiFace is Starting")


def load_string_from_file(filepath):
    with open(filepath) as f:
        for val in f.readlines():
            val = val
        return val


information = load_string_from_file("/home/pi/information.txt").split("%%%%%")

ip = information[0].split(":")[0]
port = information[0].split(":")[1]

key = information[1]
name = information[2]

authentication = key + "%%%%%" + name

print("Connecting to server...")
print("ip: " + ip + " port: " + port)
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((ip, int(port)))  # connect to the server


def send_data(data):
    print("Data sent: " + data)
    s.send(data.encode("utf-8"))


send_data(authentication)


def recieve_data():
    data = s.recv(1024).decode("utf-8")
    print("Data recieved: " + data)
    return data


time.sleep(0.25)
print("now recieving data and waiting for connection approval")
if recieve_data() == "connecting":
    if recieve_data() == "ConnectionApproved":
        print("Connection Approved")

    print("PiFace Started")
