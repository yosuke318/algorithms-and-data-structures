import random
from threading import Timer 

# ランダムな数を取得する関数を定義します。
def getRandom():
   return random.random()

# 最初は"old data"というデータを保持します。
storage = "old data"

# 非同期にデータを取得する関数です。指定したURLにリクエストを送信し、0~1秒でデータを取得します。
# 今回は非同期の挙動を制御するため、コールバック関数を引数として渡します。
def getData(url, callback) :
   # 0と1の間のランダムな数を取得します。これを非同期処理の時間とします。
   t = getRandom()
   print(f'random Number: {t}')

   # Timerに渡すコールバック関数
   # 指定したURLからデータを取得しますが、レスポンスが返るタイミングはランダムです。
   # Timerで模擬的に非同期の処理を行い、データ取得後にコールバック関数を実行します。
   def accessData() :
      print("getting from " + url)
      global storage
      storage = "new data at " + url
      callback()

   Timer(t, accessData).start()

# データを出力（レンダリング）する関数です。
def render() :
   print("rendering " + storage)
 
# 非同期にURLからデータを取得し、取得したデータをrender関数でレンダリングします。
# コールバック関数を使用することで、データ取得後に確実に新しいデータをレンダリングすることができます。
getData("https://recursionist.io/", render)