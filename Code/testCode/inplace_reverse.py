import math
import copy

def equalAssertion(a,b, callback = None):
    equality = (a == b) if callback is None else callback(a, b)
    print(f"Comparing {str(a)} and {str(b)}..." + ("They are equal." if equality else "Error, they are NOT equal."))
    # 等しくなければクラッシュします。
    assert equality
    return True

# ここから開発してください。

def reverseArr(arr):
    """in plcae入れ替え"""
    middle = math.floor(len(arr) / 2)

    for i in range(middle):
        [arr[i],arr[len(arr)-1-i]] = [arr[len(arr)-1-i], arr[i]]


def reversedArrayEquality(a, b):
    if len(a) != len(b): return False

    for i in range(len(a)):
        if a[i] != b[len(a) - 1 - i]: return False

    return True


strArr = ["FIAT", "Mercedes-Benz","CITROËN","BLUEBIRD","Alfa Romeo"]
copyArr = copy.deepcopy(strArr)
reverseArr(strArr)

equalAssertion(strArr,copyArr,reversedArrayEquality)
