function getMaxWindows(arr: number[], k: number): number[] {
    // arrの要素がkより少ない場合、空の配列を返す
    if (k > arr.length) return [];

    // 最大値を保持するための配列
    let results = [];
    // 値のインデックスを保持するデキュー
    let deque = [];

    // デキューの初期化
    for (let i = 0; i < k; i++) {
        // デキューの末尾から比較し、新しい値(arr[i])より小さいまたは等しい値をデキューから削除する。
        // これにより、デキューの末尾は新しい値よりも大きい値が保証されます。
        while (deque.length > 0 && arr[deque[deque.length - 1]] <= arr[i]) {
            deque.pop();
        }
        // 現在のインデックス(i)をデキューの末尾に追加
        deque.push(i);
    }

    // ウィンドウをスライドさせていく
    for (let i = k; i < arr.length; i++) {
        // デキューの先頭にあるインデックスの値は現在のウィンドウでの最大値
        results.push(arr[deque[0]]);
        // ウィンドウから出た要素のインデックスがデキューの先頭にある場合、それを削除する
        while (deque.length > 0 && deque[0] <= i - k) {
            deque.shift();
        }
        // デキューの末尾から比較し、現在の値(arr[i])より小さいまたは等しい値をデキューから削除する
        while (deque.length > 0 && arr[deque[deque.length - 1]] <= arr[i]) {
            deque.pop();
        }
        // 現在のインデックス(i)をデキューの末尾に追加
        deque.push(i);
    }

    // 最後のウィンドウの最大値を追加
    results.push(arr[deque[0]]);

    return results;
}