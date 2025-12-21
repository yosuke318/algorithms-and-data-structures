function isParenthesesValid(parentheses:string): boolean{
    const closedHashMap: {[key: string]: string} = {'}': "{", ']': "[", ')': "("}
    const closedParenthes = ["}", "]", ")"];
    const openedParenthes = ["{", "[", "("];
    let stack = [];

    for (let i = 0; i < parentheses.length; i ++){
        if(openedParenthes.includes(parentheses[i])){
            stack.push(parentheses[i]);
        }
        if(closedParenthes.includes(parentheses[i]))
            if(closedHashMap[parentheses[i]] === stack[stack.length - 1]){
                stack.pop()
                continue
            } else {
                return false;
            }
        }

    return stack.length === 0
}

