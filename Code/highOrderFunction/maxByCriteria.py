compareLength = lambda s1, s2: len(s1) >= len(s2)

def compareAsciiTotal(s1, s2):

    s1_sum = sum([ord(i) for i in s1])

    s2_sum = sum([ord(i) for i in s2])
    # print(f"{s1}: ",s1_sum)

    return s1_sum >= s2_sum


def maxByCriteria(f, str_list):

    max_str = str_list[0]

    for i in range(1, len(str_list)):
        if f(str_list[i], max_str):
            max_str = str_list[i]

    return max_str

            

print(maxByCriteria(compareLength, ["apple", "yumberry", "grape", "banana","mandarin"]))

print(maxByCriteria(compareLength, ["zoomzoom", "choochoo", "beepbeep", "ahhhahhh"]))

print(maxByCriteria(compareAsciiTotal, ["apple", "yumberry", "grape", "banana","mandarin"]))

print(maxByCriteria(compareAsciiTotal, ["zoom", "choochoo", "beepbeep", "ahhhahhh"]))