/**
 * 
 *あなたは今ジムの建設チームのエンジニアです。今連結したビルのような建物の中にジムを建設しようとしています。高さを表す配列 h がヒストグラムとして与えられるので、ヒストグラム内に描くことのできる長方形の最大面積を返す、largestRectangle という関数を作成してください。

入力のデータ型： integer[] h

出力のデータ型： integer

largestRectangle([3,2,3]) --> 6

largestRectangle([1,2,5,2,3,4]) --> 10

largestRectangle([1,2,3,4,5]) --> 9

largestRectangle([3,4,5,8,10,2,1,3,9]) --> 16

largestRectangle([1,2,1,3,5,2,3,4]) --> 10

largestRectangle([11,11,10,10,10]) --> 50

largestRectangle([8979,4570,6436,5083,7780,3269,5400,7579,2324,2116]) --> 26152
 */

function largestRectangle(h:number[]): number{
    let rectangle = 0;
    let maxRectangle = 0
    
    for(let i = 0; i < h.length; i++){
        let width = 1;
        let j = i
        while(j + 1 <= h.length && h[i] <= h[j + 1]){
            width += 1;
            j++;
        }
        let k = i;
        while(k >= 0 && h[i] <= h[k - 1]){
            width += 1;
            k--;
        }
        rectangle = width * h[i];
        if(maxRectangle < rectangle) maxRectangle = rectangle;
    }

    return maxRectangle;

}