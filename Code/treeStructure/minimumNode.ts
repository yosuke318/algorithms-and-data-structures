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

function minimumNode(root:BinaryTree<number> | null): BinaryTree<number> | null{
    if(root === null) return null;
    let iterator = root;

    while(iterator.left !== null){
        iterator = iterator.left;
    }

    return iterator;
}

