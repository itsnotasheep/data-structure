public class UnionFind2 implements UF{
    private int[] parent;
    private int[] rank;

    public UnionFind2(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int getSize(){return parent.length;}

    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is not of bound");

        if (p != parent[p]){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    //两个数是否在同一集合内
    public boolean isContented(int p, int q){return find(p) == find(q);}

    public void unionElement(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

}
