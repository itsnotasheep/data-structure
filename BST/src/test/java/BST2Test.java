import org.junit.Test;

public class BST2Test {
    @Test
    public void demo1(){
        int[] nums={54,50,50,75,49,51,70,70,85,30,60,72,80,80,90,90,30};
        BST2<Integer> bst=new BST2<>();
//        System.out.println(bst.isEmpty());
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
        System.out.println(bst.size());
//        bst.inOrder();
//        bst.removeAllMin();
//        bst.removeAllMax();
//        bst.removeAll(70);
        bst.levelOrder();
        System.out.println("===============================");

        bst.removeAll(70);
        bst.levelOrder();
    }
}
