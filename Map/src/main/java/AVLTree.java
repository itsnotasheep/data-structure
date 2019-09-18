import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node{
        public K key;
        public V value;
        public int height;
        public Node left, right;
        public Node(K key, V value){
            this.key   = key;
            this.value = value;
            this.left  = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;


    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){return size;}

    public boolean isEmpty(){return size == 0;}

    //获取节点node的高度
    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    //获取节点node的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
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

    //二分搜索树的层序遍历
    public void levelOrder(){
        Node node=root;
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur=q.remove();
            System.out.println(cur.value);

            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //判断该二叉树是否是一个平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null)
            return true;

        int balanceFacTor = getBalanceFactor(node);
        if (Math.abs(balanceFacTor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }
    /**
     * 添加新元素
     * @param key
     * @param value
     * @return
     */
    public void put(K key, V value){
        root = put(root, key, value);
    }


    private Node put(Node node, K key, V value){
        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = put(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = put(node.right, key, value);
        else //key.compareTo(node.key) == 0
            node.value = value;

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFator = getBalanceFactor(node);
//        if (Math.abs(balanceFator) > 1){
//            System.out.println("unblanced : " + balanceFator);
//        }

        //平衡维护
        //LL
        if (balanceFator > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        //RR
        if (balanceFator < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        //LR
        if (balanceFator > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFator < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        //向右旋转过程
        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }


    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    /**
     * 获取key键对应的元素value
     * @param key
     * @return
     */
    public V get(K key){
        Node ret = get(root, key);
        if (ret == null)
            return null;
        return ret.value;
    }


    private Node get(Node node, K key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0){
            return node;
        }

        if (key.compareTo(node.key) < 0){
            return get(node.left, key);
        }else {
            return get(node.right, key);
        }
    }


    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    private Node removeMin(Node node){
        if (node.left == null){
            size--;
            Node rightNode = node.right;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除key键对应元素
     * @param key
     * @return
     */
    public V remove(K key){
        Node ret = get(root, key);

        root = remove(root, key);

        if (ret == null)
            return null;
        return ret.value;
    }

    private Node remove(Node node, K key){
        if (node == null)
            return null;
        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }else {
            if (node.left == null){
                size--;
                Node rightNode = node.right;
                node.right = null;
                retNode = rightNode;
            }else if (node.right == null){
                size--;
                Node leftNode = node.left;
                node.left = null;
                retNode = leftNode;
            }else {
                Node replaceNode = minimum(node.right);
                replaceNode.left = node.left;
                replaceNode.right = remove(node.right, replaceNode.key);

                node.left = node.right = null;

                retNode = replaceNode;
            }
        }
        if (retNode == null)
            return null;

        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balanceFator = getBalanceFactor(retNode);

        //平衡维护
        //LL
        if (balanceFator > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        //RR
        if (balanceFator < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
        //LR
        if (balanceFator > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFator < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }


        return retNode;
    }


    public boolean contains(K key){
        Node ret = get(root, key);
        return ret != null;
    }

    /**
     * 更新key对应的value
     * @param key
     * @param newValue
     */
    public void set(K key, V newValue){
        Node ret = get(root, key);
        if (ret == null)
            throw new IllegalArgumentException("no this key");
        ret.value = newValue;
    }



}
