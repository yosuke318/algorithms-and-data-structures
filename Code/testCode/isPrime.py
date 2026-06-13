import math

def assertionTest(a, callback):
    result = callback(a)
    print(f"Checking against {str(a)}, is it valid?...{result}")
    assert result
    return True

def sieveOfPrimes(n):
    cache = [True] * n
    for currentPrime in range(2, math.ceil(math.sqrt(n))):
        i = 2
        ip = i * currentPrime
        while ip < n:
            cache[ip] = False
            i += 1
            ip = i * currentPrime

    primeNumbers = []
    for index, predicate in enumerate(cache):
        if predicate: primeNumbers.append(index)

    return primeNumbers[2:]

# kとnを受け取り、リストAを取り込み、A内の全ての要素が重複しておらず、かつn以下の素数であり、合計でk個存在するかチェックする関数を返します。
# 返されるデータは、クロージャー関数です。
def primeCheck(k, n):
    def isPrime(num):
        if num > 1:
            for i in range(2, math.floor(math.sqrt(num))):
                if (num % i) == 0: return False
            return True
        return False

    def script(aList):
        # set() はリストを受け取り、重複していない要素のみを返します。
        if not len(set(aList)) == len(aList): return False
        if not len(aList) == k: return False
        for i in range(len(aList)):
            if aList[i] > n or not isPrime(aList[i]) : return False
        return True

    return script

assertionTest([2,3,5,7,11,13],primeCheck(6, 15))
assertionTest(sieveOfPrimes(15),primeCheck(6, 15))
assertionTest(sieveOfPrimes(100),primeCheck(25, 100))
assertionTest(sieveOfPrimes(10000),primeCheck(1229, 10000))

# assertionTest([2,3,5,7,11,13,15],primeCheck(6, 15)) # Error
# assertionTest([2,3,5,7,11,12],primeCheck(6, 15)) # Error
# assertionTest([2,3,5,7,11,19],primeCheck(6, 15)) # Error