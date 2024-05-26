package Chap2;

// 迭代法
public class Fibonacci {
    /**
     * 推荐迭代法
     */
    public int fib(int n) {
        if (n <= 0) {
            return 0;
        }

        int a = 0;
        int b = 1;
        while (n > 0) {
            b = a + b;
            a = b - a; // a + b -a -> a = b也就是原来的b赋值给a
            n--;
        }
        return a;
    }
}

// 递归法，时空开销较大，不推荐。
public int fib2(int n) {
  	if (n <= 0) {
    	return 0;
  	}

  	if (n == 1) {
    	return 1;
  	}

  	return fib2(n-1) +fib(n-2);
}

