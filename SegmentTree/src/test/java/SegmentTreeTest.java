import org.junit.Test;

public class SegmentTreeTest {
    @Test
    public void demo1(){
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                return a + b;
//            }
//        });
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);

        System.out.println(segTree);
        System.out.println(segTree.query(2, 5));
    }
}
