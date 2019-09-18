/**
 * 采用递归的方法对链表进行再设计
 */
public class LinkedList2<E> {
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


    private Node dummyHead; //虚拟头节点
    private int size;       //节点数量

    public LinkedList2(){
        dummyHead=new Node(null,null);
        size=0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int index,E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev=dummyHead;
//        for (int i = 0; i < index; i++) {
//            prev=prev.next;
//        }
//        prev.next=new Node(e,prev.next);
//        size++;
        Node newNode= addByRecursive(prev,index,e);
        size++;
    }
    //采用递归思想添加元素e
    private Node addByRecursive(Node prev, int index, E e){
//        if (index==0){
//            prev.next=new Node(e,prev.next);
//        }else {
//            prev.next=addByRecursive(prev.next,index-1,e);
//        }
        prev.next=index == 0 ? new Node(e, prev.next): addByRecursive(prev.next, index-1, e);
        return prev;
    }


    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public void remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot remove from an empty linkedList");
        }

        Node prev=dummyHead;
//        for (int i = 0; i < index; i++) {
//            prev=prev.next;
//        }
//        Node retNode=prev.next;
//        prev.next=retNode.next;
//        retNode.next=null;
        Node retNode=removeByRecursive(prev.next,index);
        size--;
//        return retNode.e;
    }

    //采用递归思想去除index位置元素e,返回去除的index位置的Node
//    private Node removeByRecursive(Node prev, int index){
//        Node retNode=null;
//        if (index==0){
//            retNode=prev.next;
//            prev.next=retNode.next;
//            retNode.next=null;
//        }else {
//            retNode=removeByRecursive(prev.next,index-1);
//        }
//        return retNode;
//    }

    //采用递归思想去除index位置元素e,返回去除后的链表
    private Node removeByRecursive(Node cur, int index){
//        Node retNode=null;
        if (index==0){
            return cur.next;
        }
        cur.next=removeByRecursive(cur.next,index-1);
        return cur;

    }

    public void removeFirst(){
         remove(0);
    }

    public void removeLast(){
         remove(size-1);
    }

    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot set from an empty linkedList");
        }

        Node cur=dummyHead.next;
//        for (int i = 0; i < index; i++) {
//            cur=cur.next;
//        }
//        cur.e=e;
        setByRecursive(cur,index,e);
    }
    //采用递归思想替代idnex位置的元素
    private E setByRecursive(Node cur,int index,E e){
//        if (index==0){
//            cur.e=e;
//        }else {
//            setByRecursive(cur.next,index-1,e);
//        }
        return index == 0 ? cur.e=e : setByRecursive(cur.next, index-1, e);
    }

    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot get from an empty linkedList");
        }

//        Node cur=dummyHead.next;
//        for (int i = 0; i < index; i++) {
//            cur=cur.next;
//        }
//        return cur.e;
        return getByRecursive(dummyHead.next,index);
    }
    //采用递归思想获取idnex位置的元素
    private E getByRecursive(Node cur,int index){
//        if (index==0){
//            return cur.e;
//        }else {
//            return getByRecursive(cur.next,index-1);
//        }
        return index == 0 ? cur.e : getByRecursive(cur.next, index-1);
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        Node cur=dummyHead.next;
        while (cur != null){
            res.append(cur+"->");
            cur=cur.next;
        }
        res.append("NULL");
        return res.toString();

    }

}
