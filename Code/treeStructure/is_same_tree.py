class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def isSameTree(root1, root2):
    # 両方 None なら同じや
    if root1 is None and root2 is None:
        return True
    
    # 片方だけ None なら違うわ
    if root1 is None or root2 is None:
        return False
    
    # データが違ったらアウトや
    if root1.data != root2.data:
        return False
    
    # 左右の部分木を再帰的にチェックや
    return isSameTree(root1.left, root2.left) and isSameTree(root1.right, root2.right)