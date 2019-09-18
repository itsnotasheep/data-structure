import java.util.ArrayList;

public class Test {

    private static double testSet(Set<String> set, String filename){
        long startTime = System.nanoTime();

        ArrayList<String> words1 = new ArrayList<>();
        boolean flag1=FileOperation.readFile(filename,words1);
        if (flag1) {
            System.out.println("Total words: "+words1.size());

            for (String s : words1) {
                set.add(s);
            }
            System.out.println("Total different words: "+set.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime)/1000000000D;
    }


    @org.junit.Test
    public void demo1(){
        String filename = "D:\\software\\IntelliJ IDEA Workspace\\IntelliJ IDEA Workspace1\\Set\\src\\main\\resources\\pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet,filename);
        System.out.println("BST Set: "+time1);

        System.out.println("==============");

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet,filename);
        System.out.println("linkedListSet Set: "+time2);

        System.out.println("==============");

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet,filename);
        System.out.println("AVL Set: "+time3);
    }
}
