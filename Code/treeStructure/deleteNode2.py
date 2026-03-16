class BinaryTree:
    def __init__(self, data, left = None, right = None):
        self.data = data
        self.left = left
        self.right = right

def bstDelete(root, key):
    if root == None: return None
    node = search(root, key)
    if node == None: return root

    parent = findParent(root, node)

    if node.left == None and node.right == None:
        if parent == None: return None  # rootを削除
        if parent.left is not None and parent.left.data == key:
            parent.left = None
        elif parent.right is not None and parent.right.data == key:
            parent.right = None

    elif node.left == None:  # ← elif に変更
        root = transplant(root, parent, node, node.right)

    elif node.right == None:
        root = transplant(root, parent, node, node.left)

    else:
        successor = findSuccessor(root, node)
        successorP = findParent(root, successor)

        if successor != node.right:
            root = transplant(root, successorP, successor, successor.right)
            successor.right = node.right

        root = transplant(root, parent, node, successor)
        successor.left = node.left

    return root  # ← rootを返す


def transplant(root, nodeParent, node, target):
    if nodeParent == None:
        root = target  # rootを更新して返す
    elif nodeParent.left == node:
        nodeParent.left = target
    else:
        nodeParent.right = target
    return root  # ← rootを返す


def findSuccessor(root, node):
    targetNode = node
    if targetNode == None: return None
    if targetNode.right != None: return minimumNode(targetNode.right)

    successor = None
    iterator = root

    while iterator != None:
        if targetNode.data == iterator.data:
            return successor
        if targetNode.data < iterator.data and (successor == None or iterator.data < successor.data):
            successor = iterator  # ← iterator.left ではなく iterator
        if targetNode.data < iterator.data:
            iterator = iterator.left
        else:
            iterator = iterator.right

    return successor




def search(root, key):
    iterator = root

    while iterator is not None:
        if iterator.data == key: return iterator
        if iterator.data > key: iterator = iterator.left
        else: iterator = iterator.right

    return None

def keyExist(root, key):
    iterator = root

    while iterator is not None:
        if iterator.data == key:
            return True
        if iterator.data > key: 
            iterator = iterator.left
        else:
            iterator = iterator.right

    return False

def transplant(root, nodeParent, node, target):
    if nodeParent == None: 
        root = target
    elif nodeParent.left == node: 
        nodeParent.left = target
    else:
        nodeParent.right = target


def minimumNode(node):

    iterator = node
    while iterator != None and iterator.left != None:
        iterator = iterator.left

    return iterator