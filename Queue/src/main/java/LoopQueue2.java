import java.util.Arrays;

public class LoopQueue2<E> implements Queue<E> {
    private E[] data;
    private int front;  //头部元素所在索引
    private int tail;   //新元素所在索引


    public LoopQueue2(int capacity){
        data=(E[])new Object[capacity+1];
        front=0;
        tail=0;

    }

    public LoopQueue2(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public void enqueue(E e) {
        if (getSize()==data.length-1){
            resize(2*getCapacity());
        }
        data[tail]=e;
        tail++;

    }

    private void resize(int newCapacity) {
        E[] newData=(E[])new Object[newCapacity+1];
        int j=0;
//        System.out.println(front+" "+tail+" "+getSize()+" "+getCapacity());
        for (int i = front; i != tail; i=(i+1)%data.length) {
            newData[j]=data[i];
            j++;
        }
        data=newData;
        //必须先获取tail,再给front赋0
        tail=getSize();
        front=0;

    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("dequeue failed, cannot dequeue from an empty queue");
        }
        E ret=data[front];
        data[front]=null;
        front=(front+1)%data.length;
        if (getSize() == getCapacity()/4 && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public int getSize() {
        if (front==tail){
            return 0;
        }else if (front < tail){
            return tail-front;
        }else{
            return data.length-(front-tail);
        }
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("Queue: front = %d, tail = %d, capacity = %d, size = %d\n",front,tail,getCapacity(),getSize()));
        builder.append("front [");
        for (int i = front; i != tail ; i= (i+1)%data.length) {
            builder.append(data[i]);
            if ((i+1)%data.length!=tail){
                builder.append(", ");
            }
//            System.out.println(builder.toString());
        }
        builder.append("] tail");
        return builder.toString();
    }
}
