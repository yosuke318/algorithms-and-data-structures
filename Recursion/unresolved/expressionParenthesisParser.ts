/*
文字列で表現された数式 expression が与えられるので、その数式を評価し、整数の結果を返す、expressionParser という関数を作成してください。ただし、割り算に関しては小数点以下を切り捨てた整数値を返してください。

関数の入出力例

入力のデータ型： string expression

出力のデータ型： long

expressionParser("2+4*6") --> 26

expressionParser("2*3+4") --> 10

expressionParser("3-3+3") --> 3

expressionParser("2+2+2") --> 6

expressionParser("1-1-1") --> -1

expressionParser("3*3/3*3*3") --> 27

expressionParser("14/3*2") --> 8

expressionParser("12/3*4") --> 16

expressionParser("1+2+3+4+5+6+7+8+9+10") --> 55

expressionParser("1+2*5/3+6/4*2") --> 6

expressionParser("42") --> 42

expressionParser("7*3622*636*2910*183+343/2926/1026") --> 8587122934320
*/