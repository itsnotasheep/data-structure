import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left= null;
            this.right = null;
            this.color = RED;
        }

        @Override
        public String toString() {
            return key.toString()+" : "+value.toString();
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }


    public int getSize(){return size;}

    public boolean isEmpty(){return size==0;}

    //判断节点的颜色
    private boolean isRed(Node node){
        if (node == null)
            return BLACK;
        return node.color;
    }

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



    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //添加新元素
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;

        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    //颜色反转
    private void flipColors(Node node){
        node.left.color = node.right.color = BLACK;
        node.color = RED;
    }

    private Node add(Node node, K key, V value){
        if (node == null) {
            size++;
            return new Node(key, value);    //默认插入红色节点
        }
        if (key.compareTo(node.key) == 0)
            node.value = value;

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);

        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

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


    public boolean contains(K key){
        return getNode(root, key) != null;
    }


    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }


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

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("D:\\software\\IntelliJ IDEA Workspace\\IntelliJ IDEA Workspace1\\RedBlackTree\\src\\main\\resources\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
