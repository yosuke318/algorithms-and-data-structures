def swapCase(charList):
    return map(lambda x: x.upper() if x.islower() else x.lower(), charList)