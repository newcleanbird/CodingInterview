package Chap2;

import java.util.Arrays;

public class FindDuplicate {
    /**
     * 推荐的做法，通过交换元素，将值i保存到numbers[i]
     * 在numbers[i]不和i相等时，如果numbers[i]和numbers[numbers[i]]相等就说明重复元素；
     * 否则就交换这两个元素，这个过程相当于排序。举个例子，通过交换将2放入numbers[2]。

     * @param numbers 待查找的数组
     * @param length 数组的长度，其实就是numbers.length
     * @param duplication 用于保存重复数字，第一个被找到的重复数字存放在duplication[0]中
     * @return 如果在数组中有重复元素
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || length <= 0) {
            return false;
        }
        for (int i = 0;i < length;i++){
            if (numbers[i] < 0 || numbers[i] > length -1) {
                return false;
            }
        }

        for (int i = 0; i< length; i++) {
            while (numbers[i] != i) {
                // 现在numbers[i] != i ，设numbers[i] = j,所以如果下面的if成立,就是numbers[i] == numbers[j],说明找到 重复
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, i, numbers[i]);
            }
        }
        return false;
    }
    // 交换numbers[i]和numbers[numbers[i]]
    private void swap(int[] numbers, int p, int q) {
        int temp = numbers[p];
        numbers[p] = numbers[q];
        numbers[q] = temp;
    }
}
