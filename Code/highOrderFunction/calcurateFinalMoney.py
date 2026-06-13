from functools import reduce
import math

def calculateFinalMoney(interests,capital):

    result = reduce(lambda capital, rate: (rate/100 + 1)*capital, interests, capital)

    return math.floor(result)


