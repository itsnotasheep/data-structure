import org.junit.Test;

import java.util.ArrayList;

public class LinkedListSetTest {
    @Test
    public void demo1(){
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        boolean flag1=FileOperation.readFile("D:\\software\\IntelliJ IDEA Workspace\\IntelliJ IDEA Workspace1\\Set\\src\\main\\resources\\pride-and-prejudice.txt",words1);
        if (flag1) {
            System.out.println("Total words: "+words1.size());
            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String s : words1) {
                set1.add(s);
            }
            System.out.println("Total different words: "+set1.getSize());
        }

    }


}
