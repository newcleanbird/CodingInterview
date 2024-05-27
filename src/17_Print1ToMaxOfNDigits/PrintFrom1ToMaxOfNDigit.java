public class PrintFrom1ToMaxOfNDigit 
{
    public void printFrom1ToMaxOfNDigit(int n) {
        if (n <= 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();
      	// 初始化字符串，比如n = 3就初始化为"000"
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }

        while (stillIncrease(sb, n)) {
            print(sb);
        }

        System.out.println();
    }
}

private boolean stillIncrease(StringBuilder sb, int len) 
{
    // 进位,应该要给下一位相加，所以设置在循环外
    int toTen = 0;
    // 从个位开始加，如果有进位就看十位...如果到最高位还有进位，说明溢出；
    for (int i = len - 1; i >= 0; i--) {
        // 加上进位toTen
        int sum = sb.charAt(i) - '0' + toTen;
        // 在个位上，先自增
        if (i == len - 1) {
            sum++;
        }

        if (sum == 10) {
            // 进位溢出
            if (i == 0) {
                return false;
                // 需要进位，当前位设为0
            } else {
                sb.setCharAt(i, '0');
                // 进位了
                toTen = 1;
            }
        } else {
            sb.setCharAt(i, (char) (sum + '0'));
            // 在某位上自增后不再进位，自增完成立即退出循环
            break;
        }
    }
    return true;
}

private void print(StringBuilder sb) 
{
    int start = sb.length();
    // 找到第一个不为0的索引
    for (int i = 0; i < sb.length(); i++) {
        if (sb.charAt(i) != '0') {
            start = i;
            break;
        }
    }
    // 如果全是0，就不会打印
    if (start < sb.length()) {
        System.out.print(sb.substring(start) + " ");
    }
}


/* 全排列 */

public void printFrom1ToMaxOfNDigitRecur(int n) {
  	if (n <= 0) {
    	return;
  	}

  	StringBuilder sb = new StringBuilder();
  	for (int i = 0; i < n; i++) {
    	sb.append("0");
  	}
	// 递归中的方法为了对下一位排列，所以是index + 1，为了对最高位排列应该传入-1
  	printRecur(sb, n, -1);
  	System.out.println();
}

private void printRecur(StringBuilder sb, int len, int index) {
  	if (index == len - 1) {
    	print(sb);
    	return;
  	}

  	for (int i = 0; i < 10; i++) {
    	// 一定是先设置了字符，然后再递归
    	sb.setCharAt(index + 1, (char) (i + '0'));
    	printRecur(sb, len, index + 1);
  	}
}