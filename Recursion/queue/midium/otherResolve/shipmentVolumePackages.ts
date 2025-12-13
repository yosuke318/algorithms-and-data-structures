function shipmentVolumePackages(packages:number[]): number{
    // 関数を完成させてください
    let total : number = 0;

    while(packages.length > 1) {
        packages.sort((a,b) => a - b);
        const newPack : number = packages.shift() + packages.shift();
        total += newPack;

        packages.push(newPack);
    }

    return total;
}