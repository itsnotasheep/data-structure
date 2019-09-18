import org.junit.Test;

import java.util.LinkedList;

/**
 * 测试双链表
 */
public class Test4 {

    @Test
    public void demo1(){
        DoubleLinkedList<Integer> list=new DoubleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }

        list.add(2,9);
        System.out.println(list);
        list.addLast(8);
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

        for (int i = 0; i < 5; i++) {
            list.removeFirst();
            System.out.println(list);
        }
    }

    @Test
    public void demo2(){
        LinkedList list=new LinkedList();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }
        list.add(2,9);
        System.out.println(list);
    }
}
