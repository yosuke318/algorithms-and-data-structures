import math

class Heap:
    
    @staticmethod
    def left(i):
        return 2*i + 1
    
    @staticmethod
    def right(i):
        return 2*i + 2

    @staticmethod
    def parent(i):
        return math.floor((i-1)/2)
    
    @staticmethod
    def maxHeapify(arr, i):
        l = Heap.left(i)
        r = Heap.right(i)

        biggest = i
        if l < len(arr) and arr[l] > arr[biggest]: biggest = l
        if r < len(arr) and arr[r] > arr[biggest]: biggest = r

        if biggest != i:
            temp = arr[i]
            arr[i] = arr[biggest]
            arr[biggest] = temp
            Heap.maxHeapify(arr,biggest)

    # ここから実装してください。
    # buildMaxHeap
    def buildMaxHeap(arr):
        middle = Heap.parent(len(arr))

        for i in range(middle, -1, -1):
            Heap.maxHeapify(arr, i)

heap1 = [2,42,11,30,10,7,6,5,9]
print(heap1)
Heap.buildMaxHeap(heap1)
print(heap1) # 42, 30, 11, 9, 10, 7, 6, 5, 2

heap2 = [56,4,51,10,12,5,12,4,6,5]
print(heap2) 
Heap.buildMaxHeap(heap2)
print(heap2) # 56, 12, 51, 10, 5, 5, 12, 4, 6, 4