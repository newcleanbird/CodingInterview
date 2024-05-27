package Chap3;

public class MergeTwoOrderedList {
    public ListNode mergeRecur(ListNode list1,ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode mergedHead = null;
        if (list1.val < list2.val) {
            mergedHead = list1;
            mergedHead.next = mergeRecur(list1.next, list2);
        } else {
            mergedHead = list2;
            mergedHead.next = mergeRecur(list1, list2.next);
        }
        return mergedHead;
    }
}