import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


public class Test2 {
    private static double testSet(Map<String,Integer> map, String filename){
        long startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();

        boolean flag1= FileOperation.readFile(filename,words);
        if (flag1) {
            Collections.sort(words);
            System.out.println("Total words: "+words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            for (String word : words) {
                map.contains(word);
            }


            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));





        }

        long endTime = System.nanoTime();

        return (endTime - startTime)/1000000000D;
    }


    @org.junit.Test
    public void demo1(){
        String filename = "D:\\software\\IntelliJ IDEA Workspace\\IntelliJ IDEA Workspace1\\Set\\src\\main\\resources\\pride-and-prejudice.txt";

//        BSTMap<String, Integer> bstSet = new BSTMap<>();
//        double time1 = testSet(bstSet, filename);
//        System.out.println(time1);

        System.out.println("=========================");

        AVLTreeMap<String, Integer> avlTreeMap = new AVLTreeMap<>();
        double tim2 = testSet(avlTreeMap, filename);
        System.out.println(tim2);

        System.out.println("=========================");

        HashMap<String, Integer> hashMap = new HashMap<>();
        double tim3 = testSet(hashMap, filename);
        System.out.println(tim3);

    }

    @Test
    public void demo2(){
        System.out.println(0x7fffffff);
        System.out.println(-100000 & 0x7fffffff);
        System.out.println(100000 & 0x7fffffff);
    }
}
