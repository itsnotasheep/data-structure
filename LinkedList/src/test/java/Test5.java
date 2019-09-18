import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环双链表测试
 */
public class Test5 {
    @Test
    public void demo1(){
        LoopDoubleLinkedList<Integer> list=new LoopDoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }
//        System.out.println(list.getSize());
        list.addLast(5);
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.remove(2);
        System.out.println(list);
    }



    @Test
    public void demo2(){


    }
}
