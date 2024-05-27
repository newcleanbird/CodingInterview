public ListNode reverseListRecur(ListNode head) {
  	if (head == null || head.next == null) return head;

  	ListNode revHead = reverseListRecur(head.next);
  	ListNode nextNode = head.next;
  	nextNode.next = head;
  	head.next = null;
  	return revHead;
}