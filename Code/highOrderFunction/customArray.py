    
cube = lambda n: n ** 3

def splitAndAdd(n):


    sumDigit = 0
    sumDigit = sum(int(d) for d in str(n))
    # while n > 0:

    #     sumDigit += n % 10

    #     n = n // 10

    return sumDigit



def customArray(f, arr):
    for i in arr:
        print(f(i))


customArray(cube, [3,11,24,31])

customArray(splitAndAdd, [3,11,24,31])