import java.util.Arrays;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;  //队首元素所在索引
    private int tail;   //添加元素时占用的索引位置
    private int size ;   //元素数量

    public LoopQueue(int capacity){
        data=(E[])new Object[capacity+1];
        size=0;
        front=0;
        tail=0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapaticy(){
        return data.length-1;
    }



    @Override
    public void enqueue(E e) {
        if ((tail+1)%data.length == front){
            resize(getCapaticy()*2);
        }

        data[tail]=e;
        tail=(tail+1)%data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData=(E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i++) {
            newData[i]=data[(front+i) % data.length];
        }
        data=newData;
        front=0;
        tail=size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret=data[front];
        data[front]=null;
        front=(front + 1) % data.length;
        size--;

        if (size==getCapaticy()/4 && getCapaticy()/2 != 0){
            resize(getCapaticy()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot getFront from an empty queue");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;

    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }


    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", getSize(), getCapaticy()));
        res.append("front [");
        for (int i = front; i != tail; i=(i+1)% data.length) {
            res.append(data[i]);
            if ((i+1)% data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
