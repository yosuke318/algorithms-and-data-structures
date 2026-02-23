 /*
 Steven は過去のデータを分析して、ROI を最大化しようとしているトレードアナリストです。各日の株価を表す整数の配列 stocks が与えられるので、各日の i 日から何日前まで連続でその i 日の価格より高いかを配列で返す、stockSpan という関数を作成してください。ただし、株価の上昇には同じ日を含みます。

例えば、[1,2,5] は、[1,2,3] を返します。1 日目は 1、2 日目は 1、2 と 2 連続、3 日目は 1, 2, 5 と 3 連続で株価が上昇しているからです。

関数の入出力例

入力のデータ型： integer[] stocks

出力のデータ型： integer[]

stockSpan([30,50,60,20,30,64,80]) --> [1,2,3,1,2,6,7]

stockSpan([24,5,67,60,24,64,23,536,345]) --> [1,1,3,1,1,3,1,8,1]

stockSpan([200,85,40,60,40,65,90]) --> [1,1,1,2,1,4,6]

stockSpan([30,45,20,100,235,300,4500,40,100]) --> [1,2,1,4,5,6,7,1,2]

stockSpan([34,640,100,234,56,34,25,200,1020,160]) --> [1,2,1,2,1,1,1,4,9,1]
 */
function stockSpan(stocks:number[]): number[]{
    const result:number[] = [1];
    let maxNum = stocks[0];
    for(let i = 1; i < stocks.length; i++){
        if (maxNum <= stocks[i]){
            maxNum = stocks[i];
            result.push(i + 1)
        } else {
            let count = 0
            for (let j = i-1; j >= 0; j--){
                count++;
                if (stocks[i] < stocks[j]) break;
            }
            result.push(count);
        }
    }
    return result
}
