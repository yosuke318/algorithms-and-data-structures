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

/**
 * 2つの二分木の全要素を取得し、ソートされた配列として返す
 * @param root1 - 1つ目の二分木のルートノード
 * @param root2 - 2つ目の二分木のルートノード
 * @returns ソートされた全要素の配列
 */
function allElementsSorted(root1:BinaryTree<number> | null, root2:BinaryTree<number> | null): number[]{
    // どちらかの木がnullの場合は空配列を返す
    if(root1 === null || root2 === null) return [];
    
    let arr: number[] = [];
    
    // 1つ目の木から全要素を取得
    sortedArr(root1, arr);
    
    // 2つ目の木から全要素を取得し、最後にソート
    return sortedArr(root2, arr).sort((a, b) => a - b);
}


/**
 * 二分木を深さ優先探索(DFS)で走査し、全要素を配列に追加する
 * @param root - 走査する二分木のルートノード
 * @param arr - 要素を格納する配列
 * @returns 要素が追加された配列
 */
function sortedArr(root: BinaryTree<number>, arr: number[]): number[]{
    // 現在のノードの値を配列に追加
    if(root !== null) arr.push(root.data);
    
    // 左の子ノードを再帰的に探索
    if(root.left !== null) sortedArr(root.left, arr);
    
    // 右の子ノードを再帰的に探索
    if(root.right !== null) sortedArr(root.right, arr);
    
    // 配列をソートして返す(注: 毎回ソートするのは非効率的)
    return arr.sort((a, b) => a - b);
}

