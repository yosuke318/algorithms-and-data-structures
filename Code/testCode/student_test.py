import random

class Student:
    def __init__(self, studentId, grade, name, age, height):
        self.studentId = studentId
        self.grade = grade
        self.name = name
        self.age = age
        self.height = height

studentList1 = [
    Student(1000,9,"Matt Verdict", 14, 5.5),
    Student(1001,9,"Amy Lam", 14, 5.5),
    Student(1002,10,"Bryant Gonzales", 15, 5.9),
    Student(1003,9,"Kimberly York", 15, 5.3),
    Student(1004,11,"Christine Bryant", 15, 5.8),
    Student(1005,10,"Mike Allen", 16, 6.2),
]

studentList2 = [
    Student(1000,9,"Matt Verdict", 14, 5.5),
    Student(1001,9,"Amy Lam", 13, 5.5),
    Student(1002,10,"Bryant Gonzales", 15, 5.9),
    Student(1003,9,"Kimberly York", 15, 5.3),
    Student(1004,11,"Christine Bryant", 15, 5.8),
    Student(1005,10,"Mike Allen", 16, 6.2),
]

# 学生のリストが与えられるので、最年少で最も身長の高い学生を返す、関数を作成してください。
# もし、複数該当する場合は若いIDを持つ学生を優先してください。
def chooseStudent(studentList):

    min_age = studentList[0].age
    min_age_student = []
    min_age_student.append(studentList[0])
    for obj in studentList[1:]:
        if min_age > obj.age:
            min_age_student.clear()
            min_age_student.append(obj)
        elif min_age == obj.age:
            min_age_student.append(obj)
        else:
            continue

    if len(min_age_student) == 1:
        return min_age_student[0]
    
    max_height = min_age_student[0].height
    max_height_student = min_age_student[0]
    for obj in min_age_student[1:]:
        if max_height < obj.height:
            max_height_student = obj
            
    return max_height_student


print(chooseStudent(studentList1).studentId == 1000)
print(chooseStudent(studentList2).studentId == 1001) 
# テストケースを以下作成してください。