class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right



def totalEvenGrandparent(root):

    arr = []
    arr = totalEvenGrandParentHelper(root, arr, None, None)
    return sum(arr)



def totalEvenGrandParentHelper(root, arr, parent, grandParent):

    if root is not None:
        if grandParent is not None and grandParent.data % 2 == 0: arr.append(root.data)
        totalEvenGrandParentHelper(root.left, arr, root, parent)
        totalEvenGrandParentHelper(root.right, arr, root, parent)

    return arr
