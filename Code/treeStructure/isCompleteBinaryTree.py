class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

from collections import deque

def isCompleteBinaryTree(root):
    queue = []
    queue = deque([root])

    while queue:

        for i in range(len(queue)):
            print(queue[0].data)
            node = queue.popleft()

            queue.append(node.left)
            queue.append(node.right)

        if None in queue:
            return False

    return True