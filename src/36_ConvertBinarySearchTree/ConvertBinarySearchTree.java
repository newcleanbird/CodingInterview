public static class Node<T> {
    private T data;
    private Node<T> lchild;
    private Node<T> rchild;
    private boolean isleftThread;
    private boolean isRightThread;

  	public Node(T data) {
    	this.data = data;
    	isleftThread = false;
    	isRightThread = false;
  	}
}

// 当前访问结点的前一个结点
private Node<Item> preNode;

public void inOrderThread(Node<Item> node) {
  	if (node == null) {
    	return;
  	}
  	inOrderThread(node.lchild);

  	if (node.lchild == null) {
    	node.lchild = preNode;
    	node.isleftThread = true;
  	}

  	if (preNode != null && preNode.rchild == null) {
    	preNode.rchild = node;
    	preNode.isRightThread = true;
  	}
  	// preNode始终表示上一个访问的结点
  	preNode = node;
  	inOrderThread(node.rchild);
}

public void inOrderThread() {
  inOrderThread(root);
}

/*
而本题要实现双向链表，不光是叶子结点，所有结点的左右指针都要重新设置。因此将上面的限制条件（左右子结点为空才线索化）去掉，就得到本题的答案！
*/

package Chap4;

public class BSTTransToDLinkedList {
    // 当前结点的前驱
    private TreeNode preNode;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }

        TreeNode root = pRootOfTree;
        // 得到双向链表
        inOrder(root);
        // 向左找到双向链表的头结点
        while (root.left != null) {
            root = root.left;
        }
        return root;

    }

    // 中序遍历并改变指针
    private void inOrder(TreeNode node) {
        if (node == null) return;

        inOrder(node.left);

        node.left = preNode;
        if (preNode != null) {
            preNode.right = node;
        }
        preNode = node;

        inOrder(node.right);
    }
}

/*
最后因为需要返回双向链表的头结点，只需从二叉树的根结点开始，向左遍历，即可找到双向链表的头结点。
*/