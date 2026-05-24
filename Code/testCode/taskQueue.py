# 引数が文字列であることを強制するデコレーターを作ります。文字列でなかった場合 WrongDataTypeArray エラーをはきます。
import types
def stringBifunctionForceDecorator(callback):
    def function(str1, str2):
        if not isinstance(str1, str) or not isinstance(str2, str):
            raise Exception("WrongDataTypeArray, Not a string!") 
        return callback(str1, str2)
    return function   

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class Queue:
    def __init__(self):
        self.head = None
        self.tail = None

    def peekFront(self):
        if self.head == None: return None
        return self.head.data

    def enqueue(self, data):
        if self.head == None:
            self.head = Node(data)
        elif self.tail == None:
            self.tail = Node(data)
            self.head.next = self.tail
        else:
            self.tail.next = Node(data)
            self.tail = self.tail.next

    def dequeue(self):
        if self.head is None: return None
        temp = self.head

        if self.head.next is None:
            self.head = None
            self.tail = None
        else:
            self.head = self.head.next

        return temp.data

class TaskQueue:
    def __init__(self):
        self.queue = Queue()

    def taskExists(self):
        return self.queue.peekFront() != None

    def run(self, arr):
        try:
            # 配列が空の時
            if(not arr): raise Exception("EmptyArray")
            # 引数の個数が2個以外の時
            elif len(arr) != 2: raise Exception("InaccurateArguments")

            # ラムダを実行します。
            return self.queue.dequeue()(arr[0], arr[1])

        except Exception as err:
            print(err)
        #     # errをキャッチしたら空文字を返します。
            return ""
        
    

    # インサート関数bifunctionのラムダを受け取ります。
    def insert(self, callback):
        testSample1 = "str1"
        testSample2 = "str2"

        try:
            # ラムダでなかったらエラー
            if(type(callback) is not types.LambdaType): raise Exception("Callback is not a function")

        
        except Exception as err:
            # エラーをキャッチしログに記録します。
            print("Error occured and it looks like it was an... " + str(err))
        
        
        # 文字列を返さなかったらアサーションを返します。
        else:
            assert(isinstance(callback(testSample1, testSample2), str))
        # decoratorでラムダに文字列を強制しqueueに入れます。
        self.queue.enqueue(stringBifunctionForceDecorator(callback))
    

scheduler = TaskQueue()

# ラムダをインサート
scheduler.insert(lambda str1, str2: str1 + str2 )
scheduler.insert(lambda str1, str2: str1.upper() + str2)
scheduler.insert(lambda str1, str2: str1[0] + "." + str2[0])
# scheduler.insert("not a function") # エラー ラムダでない
# scheduler.insert(lambda str1, str2: len(str1) + len(str2)) # エラー 文字列を返さないラムダ


# run()に引数を渡します。
print(scheduler.run(["hello", "world"])) # 成功する例
print(scheduler.run(["hello", "world"])) # 成功する例
print(scheduler.run(["hello", "world"])) # 成功する例
# print(scheduler.run(["nice", "world", "hi"]))#　エラー'InaccurateArguments'
# print(scheduler.run([])) # エラー'EmptyArray'
# print(scheduler.run([3, "world"]))#　エラー'WrongDataTypeArray'