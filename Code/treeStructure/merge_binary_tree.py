
from typing import Optional, Any, List
class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def build_tree(a: List[Any], i: int = 0) -> Optional[BinaryTree]:
    if i >= len(a) or a[i] is None:
        return None
    return BinaryTree(
        a[i],
        build_tree(a, 2*i + 1),
        build_tree(a, 2*i + 2),
    )

def mergeBST(root1, root2):
    # ベースケース: 両方Noneなら None を返す
    if root1 is None and root2 is None:
        return None
    
    # どちらかがNoneなら、もう片方をそのまま返す
    if root1 is None:
        return root2
    if root2 is None:
        return root1
    
    # 両方とも存在する場合: 新しいノードを作成してマージ
    merged_node = BinaryTree(root1.data + root2.data)
    
    # 左右の子ノードを再帰的にマージ
    merged_node.left = mergeBST(root1.left, root2.left)
    merged_node.right = mergeBST(root1.right, root2.right)
    
    return merged_node

