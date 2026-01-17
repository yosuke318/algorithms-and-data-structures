class BinaryTree {
    public data: number;
    public left: BinaryTree | null
    public right: BinaryTree | null;

    constructor(data: number, left: BinaryTree | null = null, right: BinaryTree | null = null) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

function predecessor(root: BinaryTree | null, key: number): BinaryTree | null {
    let targetNode = findNode(root, key);
    if (targetNode === null) return null;

    if(targetNode.left !== null) return maximumNode(targetNode.left);

    let predecessor = null;
    let iterator = root;

    while (iterator !== null) {

        // targetとitarator.dataが等しくなったらpredecessorを返す。
        if (iterator.data === targetNode.data) {
            return predecessor;
        }
        //右に進む時は、現在のiteratorが先行のーどになる可能性があるので、predecessorに保存しておく。
        if (iterator.data < targetNode.data) {
            predecessor = iterator;
            iterator = iterator.right;
        } else {
            iterator = iterator.left;
        }
    }

    return predecessor;
}

function findNode(root: BinaryTree | null, key: number): BinaryTree | null {
    let iterator = root;

    while (iterator !== null) {
        if (iterator.data === key) return iterator;
        if (iterator.data < key) iterator = iterator.right;
        else iterator = iterator.left;
    }

    return null;
}

function maximumNode(root: BinaryTree | null): BinaryTree | null {
    if (root === null) return null;
    let iterator = root;

    while (iterator.right !== null) {
        iterator = iterator.right;
    }

    return iterator;
}