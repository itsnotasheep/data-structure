import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

public class MinHeapTest {
    @Test
    public  void demo1(){
        int n = 5;
        MinHeap<Integer> minHeap = new MinHeap<>(new IntegerComparator());
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int e = random.nextInt(40);

            minHeap.add(e);
        }
        System.out.println(minHeap);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = minHeap.remove();
        }

        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap completed");
    }

    private class IntegerComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 - o2)*(-1);
        }
    }

    @Test
    public void demo2(){
        IntegerComparator comparator = new IntegerComparator();
        System.out.println(comparator.compare(5,3));
    }
}
