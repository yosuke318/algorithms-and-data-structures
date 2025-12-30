function minWindowArrK(intArr:number[], k:number): number[]{
    /** Find the minimum values in each sliding window of size k in the array */
    
    const result: number[] = [];
    const deque: number[] = []; // window. manage index of intArr.
    // initialize deque
    for (let i = 0; i < k; i++){
        while(deque.length > 0 && intArr[deque[deque.length - 1]] >= intArr[i]) {
            deque.pop();
        }
        deque.push(i);
    }

    // slide window & compare new data.
    for (let i = k; i < intArr.length; i++){
        result.push(intArr[deque[0]]);
        
        // slide window
        while(deque.length > 0 && deque[0] <= i - k){
            deque.shift();
        }

        // compare new data
        while(deque.length > 0 && intArr[deque[deque.length - 1]] >= intArr[i]){
            deque.pop();
        }

        deque.push(i);
    }

    
    result.push(intArr[deque[0]])
    return result
}


// initialize
// ([2,3,1,1,12,3,10], 2)
// deque = [1] k = 2, i = 2
// deque = [1] k = 2, i = 3 -> i - k = 1 >= deque[0]
