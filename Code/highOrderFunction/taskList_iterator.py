from typing import List

def todoCall(arr: List[str]):
    def f():
        yield from arr
        while True:
            yield "All done!"
    
    gen = f()
    return lambda: next(gen)

todoCaller = todoCall(["Read a Book", "Work out", "Recursion"])

print(todoCaller())
print(todoCaller())
print(todoCaller())
print(todoCaller())