class BinaryTree<E>{
    data: E;
    left: BinaryTree<E> | null;
    right: BinaryTree<E> | null;

    constructor(data: E, left: BinaryTree<E> | null = null, right: BinaryTree<E> | null = null){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

function maximumNode(root:BinaryTree<number> | null): BinaryTree<number> | null{
    if(root.right === null) return root;
    
    return maximumNode(root.right)
    
}

