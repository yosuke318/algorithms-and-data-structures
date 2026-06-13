import re # 正規表現を使うため re モジュールをimportします。

# ドルから円への換算レート
USDtoJPY = 138.31

# ベースとなる給与と時間給を元に、給与計算を行う関数を定義します。
def calcDollarSalary(baseSalary, hourWage):
   salary = baseSalary # 給与の初期値はベース給与

   def getSalary(): # 現在の給与を取得する関数
      nonlocal salary
      return "$" + str(salary) # 給与の額面を文字列として返す

   def work(time): # 働いた時間に基づいて給与を増やす関数
      nonlocal salary
      salary += hourWage * time # 時間給 * 働いた時間だけ給与を増やす

   return { # 2つの関数をオブジェクトとして返す
      "getSalary": getSalary,
      "work": work
   }

# calcDollarSalaryを使用して給与計算を行います。ベース給与は100ドル、時間給は12ドルで設定します。
mikeSalary = calcDollarSalary(100, 12)
mikeSalary["work"](10) # 10時間働く
mikeSalary["work"](3)  # さらに3時間働く
print("Mike's income: " + mikeSalary["getSalary"]()) # 給与の合計を表示

# ドルから円への変換を行うデコレータ関数を定義します。
def changeToYenUnit(f) :
   def inner() :
      global USDtoJPY
      moneyWithUnit = f() # 元の関数を呼び出してドル単位の給与を取得
      # 小数点付き数値を取得する正規表現を使用し、数値に変換します。
      result = re.findall(r'\d+.?\d*', moneyWithUnit)[0]
      yenUnitMoney = float(result) * USDtoJPY # ドルから円に変換

      return "¥" + str(yenUnitMoney) # 円単位の給与を文字列として返す
   return inner

# Ryoの給与計算も同様に行います。ベース給与は80ドル、時間給は10ドルで設定します。
ryoSalary = calcDollarSalary(80, 10)
ryoSalary["work"](10) # 10時間働く
ryoSalary["work"](6)  # さらに6時間働く
print("Ryo's income: " + ryoSalary["getSalary"]()) # 給与の合計を表示

# getSalary関数を円へ変換する関数でデコレートします。
getRyoSalaryYen = changeToYenUnit(ryoSalary["getSalary"])
# ["getSalary"]) # getSalary関数を円に変換する関数でデコレート
# このように円に変換された値を出力することができます。
print("Ryo's income(yen): " + getRyoSalaryYen())

print("------------")

# calcDollarSalary関数自体を変更したわけではないため、引き続きcalcDollarSalaryは使用できます。
mikeSalary["work"](19) # Mikeがさらに19時間働く
ryoSalary["work"](28)  # Ryoがさらに28時間働く

# 働いた後の給与を表示
print("Mike's income: " + mikeSalary["getSalary"]()) # ドル単位の給与を表示
print("Ryo's income: " + ryoSalary["getSalary"]()) # ドル単位の給与を表示

# Ryoの給与を円単位で表示
print("Ryo's income(yen): " + getRyoSalaryYen()) # 円単位の給与を表示