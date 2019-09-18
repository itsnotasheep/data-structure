import org.junit.Test;

/**
 * 数组链表测试
 */
public class Test3 {
    @Test
    public void demo1(){
        LinkedList3<Integer> list=new LinkedList3<>(5);
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }
        list.add(3,9);
        System.out.println(list);

        list.remove(4);
        System.out.println(list);

        for (int i = 0; i < 5; i++) {
            list.removeFirst();
            System.out.println(list);
        }

    }

    @Test
    public void demo2(){
        System.out.println(0==2/4 && 2/2 != 0);
    }


}
