public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数,生成容量为capacity的数组
     * @param capacity 数组的容量
     */
    public Array(int capacity){
        data=(E[])new Object[capacity];
        size=0;
    }

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 无参构造函数,默认生产容量为10的数组
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组中的元素个数
     * @return int
     */
    public int getSize(){
        return size;
    }


    /**
     * 获取数组的容量大小
     * @return int
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return boolean
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 在所有元素前添加一个元素
     * @param e 元素
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 在所有元素后添加一个元素
     * @param e 元素
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 在数组指定的位置插入一个元素
     * @param index 索引
     * @param e 元素
     */
    public void add(int index,E e){
        if (index < 0|| index >size){
            throw new IllegalArgumentException("Add failed, Require index >= 0 and index <=size");
        }

        if (size==data.length){
            resize(2*data.length);
        }


        for (int i = size; i > index; i--) {
            data[i]=data[i-1];
        }
        data[index]=e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     * @param index 索引
     * @return int
     */
    public E get(int index){
        if (index < 0|| index >= size){
            throw new IllegalArgumentException("Get failed,Index is illegal");
        }
        return data[index];
    }

    /**
     * 获取最后一个元素
     * @return
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 获取第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 修改index索引位置的元素
     * @param index 索引位置
     * @param e 元素
     */
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed,Index is illegal");
        }
        data[index]=e;
    }

    /**
     * 判断数组是否包含元素e
     * @param e 元素
     * @return boolean
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引,如果不存在数组e,则返回-1
     * @param e 元素
     * @return int 元素的索引
     */
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找数组中所有元素e所在的索引,返回Array数组
     * @param e 元素
     * @return Array
     */
    public Array findAll(E e){
        Array<Integer> indexs=new Array<>(size);
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                indexs.addLast(i);
            }
        }
        return indexs;
    }

    /**
     * 从数组中删除index位置的元素,返回删除的元素
     * @param index 索引
     * @return int
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed, Index is illegal");
        }
        E ret=data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1]=data[i];
        }
        size--;
        data[size]=null;    //loitering objects != memory leak

        if (size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 从数组中删除第一个元素,返回删除的元素
     * @return int
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素,返回删除的元素
     * @return int
     */
    public E removeLast(){
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e
     * @param e 元素
     */
    public boolean removeElement(E e){
        int index = find(e);
        if (index!=-1){
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 从数组中删除所有元素e
     * @param e 元素
     * @return
     */
    public int removeAllElement(E e){
        boolean b = true;
        int ret=0;
        while (b){
            b=removeElement(e);
            if (b){
                ret++;
            }
        }
        return ret;
    }


    public void swap(int i, int j){
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }



    /**
     * toString 重写方法
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append(String.format("Array:size = %d ,capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i!=size-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();

    }

    private void resize(int newCapacity){
        E[] newData=(E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i]=data[i];
        }
        data=newData;
    }
}
