import random
from threading import Timer 
# Pythonのスレッドを使うために threading モジュールをインポートします

# 0~1の範囲でランダムな実数を取得します
def getRandom():
   return random.random()

# storageは後で更新するための変数で、初期値は"old data"です
storage = "old data"

# 指定したURLからデータを非同期に取得します。取得にかかる時間は0~1秒（ランダム）
def getData(url) :
   t = getRandom()
   print(f'random Number: {t}')

   # Timerに渡すコールバック関数
   # 指定したurlからデータを取得しますが、レスポンスが返るタイミングはランダムです。
   def accessData() :
      print("getting from " + url)
      global storage  # グローバル変数のstorageを更新するため、globalキーワードを使用
      storage = "new data at " + url

   # Timerを使用して非同期処理を実現します。実行までの時間（t秒）と、実行する関数(accessData)を渡します。
   Timer(t, accessData).start()
 
# 現在のstorageの内容を出力します
def render() :
   print("rendering " + storage)
 
# 最初にURLからデータを取得します。
getData("https://recursionist.io/")
 
# そして、現在のstorageの内容を出力します。
render()  
# しかし、この時点ではgetDataの処理が完了していないため、storageはまだ "old data" のままです

# さらに、render関数の実行を0.5秒遅らせても、storageが新しいデータで更新されているかどうかはわかりません。
# 非同期処理の特性により、実際のアプリケーションでは非同期処理の終了を待ってから次の処理に進むように設計することが重要です。
Timer(0.5, render).start()