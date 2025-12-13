/*
出荷予定の荷物の体積を表す、正の整数で構成された配列 packages が与えられます。出荷チームは、体積の小さい荷物どうしをパッケージで梱包し、合計体積の新しい荷物を作成します。この新しい荷物を作成するために使われた 2 つの荷物は配列から削除され、代わりに新しい荷物が元の配列に追加されます。このサイクルは荷物が 1 つになるまで続きます。この処理で使われたパッケージの合計を返す、shipmentVolumePackages という関数を作成してください。

例として、[5,3,10,9,4] を考えます。最初に使われるパッケージは、3 + 4 = 7 であり、出荷リストは [5,10,9,7] になります。次のサイクルでは、パッケージは 5 + 7 = 12、出荷リストは [10,9,12] になります。3 周期目のパッケージは 9 + 10 = 19、出荷リストは [12,19] になります。最後のサイクルではパッケージは 12 + 19 = 31、出荷リストは [31] になります。今まで使用した合計のパッケージは、7 + 12 + 19 + 31 = 69 となり、69 を返します。

関数の入出力例

入力のデータ型： integer[] packages

出力のデータ型： integer

shipmentVolumePackages([6,5,6]) --> 28

shipmentVolumePackages([5,3,10,9,4]) --> 69

shipmentVolumePackages([15]) --> 0

shipmentVolumePackages([1,2,3,4,5,6,10]) --> 80

shipmentVolumePackages([5,4,3,2,1]) --> 33

shipmentVolumePackages([45,65,20,3,4,5,66,19,23,3,1]) --> 700
*/

function shipmentVolumePackages(packages:number[]): number{

    if(packages.length === 1) return 0;

    return shipmentVolumePackagesHelper(packages);
    
}

function shipmentVolumePackagesHelper(packages: number[], resultList: number[]= []): number {
    if(packages.length === 1) return resultList.reduce((acc, current) => acc + current, 0);

    let Obj1 = popMin(packages);
    let Obj2 = popMin(Obj1.popedList);
    const sumMins = Obj1.min + Obj2.min;
    resultList.push(sumMins);
    Obj2.popedList.push(sumMins);
    const newList = Obj2.popedList;
    return shipmentVolumePackagesHelper(newList, resultList)
}

function popMin(packages: number[]): { min: number; popedList: number[] } {
    let min = packages[0];
    let minIdx = 0;
    let popedList: number[] = [];

    for (let i = 1; i < packages.length; i++) {
        if (min > packages[i]) {
        min = packages[i];
        minIdx = i;
        }
    }

    for (let j = 0; j < packages.length; j++) {
        if (minIdx === j) continue;
        popedList.push(packages[j]);
    }

    return { min:min, popedList: popedList };
}


