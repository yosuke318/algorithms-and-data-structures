import math

class Heap:

    @staticmethod
    def left(i):
        return i * 2 + 1

    @staticmethod
    def right(i):
        return i * 2 + 2

    @staticmethod
    def parent(i):
        return math.floor((i - 1) / 2)
        

    @staticmethod
    def maxHeapify(arr: List[int], i: int):

        right = Heap.right(i)
        left = Heap.left(i)

        biggest = i
        if len(arr) > right and arr[right] > arr[biggest]: biggest = right
        if len(arr) > left and arr[left] > arr[biggest]: biggest = left

        if biggest != i:
            temp = arr[i]
            arr[i] = arr[biggest]
            arr[biggest] = temp
            Heap.maxHeapify(arr, biggest)

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


[983,974,962,974,950,858,906,814,952,782,936,849,857,899,732,813,771,876,927,780,733,850,901,598,678,788,721,836,845,508,494,525,497,764,735,850,565,898,507,458,700,89,276,640,429,299,577,79,3,250,638,226,576,567,555,535,155,450,33,45,101,29,94,222,383,148,238,401,482,584,706,781,323,294,463,753,684,146,198,224,394,570,677]
[983,974,962,974,950,858,906,814,952,782,936,849,857,899,732,584,813,876,927,780,733,850,901,598,678,788,721,836,845,508,494,525,497,764,771,850,565,898,507,458,700,89,276,640,429,299,577,79,3,250,638,226,576,567,555,535,155,450,33,45,101,29,94,222,383,148,238,401,482,735,706,781,323,294,463,753,684,146,198,224,394,570,677]

