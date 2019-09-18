import org.junit.Test;
import java.util.Random;

public class Test2 {

    /**
     * 测试循环队列和数组队列的差异
     */
    @Test
    public void Demo(){

        int opCount=100000;

        ArrayQueue<Integer> arrayQueue=new ArrayQueue<>();
        double tim1=testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue, time: "+tim1);

        LoopQueue<Integer> loopQueue=new LoopQueue<>();
        double tim2=testQueue(loopQueue,opCount);
        System.out.println("LoopQueue, time: "+tim2);

        LoopQueue2<Integer> loopQueue2=new LoopQueue2<>();
        double tim3=testQueue(loopQueue2,opCount);
        System.out.println("LoopQueue2, time: "+tim3);

        LinkedListQueue<Integer> linkedListQueue=new LinkedListQueue<>();
        double time4=testQueue(linkedListQueue,opCount);
        System.out.println("LinkedListQueue, time: "+time4);
    }
    //测试队列q运行o'pCount个enqueue和dequeue操作需要的时间,单位:秒
    private static double testQueue(Queue<Integer> q,int opCount){
        long startTime=System.nanoTime();

        Random random=new Random();
//        System.out.println(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }



        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000D;
    }
}
