def calcDistanceList(pointsList):
    return map(lambda n: abs(int(n.split("-")[0]) - int(n.split("-")[1])), pointsList)