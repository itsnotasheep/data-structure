import org.junit.Test;

public class MySegmentTreeTest {
    @Test
    public void demo1(){
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        MySegmentTree<Integer> segTree = new MySegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segTree);
        System.out.println(segTree.query(2,4));

        segTree.set(4,6);
        System.out.println(segTree.query(2,4));
    }
}
