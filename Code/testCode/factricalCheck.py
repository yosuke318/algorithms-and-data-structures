from typing import List


def factorial(n: int) -> int:
    if n <= 0:
        return 1
    return n * factorial(n - 1)


def unitTestCheck(predicate: bool) -> None:
    if predicate:
        print("The test passed!!")
    else:
        print("ERROR! The test failed!!")


def factorialCheck(n: int, expected: int) -> None:
    # 1 ケースだけチェックする関数
    return factorial(n) == expected


def unitTests(inputs: List[int], outputs: List[int]) -> None:
    # ここでだけループを回す
    for i in range(len(inputs)):
        unitTestCheck(factorialCheck(inputs[i], outputs[i]))

