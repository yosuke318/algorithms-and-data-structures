import decimal
def formatDecimal(num):
    # round関数は、バンカーズラウンディングを使用します。
    result = decimal.Decimal(str(num)).quantize(decimal.Decimal('.01'), decimal.ROUND_HALF_UP) * 100
    print(f"rounding {num}....{result}")
    return result

assert(formatDecimal(86.258) == 8626)
assert(formatDecimal(86.253) == 8625)


# 四捨五入が目標です。
# しかし、Pythonのround関数は、バンカーズラウンディングを使用します。
assert(formatDecimal(20.355) == 2036)
assert(formatDecimal(20.345) == 2035) # Assertion Error
assert(formatDecimal(54.075) == 5408) 
assert(formatDecimal(54.065) == 5407) # Assertion Error

assert(formatDecimal(54.775) == 5478) # Assertion Error バンカーズラウンディングで5478になるにもかかわらずエラーになります。これはバグではなく、float型の制限によるものです。