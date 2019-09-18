import java.util.ArrayList;

public class Test {

    private static double testSet(Map<String,Integer> map, String filename){
        long startTime = System.nanoTime();

        ArrayList<String> words1 = new ArrayList<>();
        boolean flag1= FileOperation.readFile(filename,words1);
        if (flag1) {
            System.out.println("Total words: "+words1.size());

            for (String word : words1) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
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

        BSTMap<String,Integer> bstSet = new BSTMap<>();
        double time1 = testSet(bstSet,filename);
        System.out.println("BST Map: "+time1);

        System.out.println("=================");

        LinkedListMap<String,Integer> linkedListSet = new LinkedListMap<>();
        double time2 = testSet(linkedListSet,filename);
        System.out.println("LinkedList Map: "+time2);

        System.out.println("====================");

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testSet(avlMap,filename);
        System.out.println("AVL Map: "+time3);
    }
}
