class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def minDepth(root):
    # ノードが None やったら深さは 0
    if root is None:
        return 0
    
    # 左右の子が両方 None なら葉ノードやから深さ 1
    if root.left is None and root.right is None:
        return 0
    
    return min(minDepth(root.right), minDepth(root.left)) + 1