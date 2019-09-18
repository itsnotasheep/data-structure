import java.util.Random;

public class Test {
    @org.junit.Test
    public void demo1(){
        int size = 10000000;
        int m    = 10000000;
        UF uf1 = new UnionFind(size);
        UF uf2 = new UnionFind2(size);

        System.out.println(testUF(uf1, m));
        System.out.println(testUF(uf2, m));
    }

    private static double testUF(UF uf, int m){
        int size = uf.getSize();

        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);

            uf.unionElement(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);

            uf.isContented(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000D;
    }
}
