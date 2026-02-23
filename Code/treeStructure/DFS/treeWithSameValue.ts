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
 * 二分木の全てのノードが同じ値を持つかどうかを判定する
 * @param root - 判定する二分木のルートノード
 * @returns 全てのノードが同じ値の場合true、そうでない場合false
 */
function treeWithTheSameValue(root:BinaryTree<number> | null): boolean{
    let node = root;
    // ルートノードの値を基準値として保存
    const target = node.data;

    // DFSで全ノードが基準値と同じかチェック
    return dfs(node, target)
}

/**
 * 深さ優先探索(DFS)で全ノードが指定された値と一致するかを再帰的に判定する
 * @param node - 現在探索中のノード
 * @param target - 比較対象の基準値
 * @returns 現在のノード以下の全てが基準値と一致すればtrue
 */
function dfs(node: BinaryTree<number> | null, target: number):boolean {
    // ベースケース: nullノードは条件を満たすとみなす
    if(node === null) return true;
    
    // 現在のノードの値が基準値と異なる場合はfalse
    if(node.data !== target) return false;

    // 左右の部分木も全て基準値と一致するかを再帰的にチェック
    return dfs(node.left, target) && dfs(node.right, target);
}
