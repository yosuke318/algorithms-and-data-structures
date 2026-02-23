/*
ゲームクリエイターの Levy は、ゲーム内で暗号を解かなければ開かない扉を設置しました。その暗号は、アルファベットの I と D のみで構成されています。I は増加（increasing）、D は減少（decreasing）を意味しており、表示された文字列によって答えの数値が変わります。I と D で構成された文字列 code が与えられるので、答えとなる数値を返す getNumber という関数を作成してください。ただし、1 から 9 までの数字のみ使用可能とし、各値は 1 回しか使えないものとします。もし文字数が 8 を超えたら 0 を返してください。

例えば、D の場合、1 回分減少しなければいけません。このような条件を満たす数値のうち最小の値は 21 になります。IIDDD の場合、各値は 1 回のみしか使えないので、増加、減少を考慮すると、最小の値は 126543 になります。

関数の入出力例

入力のデータ型： string code

出力のデータ型： string

getNumber("D") --> 21

getNumber("I") --> 12

getNumber("DD") --> 321

getNumber("IIDDD") --> 126543

getNumber("DDIDDIID") --> 321654798

getNumber("DIIDIDDD") --> 213549876

getNumber("IIIDIDDD") --> 123549876

getNumber("DIIDIDDDIID") --> 0
*/

function getNumber(code: string): string {
    const n = code.length;
    const result: number[] = [];
    const stack: number[] = [];

    if(code.length > 8) return "0";

    for(let i = 0; i <= n; i++){

        stack.push(i + 1);

        // 最後まできた場合か、code[i]がIの場合
        if(i === n || code[i] === 'I'){
            while(stack.length > 0) {
                result.push(stack.pop()!);
            }
        }
    }


    return result.join('');
}

