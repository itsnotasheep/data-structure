public class Test {

    @org.junit.Test
    public void Demo1(){
        ArrayQueue<Integer> queue=new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i%3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    @org.junit.Test
    public void Demo2(){
        LoopQueue<Integer> queue=new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i%3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    @org.junit.Test
    public void Demo3(){
        LinkedListQueue<Integer> queue=new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i%3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }




}
