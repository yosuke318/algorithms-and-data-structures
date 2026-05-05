import math
import itertools

pythagora = lambda x, y: math.floor(math.sqrt(x ** 2 + y ** 2))
addition = lambda x, y: x + y
multiplication = lambda x, y: x * y

class LambdaMachine:
    def __init__(self):
        self._handlers = []
        self._cycle = None

    def insert(self, key, function: callable):
        self._handlers.append(function)
        self._cycle = itertools.cycle(self._handlers)

    def roundRobinRetrieve(self):
        return next(self._cycle)


lambdaMachine = LambdaMachine()
lambdaMachine.insert("pythagora", pythagora)
lambdaMachine.insert("addition", addition)
lambdaMachine.insert("multiplication", multiplication)

print(lambdaMachine.roundRobinRetrieve()(6, 8))  # pythagora → 10
print(lambdaMachine.roundRobinRetrieve()(6, 8))  # addition  → 14
print(lambdaMachine.roundRobinRetrieve()(6, 8))  # multiply  → 48
print(lambdaMachine.roundRobinRetrieve()(6, 8))  # pythagora → 10 (ループ)