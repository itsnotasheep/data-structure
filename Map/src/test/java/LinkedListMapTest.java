import org.junit.Test;

import java.util.ArrayList;

public class LinkedListMapTest {
    @Test
    public void demo1(){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("D:\\software\\IntelliJ IDEA Workspace\\IntelliJ IDEA Workspace1\\Map\\src\\main\\resources\\pride-and-prejudice.txt",words)){
            System.out.println("Total words: " + words.size());

            LinkedListMap<String,Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));


        }
    }
}
