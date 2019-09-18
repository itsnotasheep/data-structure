import org.junit.Test;

public class Test3 {

    @Test
    public void demo1(){
        LoopQueue2<Integer> queue2=new LoopQueue2<>(3);
        for (int i = 0; i < 7; i++) {
            queue2.enqueue(i);
            System.out.println(queue2);
        }
        System.out.println(queue2.isEmpty());
        System.out.println(queue2.getCapacity());
        System.out.println(queue2.getSize());

        for (int i = 0; i < 7; i++) {
            queue2.dequeue();
            System.out.println(queue2);
        }
    }
}
