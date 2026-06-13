def over100m(l):
    # 関数を完成させてください
    converted = [convert_meter(i) for i in l]
    return list(filter(lambda i: i >= 100, converted))

def convert_meter(d: str):
    if d[-2] == "k":
        return float(d[:-2]) * 1000
    elif d[-2] == "m":
        return float(d[:-2]) / 1000

    return float(d[:-1])
