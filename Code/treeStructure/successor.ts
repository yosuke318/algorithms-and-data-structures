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

const finedNode = (root, key) => {
    let iterator = root;

    while(iterator !== null) {
        if(iterator.data === key) return iterator;
        if(iterator.data < key) iterator = iterator.right;
        else iterator = iterator.left;
    }

    return iterator;
}

