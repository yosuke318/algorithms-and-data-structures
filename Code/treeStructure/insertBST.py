class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def bstInsert(root: BinaryTree, key: int) -> BinaryTree:
    
    if root is None: return BinaryTree(key)  # 挿入位置に到達したらノードを作成して返す

    if root.data < key: return root

    if root.data < key: bstInsert(root.right, key)
    elif root.data > key: bstInsert(root.left, key)
    else: return root  # すでに存在する場合は何もしない


    return root