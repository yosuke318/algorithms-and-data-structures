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

        //update the tail
        if(this.tail != null) this.tail.next = null;
        else this.head = null;
        return temp.data;
    }
}

// ここから開発しましょう。
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


console.log(getMaxWindows([34,35,64,34,10,2,14,5,353,23,35,63,23], 4)); //[64, 64, 64, 34, 14, 353, 353, 353, 353, 63]