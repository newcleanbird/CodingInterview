package Chap3;


public class EntryNodeOfLoop {
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 双指针法
     * @param pHead 链表头结点
     * @return 环的入口结点
     */
    public ListNode entryNodeOfLoop(ListNode pHead)
    {
        ListNode pFast = pHead;
        ListNode pSlow = pHead;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;
            pSlow = pSlow.next;
            // 运行到此说明有环
            if (pFast == pSlow) {
                pFast = pHead;
                while (pFast != pSlow) {
                    pFast = pFast.next;
                    pSlow = pSlow.next;
                }
                // 在入口节点处相遇
                return pSlow;
            }
        }

        return null;
    }
}
