class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right


def totalEvenGrandparent(root):

    return totalEvenGrandParentHelper(root, None, None)


def totalEvenGrandParentHelper(node: BinaryTree, parent_value: int or None, grandParentValue: int or None):
    if node is None:
        return 0

    total = node.data if grandParentValue is not None and grandParentValue % 2 == 0 else 0

    total += totalEvenGrandParentHelper(node.left, node.data, parent_value)
    total += totalEvenGrandParentHelper(node.right, node.data, parent_value)

    return total
