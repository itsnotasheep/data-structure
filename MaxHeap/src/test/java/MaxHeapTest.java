import org.junit.Test;

import java.util.Random;

public class MaxHeapTest {
    @Test
    public  void demo1(){
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap completed");
    }

    @Test
    public void demo2(){
        int n = 1000000;
        Integer[] testData = new Integer[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(testData,false);
        System.out.println("Without heapify: " + time1 + "s");

        double time2 = testHeap(testData,true);
        System.out.println("With heapify: " + time2 + "s");

    }

    private static double testHeap(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else {
            maxHeap = new MaxHeap<>();
            for (Integer num : testData) {
                maxHeap.add(num);
            }
        }

        //测试正确性start
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap completed");
        //测试正确性end

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000D;
    }
}
