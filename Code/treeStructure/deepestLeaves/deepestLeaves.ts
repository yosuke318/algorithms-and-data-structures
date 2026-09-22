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
 * 二分木の最も深い位置にある葉ノードの値の合計を計算する
 * @param root - 二分木のルートノード
 * @returns 最深の葉ノードの値の合計
 */
function deepestLeaves(root: BinaryTree<number> | null): number {
    if (root === null) return 0;

    // 1回のDFSで最大深さと最深の葉ノードの合計を同時に計算
    const result = { maxDepth: 0, sum: 0 };
    dfsHelper(root, 0, result);

    console.log("最大深さ：", result.maxDepth);
    console.log("合計：", result.sum);

    return result.sum;
}

/**
 * DFSで木を走査し、最大深さと最深の葉ノードの合計を同時に計算
 * @param node - 現在探索中のノード
 * @param depth - 現在の深さ
 * @param result - 最大深さと合計を保持するオブジェクト
 */
function dfsHelper(
    node: BinaryTree<number> | null,
    depth: number,
    result: { maxDepth: number; sum: number }
): void {
    if (node === null) return;

    // 葉ノードの場合
    if (node.left === null && node.right === null) {
        if (depth > result.maxDepth) {
            // より深い葉を発見した場合、合計をリセット
            result.maxDepth = depth;
            result.sum = node.data;
        } else if (depth === result.maxDepth) {
            // 同じ深さの葉を発見した場合、合計に加算
            result.sum += node.data;
        }
        return;
    }

    // 子ノードを再帰的に探索
    dfsHelper(node.left, depth + 1, result);
    dfsHelper(node.right, depth + 1, result);
}
