class Node{
    constructor(data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class Deque{
    constructor(){
        this.head = null;
        this.tail = null;
    }

    peekFront(){
        if(this.head == null) return null;
        return this.head.data;
    }

    peekBack(){
        if(this.tail == null) return null;
        return this.tail.data;
    }

    enqueueFront(data){
        if(this.head == null){
            this.head = new Node(data);
            this.tail = this.head;
        }
        else{
            let node = new Node(data);
            this.head.prev = node;
            node.next = this.head;
            this.head = node;
        }
    }

    enqueueBack(data){
        if(this.head == null){
            this.head = new Node(data);
            this.tail = this.head;
        }
        else{
            let node = new Node(data);
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
    }

    dequeueFront(){
        if(this.head == null) return null;

        let temp = this.head;
        this.head = this.head.next;
        if(this.head != null) this.head.prev = null;
        else this.tail = null;
        return temp.data;
    }

    dequeueBack(){
        if(this.tail == null) return null;

        let temp = this.tail;
        this.tail = this.tail.prev;

        if(this.tail != null) this.tail.next = null;
        else this.head = null;
        return temp.data;
    }
}

// スライディングウィンドウの最大値を取得
const getMaxWindows = (arr, k) => {
    if (k > arr.length) return [];

    let results = [];
    const deque = new Deque();

    for (let i = 0; i < k; i++){
        while (deque.head !== null && arr[deque.tail.data] <= arr[i]){
            deque.dequeueBack();
        }
        deque.enqueueBack(i);
    }

    for (let i = k; i < arr.length; i++){
        results.push(arr[deque.head.data]);

        while (deque.head !== null && deque.head.data <= i - k) {
            deque.dequeueFront();
        }
        while (deque.head !== null && arr[deque.tail.data] <= arr[i]){
            deque.dequeueBack();
        }
        deque.enqueueBack(i);
    }

    results.push(arr[deque.head.data]);
    return results;
}

// スライディングウィンドウの最小値を取得
const getMinWindows = (arr, k) => {
    if (k > arr.length) return [];

    let results = [];
    const deque = new Deque();

    for (let i = 0; i < k; i++){
        while (deque.head !== null && arr[deque.tail.data] >= arr[i]){
            deque.dequeueBack();
        }
        deque.enqueueBack(i);
    }

    for (let i = k; i < arr.length; i++){
        results.push(arr[deque.head.data]);

        while (deque.head !== null && deque.head.data <= i - k) {
            deque.dequeueFront();
        }
        while (deque.head !== null && arr[deque.tail.data] >= arr[i]){
            deque.dequeueBack();
        }
        deque.enqueueBack(i);
    }

    results.push(arr[deque.head.data]);
    return results;
}

// ==================== 株価分析アプリケーション ====================

/**
 * ブレイクアウト検出: 現在価格が過去N日の最高値を更新したポイントを検出
 * @param {number[]} prices - 株価データ
 * @param {number} windowSize - 監視期間(日数)
 * @returns {Object[]} ブレイクアウトポイントの配列
 */
const detectBreakout = (prices, windowSize) => {
    const movingHighs = getMaxWindows(prices, windowSize);
    const breakouts = [];

    for (let i = 0; i < movingHighs.length; i++){
        const currentPrice = prices[i + windowSize];
        const previousHigh = movingHighs[i];
        
        // 現在価格が過去の最高値を超えた場合
        if (currentPrice > previousHigh){
            breakouts.push({
                day: i + windowSize,
                price: currentPrice,
                previousHigh: previousHigh,
                increase: ((currentPrice - previousHigh) / previousHigh * 100).toFixed(2) + '%'
            });
        }
    }

    return breakouts;
}

/**
 * ドローダウン計算: 過去最高値からの下落率を計算
 * @param {number[]} prices - 株価データ
 * @param {number} windowSize - 監視期間(日数)
 * @returns {Object[]} 各時点でのドローダウン情報
 */
const calculateDrawdown = (prices, windowSize) => {
    const movingHighs = getMaxWindows(prices, windowSize);
    const drawdowns = [];

    for (let i = 0; i < movingHighs.length; i++){
        const currentPrice = prices[i + windowSize];
        const recentHigh = movingHighs[i];
        const drawdownPercent = ((currentPrice - recentHigh) / recentHigh * 100).toFixed(2);
        
        drawdowns.push({
            day: i + windowSize,
            currentPrice: currentPrice,
            recentHigh: recentHigh,
            drawdown: drawdownPercent + '%'
        });
    }

    return drawdowns;
}

/**
 * レジスタンス・サポートレベル分析
 * @param {number[]} prices - 株価データ
 * @param {number} windowSize - 監視期間(日数)
 * @returns {Object} レジスタンスとサポートの情報
 */
const analyzeSupportResistance = (prices, windowSize) => {
    const movingHighs = getMaxWindows(prices, windowSize);
    const movingLows = getMinWindows(prices, windowSize);

    const analysis = [];

    for (let i = 0; i < movingHighs.length; i++){
        const currentPrice = prices[i + windowSize];
        const resistance = movingHighs[i];
        const support = movingLows[i];
        const range = resistance - support;
        const position = ((currentPrice - support) / range * 100).toFixed(2);

        analysis.push({
            day: i + windowSize,
            currentPrice: currentPrice,
            resistance: resistance,
            support: support,
            range: range.toFixed(2),
            positionInRange: position + '%' // レンジ内での位置(0%=サポート, 100%=レジスタンス)
        });
    }

    return analysis;
}

/**
 * ボラティリティ分析: 価格変動の激しさを測定
 * @param {number[]} prices - 株価データ
 * @param {number} windowSize - 監視期間(日数)
 * @returns {Object[]} ボラティリティ情報
 */
const analyzeVolatility = (prices, windowSize) => {
    const movingHighs = getMaxWindows(prices, windowSize);
    const movingLows = getMinWindows(prices, windowSize);

    const volatility = [];

    for (let i = 0; i < movingHighs.length; i++){
        const high = movingHighs[i];
        const low = movingLows[i];
        const volatilityPercent = ((high - low) / low * 100).toFixed(2);

        volatility.push({
            day: i + windowSize,
            high: high,
            low: low,
            volatility: volatilityPercent + '%'
        });
    }

    return volatility;
}

/**
 * トレーディングシグナル生成
 * @param {number[]} prices - 株価データ
 * @param {number} windowSize - 監視期間(日数)
 * @returns {Object[]} 買い/売りシグナル
 */
const generateTradingSignals = (prices, windowSize) => {
    const movingHighs = getMaxWindows(prices, windowSize);
    const movingLows = getMinWindows(prices, windowSize);
    const signals = [];

    for (let i = 0; i < movingHighs.length; i++){
        const currentPrice = prices[i + windowSize];
        const resistance = movingHighs[i];
        const support = movingLows[i];

        let signal = 'HOLD';
        let reason = '';

        // ブレイクアウト → 買いシグナル
        if (currentPrice > resistance){
            signal = 'BUY';
            reason = 'レジスタンスブレイクアウト';
        }
        // サポート付近 → 買いチャンス
        else if (currentPrice <= support * 1.02){
            signal = 'BUY';
            reason = 'サポートライン付近';
        }
        // レジスタンス付近 → 売りシグナル
        else if (currentPrice >= resistance * 0.98){
            signal = 'SELL';
            reason = 'レジスタンス付近で利確';
        }

        signals.push({
            day: i + windowSize,
            price: currentPrice,
            signal: signal,
            reason: reason
        });
    }

    return signals;
}

// ==================== デモ実行 ====================

console.log('📊 株価分析デモ\n');

// 架空の株価データ(30日分)
const stockPrices = [
    150, 152, 155, 153, 151, 148, 150, 153, 156, 158,  // Day 0-9
    160, 159, 162, 165, 163, 161, 164, 167, 170, 168,  // Day 10-19
    172, 175, 173, 171, 169, 167, 170, 173, 178, 180   // Day 20-29
];

const windowSize = 5; // 5日間の移動ウィンドウ

console.log('🔍 1. ブレイクアウト検出 (過去5日の最高値更新)');
console.log('─────────────────────────────────────');
const breakouts = detectBreakout(stockPrices, windowSize);
breakouts.forEach(b => {
    console.log(`Day ${b.day}: ¥${b.price} (前回高値: ¥${b.previousHigh}, 上昇率: ${b.increase})`);
});

console.log('\n📉 2. ドローダウン分析 (過去5日の最高値からの下落率)');
console.log('─────────────────────────────────────');
const drawdowns = calculateDrawdown(stockPrices, windowSize);
drawdowns.slice(0, 5).forEach(d => {
    console.log(`Day ${d.day}: 現在価格 ¥${d.currentPrice}, 最高値 ¥${d.recentHigh}, 下落率: ${d.drawdown}`);
});
console.log('...(省略)');

console.log('\n📊 3. サポート・レジスタンス分析');
console.log('─────────────────────────────────────');
const srAnalysis = analyzeSupportResistance(stockPrices, windowSize);
srAnalysis.slice(-5).forEach(a => {
    console.log(`Day ${a.day}: 価格 ¥${a.currentPrice} | サポート ¥${a.support} | レジスタンス ¥${a.resistance} | レンジ位置 ${a.positionInRange}`);
});

console.log('\n📈 4. ボラティリティ分析');
console.log('─────────────────────────────────────');
const volatilityData = analyzeVolatility(stockPrices, windowSize);
volatilityData.slice(-5).forEach(v => {
    console.log(`Day ${v.day}: 高値 ¥${v.high}, 安値 ¥${v.low}, ボラティリティ ${v.volatility}`);
});

console.log('\n🎯 5. トレーディングシグナル');
console.log('─────────────────────────────────────');
const signals = generateTradingSignals(stockPrices, windowSize);
const actionableSignals = signals.filter(s => s.signal !== 'HOLD');
actionableSignals.forEach(s => {
    const emoji = s.signal === 'BUY' ? '🟢' : '🔴';
    console.log(`${emoji} Day ${s.day}: ${s.signal} @ ¥${s.price} - ${s.reason}`);
});

console.log('\n💡 アルゴリズムの利点:');
console.log('  • O(n)の計算量で高速処理');
console.log('  • リアルタイム取引に最適');
console.log('  • 複数銘柄の同時監視が可能');
console.log('  • メモリ効率的(スライディングウィンドウ)');
