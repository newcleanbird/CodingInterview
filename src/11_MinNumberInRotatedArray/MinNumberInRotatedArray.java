// 顺序查找
public int minNumberInRotateArray(int [] array) {
  	if (array.length == 0) {
    	return 0;
  	}

  	for (int i = 0;i < array.length - 1;i++) {
    	if (array[i] > array[i + 1]) {
      		return array[i + 1];
    	}
  	}
  	return array[0];
}

// 二分查找
public class MinNumberInRotateArray {

    public int minNumberInRotateArray(int [] array) {
        if (array==null || array.length == 0) {
            return 0;
        }

        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
          	// 此时mid一定在左半数组中，最小值在mid的右边
            if (array[mid] > array[high]) {
                low = mid + 1;
              // 此时mid一定在右半数组中，最小值在mid的左边或就是mid本身
            } else if (array[mid] < array[high]) {
                high = mid;
              // 暂时放弃二分查找，和前一个字符继续比较
            } else {
                high--;
            }
        }
      	// low == high时推出循环并返回
        return array[low];
    }
}
