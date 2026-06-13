import datetime

# 'timerDecorator'というデコレータ関数を定義します。この関数は単項関数 'f' を引数として取ります。
def timerDecorator(f):
    def function(arg):
        # 'function'関数はまず、現在の時間を記録します。これが実行時間の計測の開始点です。
        start = datetime.datetime.now()

        # そして元の関数 'f' を呼び出し、その結果を保存します。
        result = f(arg)

        # 次に再び現在の時間を取得します。これが実行時間の計測の終点です。
        end = datetime.datetime.now()

        # 実行時間（終点から開始点の時間差）を計算し、コンソールに表示します。
        print("This function took: " + str(end-start) + "ms")

        # 最後に、元の関数 'f' の結果を返します。
        return result
    # 'function'関数を返します。これがデコレータの新しい機能を追加した結果の関数です。
    return function

# O(1)の計算量を持つ関数に対して'timerDecorator'を適用します。そしてその関数を実行します。
print(timerDecorator(lambda x: x*2)(2424))

# O(n^2)の計算量を持つ関数を定義します。
def on2func(x):
    finalResult = 1
    for i in range(x):
        result = i
        for j in range(i):
            result += j
        finalResult += result
    return finalResult

# O(n^2)の計算量を持つ関数に対して'timerDecorator'を適用します。そしてその関数を実行します。
print(timerDecorator(on2func)(500))

# O(2^n)の計算量を持つフィボナッチ関数を定義します。
def fibonacci(n):
    if n <= 0: return 0
    if n == 1: return 1
    return fibonacci(n-1) + fibonacci(n-2)

# O(n)の計算量を持つフィボナッチ関数を定義します。
def fibonacciFast(fib1, fib2, n):
    if n <= 0: return fib1
    return fibonacciFast(fib2, fib1 + fib2, n-1)

# フィボナッチ関数に対して'timerDecorator'を適用し、新しい関数を生成します。そしてその関数を実行します。
timedFibonacci = timerDecorator(fibonacci)
print(timedFibonacci(20))

# 計算量がO(n)のフィボナッチ関数に対して'timerDecorator'を適用し、新しい関数を生成します。そしてその関数を実行します。
timedFibonacciFast = timerDecorator(lambda n: fibonacciFast(0, 1, n))
print(timedFibonacciFast(20))