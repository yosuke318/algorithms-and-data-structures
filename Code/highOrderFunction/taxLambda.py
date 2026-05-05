# 連邦の税金を表すグローバル変数を定義します。これは全ての計算に影響を与えます。
federalTaxes = 0.2;

# taxLambdaという関数を定義します。この関数は他の関数（ラムダ関数）を返します。
# 引数として州の税金(stateTax)と州名(state)を受け取ります。
def taxLambda(stateTax, state):
    # この内部関数fは所得(income)を引数として、連邦税金と州税金を引いた後の所得を計算して返します。
    def f(income):
        # 連邦税金（グローバル変数）と引数で指定した州税金を合算します。
        taxes = federalTaxes + stateTax
        # 現在の計算がどの州に対して行われているかを表示します。
        print("Computing taxes for state..." + state)
        # 合算した税金を所得から引いて、税金控除後の金額を返します。
        return int(income - (taxes * income));
    # 内部関数fを返します。これにより、taxLambda関数を通じて独自の税金計算関数を生成できます。
    return f

# taxLambda関数を使って各州の税金計算関数を生成します。これらの関数は独立して動作し、それぞれの州の税率を適用します。
# カリフォルニア州の税金計算関数
californiaF = taxLambda(0.0725, "California")
# テキサス州の税金計算関数
texasF = taxLambda(0.0625, "Texas")
# ハワイ州の税金計算関数
hawaiiF = taxLambda(0.04, "Hawaii")

# 所得を設定し、それぞれの税金計算関数を使って税金控除後の所得を計算します。
income = 40000
print("Calculating income using lambdas")
# カリフォルニア州の税金計算
print(californiaF(income))
# テキサス州の税金計算
print(texasF(income))
 # ハワイ州の税金計算
print(hawaiiF(income))

# このように、異なる所得や税率に対応する関数を一度定義してしまえば、税金計算を再利用して効率的に計算を行うことが可能になります。
income2 = 500000;
print("------Calculating more income using lambdas------")
# カリフォルニア州の税金計算
print(californiaF(income2))
# テキサス州の税金計算
print(texasF(income2))
# ハワイ州の税金計算
print(hawaiiF(income2))