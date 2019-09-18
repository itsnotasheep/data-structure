/**
 * 数组链表
 */
public class LinkedList3<E> {
    private class Node<E>{
        public E e;
        public int nextIndex;
        public Node(E e,int nextIndex){
            this.e=e;
            this.nextIndex=nextIndex;
        }
        public Node(E e){
            this(e,-1);
        }
        public Node(){
            this(null,-1);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node<E>[] data;
    private Node dummyHead;
    private int size;

    public LinkedList3(int capacity){
        data=new Node[capacity];
        dummyHead=new Node();
        size=0;
    }

    public LinkedList3(){
        this(10);
    }

    public int getSize(){return size;}

    public int getCapacity(){return data.length;}

    public boolean isEmpty(){return size==0;}

    /**
     * 在链表的index位置添加节点e
     * @param index 链表的位置
     * @param e 元素
     */
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        if (size==getCapacity()){
            resize(data.length*2);
        }
        Node prev=dummyHead;
        for (int i = 0; i < index; i++) {
            prev=data[prev.nextIndex];
        }
        data[size]=new Node(e,prev.nextIndex);
        prev.nextIndex=size;
        size++;
    }

    public void addFirst(E e){add(0,e);}

    public void addLast(E e){add(size,e);}

    /**
     * 去除链表index位置的节点
     * @param index
     * @return
     */
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index");
        Node<E> prev=dummyHead;
        for (int i = 0; i < index; i++) {
            prev=data[prev.nextIndex];
        }

        Node<E> retNode=data[prev.nextIndex];
        prev.nextIndex=retNode.nextIndex;
        retNode.nextIndex=-1;
        size--;

        if (size == getCapacity()/4 && getCapacity()/2 != 0 && size != 0){

            resize(getCapacity()/2);
        }
        return retNode.e;
    }

    public E removeFirst(){return remove(0);}

    public E removeLast(){return remove(size-1);}

    private void resize(int newCapacity) {
        Node<E>[] newData=new Node[newCapacity];
        Node<E> cur=data[dummyHead.nextIndex];
        for (int i = 0; i < size; i++) {
            if (cur.nextIndex != -1){
                newData[i]=new Node(cur.e,i+1);
                cur=data[cur.nextIndex];
            }else {
                newData[i]=new Node(cur.e,-1);
            }
        }
        data=newData;
        dummyHead.nextIndex=0;
    }



    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append(String.format("LinkedList3 capacity: %d, size: %d\n",getCapacity(),getSize()));
        if (isEmpty()){
            res.append("NULL");
        }else {
            Node cur=data[dummyHead.nextIndex];
            while (cur.nextIndex != -1){
                res.append(cur.e+"->");
                cur=data[cur.nextIndex];
            }
            res.append(cur.e+"->NULL");
        }

        return res.toString();
    }
}
