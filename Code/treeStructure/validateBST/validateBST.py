class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def validateBST(root):
    # 初期範囲は -inf ~ inf（制約なし）でhelperを呼び出す
    return helper(root, float('-inf'), float('inf'))


def helper(node, min_val, max_val):
    # Noneに到達したら違反なし
    if node is None:
        return True
    # 祖先ノードから引き継いだ範囲を外れていたらBST違反
    if node.data <= min_val or node.data >= max_val:
        return False
    # 左部分木：上限を現在のノードの値に更新して渡す
    # 右部分木：下限を現在のノードの値に更新して渡す
    return (helper(node.left, min_val, node.data) and
            helper(node.right, node.data, max_val))