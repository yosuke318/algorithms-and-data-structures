class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def symmetricTree(root):

    arr1 = []
    arr2 = []

    left = symmetricTreeLeftHelper(root, arr1)
    right = symmetricTreeRightHelper(root, arr2)

    print("leftNOdelist",left)
    print("rightNodelist", right)

    return left == right




def symmetricTreeLeftHelper(root, arr):
    arr.append(root.data) if root is not None else arr.append(None)
    if root:
        symmetricTreeLeftHelper(root.left, arr)
        symmetricTreeLeftHelper(root.right,arr)

    return arr


def symmetricTreeRightHelper(root, arr):

    arr.append(root.data) if root is not None else arr.append(None)
    if root:
        symmetricTreeRightHelper(root.right, arr)
        symmetricTreeRightHelper(root.left, arr)
        

    return arr

