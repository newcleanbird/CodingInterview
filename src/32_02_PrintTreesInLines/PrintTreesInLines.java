package Chap4;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeEveryLayer {
    public void printEveryLayer(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 下一层的结点数
        int nextLevel = 0;
        // 本层还有多少结点未打印,因为目前只有根结点没被打印所以初始化为1
        int toBePrinted = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            // 每打印一个，减1
            toBePrinted--;

            if (node.left != null) {
                queue.offer(node.left);
              	// 每一个元素加入队列，加1
                nextLevel++;
            }

            if (node.right != null) {
                queue.offer(node.right);
                nextLevel++;
            }
		   // 本层打印完毕
            if (toBePrinted == 0) {
                System.out.println();
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }
    }

}
