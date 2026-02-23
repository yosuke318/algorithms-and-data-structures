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

function invertTreeBFS(root: BinaryTree<number> | null): BinaryTree<number> | null {
    if (root === null) return null

    let iterator = root
    let queue = [iterator];
    while(queue.length > 0){
        let iterator = queue.shift();
        if(iterator) {
            let tempNode = iterator.left;
            iterator.left = iterator.right;
            iterator.right = tempNode;
            if(iterator.left) queue.push(iterator.left);
            if(iterator.right) queue.push(iterator.right);
        }
    }

    return iterator;
}  


function invertTreeDFS(root){
    // ベースケース
    if (root == null) return null;
    // 左右の部分木をスワップ
    let temp = root.left;
    root.left = root.right;
    root.right = temp;

    // 左右の子を入れて自身を呼び出し再帰します。
    invertTree(root.right);
    invertTree(root.left);

    return root;
}