/**
 * 循环双链表
 * @param <E>
 */
public class LoopDoubleLinkedList<E> {
    private class Node{
        public E e;
        public Node prev;
        public Node next;
        public Node(E e,Node prev,Node next){
            this.e=e;
            this.prev=prev;
            this.next=next;
        }
        public Node(E e,Node prev){
            this(e,prev,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LoopDoubleLinkedList(){
        dummyHead=new Node(null,null,null);
        size=0;
    }

    public int getSize(){return size;}

    public boolean isEmpty(){return size==0;}

    public void add(int index,E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index");
        Node suc=node(index);
        if (suc==null){
            Node newNode=new Node(e,dummyHead,dummyHead);
            dummyHead.next=newNode;
            dummyHead.prev=newNode;
        }else {
            Node prev=suc.prev;
            Node newNode=new Node(e,prev,suc);
            prev.next=newNode;
            suc.prev=newNode;
        }
        size++;
    }

    public void addFirst(E e){add(0,e);}

    public void addLast(E e){add(size,e);}

    private Node node(int index){
        if (index<=size/2){
            Node prev=dummyHead;
            for (int i = 0; i < index; i++) {
                prev=prev.next;
            }
            return prev.next;
        }else {
            Node next=dummyHead;
            for (int j = size; j > index; j--){
                next=next.prev;
            }
            return next;
        }
    }

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index");
        if (isEmpty())
            throw new IllegalArgumentException("Cannot remove from an empty linkedList.");
        Node cur=node(index);
        if (size==1){
            dummyHead.next=null;
            dummyHead.prev=null;
        }else {
            Node prev=cur.prev;
            Node next=cur.next;
            prev.next=next;
            next.prev=prev;
        }
        cur.next=null;
        cur.prev=null;
        size--;
        return cur.e;
    }

    public E removeFirst(){return remove(0);}

    public E removeLast(){return remove(size-1);}

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append("dummy");
        Node cur=dummyHead.next;
        while (cur!=dummyHead){
            res.append(cur.e+"<->");
            cur=cur.next;
        }
        res.append("dummy");
        return res.toString();
    }
}
