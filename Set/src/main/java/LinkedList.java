public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e=e;
            this.next=next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node dummyHead;
//    private Node head;
    private int size;

    public LinkedList(){
        dummyHead=new Node(null,null);
//        head=null;
        size=0;
    }

    //获取链表中的元素个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //在链表的index(0-based)位置添加新元素e:这是练习用的,平时用不上
    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed,Illegal index.");
        }

        Node prev=dummyHead;
        for (int i = 0; i < index; i++) {
            prev=prev.next;
        }
//            Node node=new Node(e);
//            node.next=prev.next;
//            prev.next=node;

        prev.next=new Node(e,prev.next);

        size++;
    }

    //在链表头添加新元素e
    public void addFirst(E e){
        add(0,e);
    }

    //在链表的末尾添加新的元素e
    public void addLast(E e){
        add(size,e);
    }

    //获得链表的第index(0-based)个位置的元素: 练习用
    public E get(int index){
        if (index < 0 || index >=size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur=dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur=cur.next;
        }
        return cur.e;
    }

    //获取链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    //获取链表的最后一个元素
    public E getLast(){
        return get(size-1);
    }

    //修改链表的第index(0-based)个位置的元素e: 练习用
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index");
        }
        Node cur=dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur=cur.next;
        }
        cur.e=e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e){
        if (isEmpty()){
            return false;
        }

        Node cur=dummyHead.next;
        while (cur!=null){
            if (cur.e.equals(e)){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

    //从链表中删除index(0-based)位置的元素,返回删除的元素:练习用
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Illegal index");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev=prev.next;
        }
        Node retNode=prev.next;
        prev.next=retNode.next;
        retNode.next=null;

        size--;
        return retNode.e;
    }

    //从链表中删除第一个元素,返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从链表中删除最后一个元素,返回删除的元素
    public E removeLast(){
        return remove(size-1);
    }

    //从链表中删除元素e
    public void removeElement(E e){
        dummyHead = removeElement(dummyHead, e);
    }

    private Node removeElement(Node node, E e){
        if (node == null)
            return null;
        if (e.equals(node.e))
            return node.next;

        node.next = removeElement(node.next, e);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
//        Node cur=dummyHead.next;
//        while (cur !=null){
//            builder.append(cur+"->");
//            cur=cur.next;
//        }
        for (Node cur=dummyHead.next; cur != null; cur=cur.next){
            builder.append(cur+"->");
        }
        builder.append("NULL");
        return builder.toString();
    }
}
