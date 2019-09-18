public class Exercise203 {
    public class ListNode{
        private int val;
        private ListNode next;
        public ListNode(int val){this.val=val;}
    }

    class Solution{
        public ListNode removeElements(ListNode head,int val){
            while(head != null && head.val == val){
                ListNode delNode=head;
                head=head.next;
                delNode.next=null;
            }
            if (head == null){
                return null;
            }
            ListNode prev=head;
            while (prev.next != null){
                if (prev.next.val == val) {
                    ListNode delNode = prev.next;
                    prev.next=delNode.next;
                    delNode.next=null;
                }else {
                    prev=prev.next;
                }
            }
            return head;
        }
    }

    class Solution2{
        public ListNode removeElements(ListNode head,int val){
            ListNode dummyHead=new ListNode(-1);
            dummyHead.next=head;


            ListNode prev=dummyHead;
            while (prev.next != null){
                if (prev.next.val == val) {
                    ListNode delNode = prev.next;
                    prev.next=delNode.next;
                    delNode.next=null;
                }else {
                    prev=prev.next;
                }
            }
            return dummyHead.next;
        }
    }
}
