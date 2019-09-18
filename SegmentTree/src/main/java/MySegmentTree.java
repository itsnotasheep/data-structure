public class MySegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public MySegmentTree(E[] arr,Merger<E> merger){
        this.data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        this.merger = merger;
        this.tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){
        if (l == r){
            tree[treeIndex] = data[l];
            return ;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (queryL == l && queryR == r)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1)
            return  query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);
        else {
            E leftChild = query(leftTreeIndex, l, mid, queryL, mid);
            E rightChild = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            E ret = merger.merge(leftChild, rightChild);
            return ret;
        }

    }

    public void set(int index, E e){
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private E set(int treeIndex, int l, int r, int index, E e){
        if (l == r && l == index){
            tree[treeIndex] = e;
            return e;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1)
            tree[rightTreeIndex] = set(rightTreeIndex, mid + 1, r, index, e);
        else
            tree[leftTreeIndex] = set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        return tree[treeIndex];
    }


    public int getSize(){return data.length;}

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){return leftChild(index) + 1;}

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] == null)
                res.append("null");
            else
                res.append(tree[i]);
            if (i != tree.length - 1)
                res.append(", ");

        }
        res.append(']');
        return res.toString();
    }
}
