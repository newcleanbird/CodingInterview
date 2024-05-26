package Chap2;

public class RobotMove {
    public int movingCount(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0 || threshold < 0) {
            return 0;
        }

        boolean[] marked = new boolean[rows * cols];
      	// 从(0, 0)处开始
        return move(0, 0, threshold, rows, cols, marked);

    }

    /**
     * 递归方法，每到一个格子就四个方向搜索
     *
     * @param row 当前行
     * @param col 当前列
     * @param threshold 门限值
     * @param rows 总行数
     * @param cols 总列数
     * @param marked 是否访问过
     * @return 当前格子数(等于1)加上4个方向能访问到的格子数的总和
     */
    private int move(int row, int col, int threshold, int rows, int cols, boolean[] marked) {
        int count = 0;
        if (checked(row, col, threshold, rows, cols, marked)) {
            marked[row * cols + col] = true;
		   // 递归对四个方向计数，注意四个方向的搜索不是同时发生，
            count = move(row - 1, col, threshold, rows, cols, marked) +
                    move(row + 1, col, threshold, rows, cols, marked) +
                    move(row, col - 1, threshold, rows, cols, marked) +
                    move(row, col + 1, threshold, rows, cols, marked) + 1;
        }
        return count;
    }

    /**
     * 判断当前格子是否超过门限值，以及边界值的判断
     *
     * @return true如果当前格子满足条件
     */
    private boolean checked(int row, int col, int threshold, int rows, int cols, boolean[] marked) {
        return row >= 0 && row < rows && col >= 0 && col < cols && !marked[row * cols + col] && digitSum(row) + digitSum(col) <= threshold;
    }

    /**
     * 比如数字1234,每位数相加的和将返回10
     * @param number 某数字
     * @return 该数字的数位之和
     */
    private int digitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
