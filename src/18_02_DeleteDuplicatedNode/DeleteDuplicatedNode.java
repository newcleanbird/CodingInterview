package Chap3;

public class DeleteDuplicationNode {

    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication_2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        // 当前结点的前一个结点
        ListNode pre = null;
        // 当前结点
        ListNode cur = pHead;
        while (cur != null && cur.next != null) {
            // 如果当前结点和下一个结点值相同
            if (cur.val == cur.next.val) {
                int val = cur.val;
                // 跳过所有和cur相同的值
                while (cur != null && (cur.val == val)) {
                    cur = cur.next;
                }
                // 跳出循环得到的是第一个和cur.val不同的结点
                // pre为空说明头结点就是重复结点，因此需要重新设置头结点
                if (pre == null) pHead = cur;
                    // 否则cur之前的pre的下一个结点何cur连接
                else pre.next = cur;
                // 不相等就像前推进，更新cur和pre
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return pHead;
    }
}

// 增加头节点

public ListNode deleteDuplication(ListNode pHead) {
    if (pHead == null || pHead.next == null) {
        return pHead;
    }
    // 建立一个头结点代替原来的pHead
    ListNode first = new ListNode(pHead.val - 1);
    first.next = pHead;
    // 当前结点的前一个结点
    ListNode pre = first;
    // 当前结点
    ListNode cur = pHead;
    while (cur != null && cur.next != null) {
        if (cur.val == cur.next.val) {
            int val = cur.val;
            while (cur != null && (cur.val == val)) {
                cur = cur.next;
            }
            pre.next = cur;
        } else {
            pre = cur;
            cur = cur.next;
        }
    }
    // 这里不能返回pHead，因为pHead也可能被删除了
    return first.next;
}
