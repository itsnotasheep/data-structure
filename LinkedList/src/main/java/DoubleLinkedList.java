import java.util.LinkedList;

/**
 * 双链表
 */
public class DoubleLinkedList<E> {
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
    private Node tail;
    private int size;

    public DoubleLinkedList(){
        dummyHead=new Node(null,null,null);
        tail=null;
        size=0;
    }

    public int getSize(){return size;}

    public boolean isEmpty(){return size==0;}

    public void add(int index,E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index");
        if (tail==null){
            tail=new Node(e,dummyHead,null);
            dummyHead.next=tail;
            size++;
        }else {
            Node prev=prevNode(index);
            Node succ=prev.next;
            Node addNode=new Node(e,prev,succ);
            if (succ!=null){
                succ.prev=addNode;
            }else {
                tail=addNode;
            }
            prev.next=addNode;

            size++;
        }
    }

    //获取index位置的前一节点
    private Node prevNode(int index){
        if (index <= getSize()/2 ){
            Node prev=dummyHead;
            for (int i = 0; i < index; i++) {
                prev=prev.next;
            }
            return prev;
        }else {
            Node next=tail;
            for (int i = size; i > index; i--){
                next=next.prev;
            }
            return next;

        }
    }

    public void addFirst(E e){add(0,e);}

    public void addLast(E e){add(size,e);}

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index");
        if (isEmpty())
            throw new IllegalArgumentException("Cannot remove form an empty linkedList.");
        Node prev = prevNode(index);
        Node cur=prev.next;
        Node next=cur.next;

        prev.next=next;
        if (next !=null){
            next.prev=prev;
        }
        cur.prev=null;
        if (cur.next==null){
            tail=prev;
        }else {
            cur.next=null;
        }
        size--;
        return cur.e;
    }

    public E removeFirst(){return remove(0);}

    public E removeLast(){return remove(size-1);}

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        Node cur=dummyHead.next;
        res.append("front:");
        for (int i = 0; i < size; i++) {
            res.append(cur.e+"<->");
            cur=cur.next;
        }
        res.append("NULL :tail");
        return res.toString();
    }
}
