import threading
import socket

# import RPi.GPIO as GPIO

print("PiFace is Starting")


def load_string_from_file(filepath):
    with open(filepath) as f:
        for val in f.readlines():
            val = val
        return val


information = load_string_from_file("home/pi/information.txt").split("%%%%%")

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


async def recieve_data():
    while True:
        data = s.recv(1024)
        print("Data recieved: " + data)
        if data.decode("utf-8") == "ConnectionApproved":
            global ConnectionApproved
            ConnectionApproved = True
            print("Connection Approved")


threading.Thread(target=recieve_data).start()
print("now recieving data and waiting for connection approval")

global ConnectionApproved
if ConnectionApproved:
    print("Connection Approved")

print("PiFace Started")
