import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left= null;
            this.right = null;
        }

        @Override
        public String toString() {
            return key.toString()+" : "+value.toString();
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize(){return size;}

    @Override
    public boolean isEmpty(){return size==0;}

    @Override
    //判断该二叉树是否是一个二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>(size);
        inOrder(root, keys);

        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0)
                return false;
        }
        return true;
    }

    @Override
    public boolean isBalanced() {
        return false;
    }


    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    @Override
    public void add(K key, V value){
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if (node == null) {
            size++;
            return new Node(key, value);
        }else if (key.compareTo(node.key) == 0)
            node.value = value;

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        return node;
    }

    //返回以node为根节点的二分搜索树中,key所在的节点
    private Node getNode(Node node, K key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node;

        if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else//(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
//        return node;
    }

    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value){
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = value;
    }

    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    //删除以node为根的二分搜索树的最小值所在的节点
    //返回删除节点后新的二分搜索树
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else {//key.compareTo(node.key) == 0
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node minNode = minimum(node.right);
            minNode.right = removeMin(node.right);
            minNode.left = node.left;

            node.left = node.right = null;
            return minNode;
        }
    }
}
