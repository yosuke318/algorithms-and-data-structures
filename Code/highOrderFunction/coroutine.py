# asyncioはPythonの標準ライブラリであり、非同期I/Oをサポートしています。
# これにより、ネットワークとの通信やディスクへの書き込みなど、時間のかかる操作を並列に実行することができます。
import asyncio 

# randomモジュールは、様々なランダム値を生成する関数を提供しています。
import random

# 0からmaxまでのランダムな整数を生成する関数
def getRandomInt(max):
   return random.randint(0, max)

# asyncキーワードを使用して、非同期の関数（コルーチン）を定義します。
# この関数はタスク番号(n)と遅延時間(delay)を引数に取ります。
async def printTaskStatus(n, delay):
   # awaitは非同期操作を待つためのキーワードで、この行で時間のかかる操作（この場合はsleep）が行われます。
   # asyncio.sleepは指定した時間だけ処理を中断（スリープ）する関数です。
   await asyncio.sleep(delay) 
   # タスクが完了したことを表示します。
   print(f"{n}. processing completed.")


async def asyncFnProcessing():
   coList = [] # 各コルーチンを保存するためのリストを作成します。
   for n in range(10): # 0から9までの10回ループします。
      t = getRandomInt(5) # ランダムな整数を生成します。
      # コルーチンを作成しますが、まだ実行は開始されません。
      # 各コルーチンは、タスクの番号とランダムに生成した遅延時間を引数に持ちます。
      c = printTaskStatus(n, t/10)
      coList.append(c) # 作成したコルーチンをリストに追加します。

   # asyncio.gatherは複数のコルーチンを並行に実行します。
   # コルーチンのリストを*演算子で展開して引数に渡します。
   await asyncio.gather(*coList)

   # 全てのコルーチンが完了したことを表示します。
   print("All processing Done!")

# asyncio.runは新たなイベントループを作成し、引数のコルーチンを実行します。
# このコードでは、asyncFnProcessingコルーチンが実行されます。
asyncio.run(asyncFnProcessing())