import org.junit.Test;

import java.util.Random;

public class Test4 {

    @Test
    public void demo1(){
        LinkedListStack<Integer> stack=new LinkedListStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

    //测试使用数组栈和链表栈之间性能差异
    private static double testStack(Stack<Integer> stack,int opCount){
        long startTime=System.nanoTime();

        Random random=new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000D;
    }

    @Test
    public void demo2(){
        int opCount=20000;
        ArrayStack<Integer> arrayStack=new ArrayStack<>();
        double time1=testStack(arrayStack,opCount);
        System.out.println("ArrayStack time: "+time1+" s");

        LinkedListStack<Integer> linkedListStack=new LinkedListStack<>();
        double time2=testStack(linkedListStack,opCount);
        System.out.println("LinkedListStack time: "+time2+" s");

        //LinkedListStack中包含更多的new操作
    }
}
