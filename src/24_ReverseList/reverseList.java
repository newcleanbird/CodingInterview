public ListNode reverseList(ListNode head) {
  	ListNode pre = null;
  	ListNode next = null;
  	ListNode cur = head;
  	while (cur != null) {
      	// 当前结点的后一结点需要保存下来
    	next = cur.next;
      	// 反转
    	cur.next = pre;
      	// 指针前移
    	pre = cur;
    	cur = next;
  	}
  	// 最后pre到达的位置刚好是最末尾那个结点，即反转后的头结点
  	return pre;
}