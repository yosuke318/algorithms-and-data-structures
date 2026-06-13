from typing import List

def todoCall(arr: List["str"]):

    index = 0

    def f():
        nonlocal index
        if index > len(arr) - 1:
            return "All done!"
        
        temp = index
        index += 1
        return arr[temp]

    return f

todoCaller = todoCall(["Read a Book", "Work out", "Recursion"])

print(todoCaller())
print(todoCaller())
print(todoCaller())
print(todoCaller())