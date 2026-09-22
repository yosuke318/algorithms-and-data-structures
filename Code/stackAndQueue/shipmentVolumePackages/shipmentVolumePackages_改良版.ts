function shipmentVolumePackagesImprovedVersion(packages:number[]): number{
    /*
    リストを照準にして、先頭の2つを取り出して合計し、その合計を再びリストに追加する。
    これをリストの要素が1つになるまで繰り返す。
    最終的に合計した値を返す。
    例えば、[6,5,6]の場合、
    1. 5と6を取り出して11にし、リストは[6,11]になる。合計は11。
    2. 6と11を取り出して17にし、リストは[17]になる。合計は11 + 17 = 28。
    最終的な合計は28となる。
    これを再帰的に実装することも可能だが、ここではループを使用している。
    */

    let total : number = 0;

    while(packages.length > 1) {
        packages.sort((a,b) => a - b); // 昇順にソート
        const first = packages.shift();
        const second = packages.shift();
        
        if (first === undefined || second === undefined) {
            break;
        }
        
        const newPack : number = first + second;
        total += newPack;

        packages.push(newPack);
    }

    return total;
}