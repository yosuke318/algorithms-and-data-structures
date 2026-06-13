def chooseStudent(studentList):
    # 年齢昇順 → 身長降順 → ID昇順 でソートして先頭を返す
    return min(studentList, key=lambda s: (s.age, -s.height, s.studentId))