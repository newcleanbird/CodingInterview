package Chap3;

import java.util.Arrays;
import java.util.function.Predicate;

public class ReOrderArray {
    public void reOrderArray(int[] array, Predicate<Integer> p) {
        if (array == null || array.length == 0) {
            return;
        }
        int pBegin = 0;
        int pEnd = array.length - 1;
        while (pBegin < pEnd) {
            // 左到右找出第一个偶数
            while (pBegin < pEnd && p.test(array[pBegin]))
                pBegin++;
            // 右到左找出第一个奇数
            while (pBegin < pEnd && !p.test(array[pEnd]))
                pEnd--;
		   // 交换两个奇数和偶数，奇数被换到了前面，偶数换到了后面
            if (pBegin < pEnd) {
                int temp = array[pBegin];
                array[pBegin] = array[pEnd];
                array[pEnd] = temp;
            }
        }
    }

    public static void main(String[] args) {
        ReOrderArray reOrder = new ReOrderArray();
        int[] a = {3, 2, 1, 9, 8, 7, 4, 5, 6};
        reOrder.reOrderArray(a, p -> (p & 1) == 1);
        System.out.println(Arrays.toString(a));
    }
}
