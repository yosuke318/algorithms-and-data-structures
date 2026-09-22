# algorithms-and-data-structures
Practicing algorithms and data structures with online judges (LeetCode, AtCoder, etc.).
About 90% of it is written by me, not AI.

## ディレクトリ構成

問題ごとに 1 フォルダを切り、その中に解答ファイルを置いています。
同じ問題の別解・改良版は同じフォルダに並べています（`_改良版`、`2`、`_2` などのサフィックス付き）。

```
Code/
├── stackAndQueue/   スタック・キュー・デキュー
├── treeStructure/   二分木・二分探索木・ヒープ
├── highOrderFunction/
├── oop/
├── testCode/
└── unresolved/
```

---

## stackAndQueue

| 問題 | ファイル | 内容 | 手法 |
| --- | --- | --- | --- |
| getMaxWindow | `getMaxWindow.ts` | 長さ `k` のスライディングウィンドウごとの最大値を返す | 単調減少デキュー（インデックスを保持） |
| getMinWindow | `getMinWindow.ts` | 長さ `k` のスライディングウィンドウごとの最小値を返す | 単調増加デキュー（インデックスを保持） |
| increaseDecrease | `increaseDecrease.ts` | `I`（増加）/`D`（減少）の文字列から、1〜9 を 1 回ずつ使った条件を満たす最小の数を返す。8 文字超なら `0` | スタックに数字を積み、`I` か末尾で全部 pop して出力 |
| isParenthesesValid | `isParenthesesValid.ts` | `()` `[]` `{}` の括弧列が正しく対応しているか判定 | 開き括弧をスタックに積み、閉じ括弧と対応表で照合 |
| largestRectangle | `largestRectangle.ts` | ヒストグラム内に描ける長方形の最大面積を返す | 各バーから左右に高さ以上のバーが続く限り広げる（O(n²)） |
| shipmentVolumePackages | `shipmentVolumePackages.ts` | 最小の 2 つの荷物を合算し続け、荷物が 1 つになるまでの合計体積を返す | 最小値を 2 回取り出す処理を再帰で繰り返す |
| | `shipmentVolumePackages_改良版.ts` | 同上 | ループ内で昇順ソートし、先頭 2 つを取り出して合算 |
| stockSpan | `stockSpan.ts` | 各日について、その日の株価以下の日が何日連続しているかを返す | 各日から過去にさかのぼって数える（O(n²)） |
| | `stockSpan_改良版.ts` | 同上 | 単調スタックでインデックスを保持し、pop した日のスパンを加算（O(n)） |
| stockAnalysis | `stockAnalysis.js` | スライディングウィンドウの最大値・最小値を使った株価分析のデモ（ブレイクアウト検出、ドローダウン、サポート/レジスタンス、ボラティリティ、売買シグナル） | 双方向連結リストで自作した `Deque` を使用 |

---

## treeStructure

### 探索・走査

| 問題 | ファイル | 内容 | 手法 |
| --- | --- | --- | --- |
| preOrderWalk | `preOrderWalk.ts` | 二分木を前順（NLR）で出力 | 再帰 DFS |
| postOrderWalk | `postOrderWalk.ts` | 二分木を後順（LRN）で出力 | 再帰 DFS |
| preorderTraversal | `preorderTraversal.ts` | 前順で走査した値を配列で返す | 再帰 DFS |
| allElementedSorted | `allElementedSorted.ts` | 2 つの二分木の全要素をソートした配列で返す | DFS で全要素を集めてからソート |
| deepestLeaves | `deepestLeaves.ts` | 最も深い位置にある葉ノードの値の合計を返す | 1 回の DFS で最大深さと合計を同時に更新 |
| minDepth | `minDepth.py` | 二分木の最小の深さを返す | 再帰 DFS |
| treeWithSameValue | `treeWithSameValue.ts` | 全ノードが同じ値かを判定 | ルートの値を基準に DFS で比較 |
| totalEvenGrandParent | `totalEvenGrandParent.py` | 祖父母ノードの値が偶数であるノードの合計を返す | 親・祖父母ノードを引数で渡しつつ DFS、該当値を配列に集めて `sum` |
| | `totalEvenGrandParent2.py` | 同上 | 親・祖父母の値だけを渡し、部分木の合計を再帰で返す |

### 木の比較・変形

| 問題 | ファイル | 内容 | 手法 |
| --- | --- | --- | --- |
| is_same_tree | `is_same_tree.py` | 2 つの二分木が同じ構造・同じ値か判定 | 左右の部分木を再帰で比較 |
| symmetricBST | `symmetricBST.py` | 二分木が左右対称か判定 | 左優先・右優先の前順走査で値の列を作り比較 |
| isCompleteBinaryTree | `isCompleteBinaryTree.py` | 二分木が完全二分木（全段が埋まっている）か判定 | `deque` を使ったレベル順 BFS |
| invertTree | `invertTree.ts` | 二分木の左右を反転する | BFS（キュー）版と DFS（再帰）版の 2 通り |
| merge_binary_tree | `merge_binary_tree.py` | 2 つの二分木を重ね合わせ、重なったノードの値を足した木を返す | 再帰で左右をマージ（配列から木を作る `build_tree` 付き） |

### 二分探索木（BST）

| 問題 | ファイル | 内容 | 手法 |
| --- | --- | --- | --- |
| minimumNode | `minimumNode.ts` | BST の最小ノードを返す | 左の子をたどるループ |
| | `minimumNode_2.ts` | 同上 | 左の子をたどる再帰 |
| maximumNode | `maximumNode.ts` | BST の最大ノードを返す | 右の子をたどる再帰 |
| predecessor | `predecessor.ts` | 指定キーの前任ノード（中順で 1 つ前）を返す | 左部分木があればその最大値、なければルートから探索して右に進んだ最後のノード |
| successor | `successor.ts` | 指定キーの後任ノード（中順で 1 つ後）を返す | 実装途中（ノード検索 `finedNode` のみ） |
| insertBST | `insertBST.py` | BST にキーを挿入する | 再帰で挿入位置を探す |
| validateBST | `validateBST.py` | 二分木が BST の条件を満たすか判定 | 祖先から受け継いだ下限・上限の範囲で再帰チェック |
| deleteNode | `deleteNode.py` | BST からキーを削除する（クラス版） | `BinarySearchTree` クラスに `transplant` / `findSuccessor` を実装し、子の数で場合分け |
| | `deleteNode2.py` | 同上（関数版） | 同じ考え方を関数として実装し、更新後の root を返す |

### ヒープ

| 問題 | ファイル | 内容 | 手法 |
| --- | --- | --- | --- |
| heap | `heap.py` | 配列から最大ヒープを構築する（`buildMaxHeap`） | 最後の親ノードから根に向かって `maxHeapify` |
| | `heapsort.py` | ヒープソート | 最大ヒープを構築後、根と末尾を入れ替えながら範囲を縮めて `maxHeapify` |
