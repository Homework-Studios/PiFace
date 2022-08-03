import numpy as np
import pandas as pd
import pickle

print("PiFace Started")


def load_csv(filepath):
    data = []
    col = []
    checkcol = False
    with open(filepath) as f:
        for val in f.readlines():
            val = val.replace("\n", "")
            val = val.split(',')
            if checkcol is False:
                col = val
                checkcol = True
            else:
                data.append(val)
    df = pd.DataFrame(data=data, columns=col)
    return df


filedata = load_csv("/home/pi/information.txt")
print(filedata)
