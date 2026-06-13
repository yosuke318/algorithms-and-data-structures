def calcDistanceList(pointsList):
    def calcDistance(n):
        a, b = n.split("-")
        return abs(int(a) - int(b))
    
    return map(calcDistance, pointsList)