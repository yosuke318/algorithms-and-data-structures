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
    def maxHeapify(arr, end, i):
        # print(i)

        left_index = Heap.left(i)
        right_index = Heap.right(i)

        biggest = i

        if left_index <= end and arr[left_index] > arr[biggest]: 
            biggest = left_index
        if right_index <= end and arr[right_index] > arr[biggest]: 
            biggest = right_index

        if biggest != i:
            temp = arr[i]
            arr[i] = arr[biggest]
            arr[biggest] = temp

            Heap.maxHeapify(arr, end, biggest)
        
    @staticmethod
    def maxBuildHeap(arr):

        middle = Heap.parent(len(arr))

        for i in range(middle, -1, -1):
            print("middle:",middle)
            print("i:",i)
            Heap.maxHeapify(arr, len(arr)-1, i)



def heapsort(intArr):

    Heap.maxBuildHeap(intArr)

    heapEnd = len(intArr) - 1

    while heapEnd > 0:

        temp = intArr[heapEnd]
        intArr[heapEnd] = intArr[0]
        intArr[0] = temp

        heapEnd -= 1

        Heap.maxHeapify(intArr, heapEnd, 0)

    return intArr
    


