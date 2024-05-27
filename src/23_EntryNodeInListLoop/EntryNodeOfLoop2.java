// 利用Set中元素的唯一性

package Chap3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * 一个链表中包含环，请找出该链表的环的入口结点。
 */
public class EntryNodeOfLoop {
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 利用Set不可添加重复元素的性质
     */
    public ListNode entryNodeOfLoop_set(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode cur = pHead;
        while (cur != null) {
            if (!set.add(cur)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * Map，思路同Set
     */
    public ListNode entryNodeOfLoop_map(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        Map<ListNode, Boolean> map = new HashMap<>();
        ListNode cur = pHead;
        while (cur != null) {
            if (map.containsKey(cur)) {
                return cur;
            }
            map.put(cur, true);
            cur = cur.next;
        }
        return null;
    }
}
