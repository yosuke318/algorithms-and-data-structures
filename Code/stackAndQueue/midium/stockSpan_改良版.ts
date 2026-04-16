function stockSpan2(stocks:number[]): number[]{
    const result: number[] = [];
    const stack: number[] = []; // 最大の数のindexを保管

    for(let i = 0; i < stocks.length; i++){
        let current = stocks[i];
        let count = 1;

        while(stack.length > 0 && stocks[stack[stack.length - 1]] < current){
            count += result[stack.pop()!];
        }

        result.push(count);
        stack.push(i);
    }

    return result;
}