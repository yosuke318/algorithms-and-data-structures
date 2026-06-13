shoppingList = [
   {
      "name" : "Apple",
      "price" : 100,
      "quantity" : 10
   },
   {
      "name" : "Orange",
      "price" : 120,
      "quantity" : 8
   },
   {
      "name": "Banana",
      "price": 80,
      "quantity" : 14
   }
]

# reduce関数を使用して、商品リスト全体の合計コストを計算します。
# この関数は、各商品の価格と数量の積を合計に加えていきます（初期値は0）。
totalCost = reduce(lambda total, item : total + item["price"] * item["quantity"] , shoppingList, 0);
print(totalCost);