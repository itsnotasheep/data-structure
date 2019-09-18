import org.junit.Test;

public class MyArrayTest{
    @Test
    public void demo1(){
        MyArray<Integer> array = new MyArray<>(3);
        for (int i = 0; i < 5; i++) {
            array.addFirst(i);
            System.out.println(array);
        }

        array.add(3,9);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        for (int i = 0; i < 5; i++) {
            array.removeFirst();
            System.out.println(array);
        }

    }
}
