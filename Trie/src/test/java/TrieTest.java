import org.junit.Test;

import java.util.ArrayList;

public class TrieTest {

    //Trie 与 BSTSet 存储查询字符串的性能比较
    @Test
    public void demo1() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("D:\\software\\IntelliJ IDEA Workspace\\IntelliJ IDEA Workspace1\\Trie\\src\\main\\resources\\a-tale-of-two-cities.txt", words)) {
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000D;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSET: " + time + "s");

            //--
            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000D;
            System.out.println("Total different words: " + trie.getSize());
            System.out.println("TRIE: " + time + "s");
        }
    }
}
