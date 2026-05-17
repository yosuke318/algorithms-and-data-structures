import copy
import math
def equalAssertion(a,b, callback = None):
    equality = (a == b) if callback is None else callback(a, b)
    print(f"Comparing {str(a)} and {str(b)}..." + ("They are equal." if equality else "Error, they are NOT equal."))
    # 等しくなければクラッシュします。
    assert equality
    return True

# 重複していないメールのみを全て返す関数。
def createSetList(arr):
    arrUnique = list(set(arr))
    return arrUnique

def unorderedArrayEquality(a,b):
    aHash = {}
    bHash = {}

    for i in range(len(a)):
        if a[i] in aHash: aHash[a[i]] += 1
        else: aHash[a[i]] = 1

    for i in range(len(b)):
        if b[i] in bHash: bHash[b[i]] += 1
        else: bHash[b[i]] = 1

    for key in aHash:
        if key not in bHash: return False
    return True

emailArr = ["aaa@bbb.com", "bbb@ccc.com", "ccc@ddd.com", "aaa@bbb.com", "ccc@bbb.com"]

copyArr = copy.deepcopy(emailArr)
setArr = createSetList(emailArr)
equalAssertion(copyArr, setArr, unorderedArrayEquality)