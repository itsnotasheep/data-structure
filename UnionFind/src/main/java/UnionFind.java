public class UnionFind implements UF{
    private int[] parent;
    private int[] rank;

    public UnionFind(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    //获取数据大小
    public int getSize(){return parent.length;}

    //查找过程, 查找元素p所对应的集合编号
    //O(h)复杂度, h为树的高度
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is not of bound");

        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
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
