import org.junit.Test;

public class Test2 {
    @Test
    public void demo1(){
        LinkedList2<Integer> list=new LinkedList2<>();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }
        list.add(3,6);
        System.out.println(list);

        list.set(2,9);
        System.out.println(list);

        System.out.println(list.get(1));

        list.remove(4);
        System.out.println(list);
        for (int i = 0; i < 5; i++) {
            list.removeFirst();
            System.out.println(list);
        }
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }
    }
}
