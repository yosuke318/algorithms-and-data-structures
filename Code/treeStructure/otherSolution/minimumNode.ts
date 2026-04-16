class BinaryTree{
    constructor(data, left = null, right = null){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

function minimumNode(root: BinaryTree | null): BinaryTree | null{
    // ベースケース　左の子がnullになったらrootを返します。
    if(root.left == null) return root;
    // rootを左へ進めて再帰的に繰り返します。
    return minimumNode(root.left);
}