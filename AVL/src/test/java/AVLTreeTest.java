import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void demo1(){
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        avlTree.put(1,1);
        avlTree.put(3,3);

        avlTree.put(2,2);

        avlTree.levelOrder();
    }
}
