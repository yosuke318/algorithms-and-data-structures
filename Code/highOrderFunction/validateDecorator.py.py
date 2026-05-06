from typing import List


def sumOfArray(arr:List[int]):

    return sum(arr)


def validateDecorator(f):
    def function(arr:List[int]):
        count = 0
        for i in arr:
            print(i)
            if arr[i] < 10:
                count += 1
        if count > 0:
            return f"{count} error found"
        return function(arr)

    return function


sum = validateDecorator(sumOfArray)

print(sum([10, 20, 30, 40]))

