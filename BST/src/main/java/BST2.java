import java.util.LinkedList;
import java.util.Queue;

/**
 * 升级版二分搜索树
 * 1.Node新增count属性,支持重复元素,所有相同元素占一个节点
 * 2.Node新增size属性,统计以该节点为根的二分搜索树的元素
 * 3.Node新增depth属性,作为节点在二分搜索树内的深度
 *
 */
public class BST2<E extends Comparable<E>> {
    public class Node {
        public E e;         //存储的元素
        public Node left;   //左节点
        public Node right;  //右节点
        public int size;    //该节点及其下所有节点内的元素数量
        public int depth;   //节点的深度(root节点深度为0,其余依次递增)
        public int count;   //该节点存储的元素的数量

        public Node(E e, int depth) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.size = 1;
            this.depth = depth;
            this.count = 1;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < this.depth; i++) {
                res.append("-");
            }
            String str = String.format(" %d (%d) Size: %d, Depth: %d", this.e, this.count, this.size, this.depth);
            res.append(str);
            return res.toString();
        }
    }

    private Node root;  //根节点

    public BST2() {
        root = null;
    }

    //获取二分搜索树的元素总数
    public int size() {
        if (root == null)
            return 0;
        else
            return root.size;
    }

    //判断二分搜索树是否为空
    public boolean isEmpty() {
        return root == null;
    }

    //是否包含某元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    //递归方式查询是否有元素e
    private boolean contains(Node node,E e){
        if (node == null)
            return false;
        if (e.compareTo(node.e) == 0)
            return true;

        if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else// (e.compareTo(node.e) > 0)
            return contains(node.right, e);
    }


    //添加元素e
    public void add(E e) {
        root = add(root, e, 0);
    }

    //采用递归方式添加元素
    private Node add(Node node, E e, int depth) {
        if (node == null) {
            return node = new Node(e, depth);
        } else if (e.compareTo(node.e) == 0) {
            node.count++;
            node.size++;
            return node;
        }

        if (e.compareTo(node.e) < 0) {
            node.size++;
            node.left = add(node.left, e, depth + 1);
            return node;
        } else {// if (e.compareTo(node.e) > 0)
            node.size++;
            node.right = add(node.right, e, depth + 1);
            return node;
        }
    }


    //中序遍历
    public void inOrder() {
        inOrder(root);
    }

    //递归方式进行中序遍历
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }


    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //递归方式进行前序遍历
    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);
    }


    //后序遍历
    public void postOrder() {
        postOrder(root);
    }

    //递归方式进行后序遍历
    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node);
    }


    //递归方式进行层序遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = ((LinkedList<Node>) queue).pop();
            if (cur == null){
                continue ;
            }else {
                System.out.println(cur);
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
    }


    //寻找最小值
    public E minimum(){
        if (isEmpty())
            throw new IllegalArgumentException("BST2 is empty!");
        return minimum(root).e;
    }

    //递归方式查询以node为根的二分搜索树的最小元素所在节点;
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //寻找最大值
    public E maximum(){
        if (isEmpty())
            throw new IllegalArgumentException("BST2 is empty!");
        return maximum(root).e;
    }

    //递归方式查询以node为根的二分搜索树的最大元素所在节点;
    private Node maximum(Node node){
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    //删除最小值(复数情况下只删一个)
    public E removeMin(){
        E e = minimum();
        root = removeMin(root);
        return e;
    }

    //递归方式删除以node为根的最小元素(最小元素为复数情况下只删一个)
    //返回新的node节点
    private Node removeMin(Node node){
        if (node.left == null){
            node.count--;
            node.size--;
            if (node.count == 0)
                return lessDepth(node.right,1);
            else
                return node;
        }
        node.size--;
        node.left = removeMin(node.left);
        return node;

    }

    //删除最小元素所在节点
    public E removeAllMin(){
        Node node = minimum(root);
        root = removeAllMin(root,node.count);
        return node.e;
    }

    //递归方式删除以node为根的最小元素所在节点
    //返回新的node节点
    private Node removeAllMin(Node node,int count){
        if (node.left == null){
            return lessDepth(node.right,1);
        }
        node.size = node.size - count;
        node.left = removeAllMin(node.left, count);
        return node;

    }

    //将以node为根的二分搜索树下所有节点(包含node节点)的depth减i
    private Node lessDepth(Node node, int i){
        if (node == null)
            return node;
        node.left = lessDepth(node.left, i);
        node.right = lessDepth(node.right, 1);
        node.depth=node.depth-i;
        return node;
    }

    //删除最大元素(最大元素为复数情况下只删一个)
    public E removeMax(){
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    //递归方式删除以node为根的二分搜索树下的最大元素(最大元素为复数情况下只删一个)
    //返回新的node节点
    private Node removeMax(Node node){
        if (node.right == null) {
            node.size--;
            node.count--;
            if (node.count == 0)
                return lessDepth(node.left, 1);
            else
                return node;
        }
        node.size--;
        node.right = removeMax(node.right);
        return node;
    }

    //删除最大元素所在的节点
    public E removeAllMax(){
        Node node = maximum(root);
        root = removeAllMax(root,node.count);
        return node.e;
    }

    //递归方式删除以node为根的二分搜索树下的最大元素所在节点
    //返回新的node节点
    private Node removeAllMax(Node node, int count){
        if (node.right == null) {
            return lessDepth(node.left, 1);
        }
        node.size = node.size - count;
        node.right = removeAllMax(node.right, count);
        return node;
    }

    //删除元素e()
    public void remove(E e){
        root = remove(root, e);
    }

    //递归方式去除元素e
    //返回新的二分搜索树
    private Node remove(Node node, E e){
        if (node == null)
            return null;
        if (e.compareTo(node.e) == 0 && node.right == null && node.left != null) {
            node.size--;
            node.count--;
            if (node.count == 0)
                return lessDepth(node.left, 1);
            else
                return node;
        }else if (e.compareTo(node.e) == 0 && node.left == null && node.right != null){
            node.size--;
            node.count--;
            if (node.count == 0)
                return lessDepth(node.right, 1);
            else
                return node;
        }else if(e.compareTo(node.e) == 0 && node.left != null && node.right !=null){ // e.compareTo(node.e) == 0 && node.left != null && node.right !=null
            node.size--;
            node.count--;
            if (node.count == 0){
                Node rightNode = minimum(node.right);
                node.right = removeAllMin(node.right,rightNode.size);
                node.e = rightNode.e;
                node.count = rightNode.count;
                return node;
            }else
                return node;
        }

        node.size--;
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else { //e.compareTo(node.e) > 0
            node.right = remove(node.right, e);
            return node;
        }


    }

    //删除元素e所在的节点
    public void removeAll(E e){
        Node node = getNode(root,e);
        root = removeAll(root, e, node.count);
    }

    //递归方式去除元素e
    //返回新的二分搜索树
    private Node removeAll(Node node, E e, int count){
        if (node == null)
            return null;
        if (e.compareTo(node.e) == 0 && node.right == null && node.left != null) {
            return lessDepth(node.left, 1);
        }else if (e.compareTo(node.e) == 0 && node.left == null && node.right != null){
            return lessDepth(node.right, 1);
        }else if(e.compareTo(node.e) == 0 && node.left != null && node.right !=null){ // e.compareTo(node.e) == 0 && node.left != null && node.right !=null
            node.size = node.size - node.count;
            Node rightNode = minimum(node.right);
            node.right = removeAllMin(node.right,rightNode.size);
            node.e = rightNode.e;
            node.count = rightNode.count;

            return node;
        }

        node.size = node.size - count;
        if (e.compareTo(node.e) < 0){
            node.left = removeAll(node.left, e, count);
            return node;
        }else { //e.compareTo(node.e) > 0
            node.right = removeAll(node.right, e, count);
            return node;
        }
    }

    //获取以node为根的二分搜索树内元素为e的节点
    private Node getNode(Node node,E e){
        if (node == null)
            return null;
        if (e.compareTo(node.e) == 0)
            return node;
        if (e.compareTo(node.e) < 0)
            return getNode(node.left, e);
        else
            return getNode(node.right, e);

    }


}
