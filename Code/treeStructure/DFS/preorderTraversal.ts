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

let globalArr:number[] = [];

function preorderTraversal(root:BinaryTree<number> | null): number[]{
    let iterator = root;

    if(iterator !== null) {
        globalArr.push(iterator.data);
        preorderTraversal(iterator.left);
        preorderTraversal(iterator.right);
    }

    return globalArr;
}


