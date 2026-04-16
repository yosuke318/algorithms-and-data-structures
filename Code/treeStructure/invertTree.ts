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
 * 幅優先探索(BFS)を用いて二分木を反転する
 * キューを使用してレベル順に各ノードの左右の子を入れ替える
 * @param root - 反転する二分木のルートノード
 * @returns 反転された二分木のルートノード
 */
function invertTreeBFS(root: BinaryTree<number> | null): BinaryTree<number> | null {
    // ベースケース: 空の木の場合はnullを返す
    if (root === null) return null

    // ルートノードを保持
    let iterator = root
    // キューにルートノードを追加してBFS開始
    let queue = [iterator];
    
    // キューが空になるまで処理を続ける
    while(queue.length > 0){
        // キューの先頭要素を取り出す
        let iterator = queue.shift();
        if(iterator) {
            // 現在のノードの左右の子を入れ替える
            let tempNode = iterator.left;
            iterator.left = iterator.right;
            iterator.right = tempNode;
            
            // 左の子が存在すれば、キューに追加
            if(iterator.left) queue.push(iterator.left);
            // 右の子が存在すれば、キューに追加
            if(iterator.right) queue.push(iterator.right);
        }
    }

    // 反転された木のルートを返す
    return iterator;
}  


/**
 * 深さ優先探索(DFS)を用いて二分木を反転する
 * 再帰的に各ノードの左右の子を入れ替える
 * @param root - 反転する二分木のルートノード
 * @returns 反転された二分木のルートノード
 */
function invertTreeDFS(root: BinaryTree<number> | null): BinaryTree<number> | null {
    // ベースケース: 空のノードの場合はnullを返す
    if (root == null) return null;
    
    // 現在のノードの左右の子を入れ替える
    let temp = root.left;
    root.left = root.right;
    root.right = temp;

    // 右の部分木を再帰的に反転(元の左の子)
    invertTreeDFS(root.right);
    // 左の部分木を再帰的に反転(元の右の子)
    invertTreeDFS(root.left);

    // 反転されたノードを返す
    return root;
}