class Student:
    def __init__(self, studentId, grade, name, age, height):
        self.studentId = studentId
        self.grade = grade
        self.name = name
        self.age = age
        self.height = height

    def __str__(self):
        return f"ID: {self.studentId}...{self.name}, grade:{self.grade}, age {self.age}, height {self.height}"

studentList1 = [
    Student(1000,9,"Matt Verdict", 14, 5.5),
    Student(1001,9,"Amy Lam", 14, 5.5),
    Student(1002,10,"Bryant Gonzales", 15, 5.9),
    Student(1003,9,"Kimberly York", 15, 5.3),
    Student(1004,11,"Christine Bryant", 15, 5.8),
    Student(1005,10,"Mike Allen", 16, 6.2),
]
# 最年少かつ最も高い生徒をid順に並べると、[1000, 1001, 1002, 1004, 1003, 1005]

studentList2 = [
    Student(1000,9,"Matt Verdict", 14, 5.5),
    Student(1001,9,"Amy Lam", 13, 5.5), # 13歳に変更します
    Student(1002,10,"Bryant Gonzales", 15, 5.9),
    Student(1003,9,"Kimberly York", 15, 5.3),
    Student(1004,11,"Christine Bryant", 15, 5.8),
    Student(1005,10,"Mike Allen", 16, 6.2),
]
# 最年少かつ最も高い生徒をid順に並べると、[1001, 1000, 1002, 1004, 1003, 1005]

def printStudents(students):
    print("----Total students: "+str(len(students))+"----")
    for student in students: print(student)
    print("---END---")

# 最年少かつ最も高い生徒をk人返します。kはオプションでデフォルトは1になります。
def chooseStudent(studentList, k = 1):
    # ラムダの比較
    # s1がs2より若く、背が高いかどうかを返します。もし、同じならs1とs2のIDを比較します。
    def studentCompare(s1, s2):
        if s1.age == s2.age: return s1.studentId < s2.studentId if s1.height == s2.height else s1.height > s2.height
        return s1.age < s2.age

    # studentListをheapifyし、最初のk個の要素をpopします。
    def heapify(l):
        for index in reversed(range(0, len(l)//2)):
            minHeap(l, index)

    def swap(arr, i,j):
        temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp

    def minHeap(l, index):
        lengthL = len(l)
        curr = index
        flag = True
        while flag:
            left = curr * 2 + 1
            right = curr * 2 + 2
            smallest = curr

            if lengthL > left and not studentCompare(l[smallest], l[left]): smallest = left
            if lengthL > right and not studentCompare(l[smallest], l[right]): smallest = right

            if smallest == curr: flag = False
            else: swap(studentList, curr, smallest)

            curr = smallest

    # Heapify studentList
    heapify(studentList)
    results = []
    for i in range(k):
        # minを最後のノードとswapし、削除します。O(1)
        swap(studentList, 0, len(studentList)-1)
        results.append(studentList.pop())

        if len(studentList) > 0: minHeap(studentList, 0)
        else: break
    return results

# リスト1に対してテストを実行します
printStudents(studentList1)
# ブラックボックステスト
print(chooseStudent(studentList1)[0].studentId == 1000)
# 副作用。popにより、リストから一人が減り、idでソートされていた配列もheapifyされてバラバラになりました。
# 関数内のin-placeアルゴリズムによって、入力の配列に影響を与えました。
printStudents(studentList1) 

# リスト2
printStudents(studentList2)
# ブラックボックステスト
print(chooseStudent(studentList2)[0].studentId == 1001)
# 副作用。popにより、リストから一人が減り、idでソートされていた配列もheapifyされてバラバラになりました。
# 関数内のin-placeアルゴリズムによって、入力の配列に影響を与えました。
printStudents(studentList2)


studentList3 = [
    Student(1000,9,"Matt Verdict", 11, 5.5), # 11歳へ変更しました
    Student(1001,9,"Amy Lam", 13, 5.5),
    Student(1002,10,"Bryant Gonzales", 13, 5.5), # 13歳へ変更しました
    Student(1003,9,"Kimberly York", 15, 5.3),
    Student(1004,11,"Christine Bryant", 15, 5.3), # 身長5.3へ変更しました
    Student(1005,10,"Mike Allen", 16, 6.2),
]

# 最年少かつ最も高い生徒をid順に並べると、[1000, 1001, 1002, 1003, 1004, 1005]

printStudents(studentList3)
# リスト3から4人を出力します。
printStudents(chooseStudent(studentList3,4))
# 副作用。Christine BryantとMike Allenしか残っていません。
# 関数内のin-placeアルゴリズムによって、入力の配列に影響を与えました。
printStudents(studentList3)