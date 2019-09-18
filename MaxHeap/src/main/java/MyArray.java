public class MyArray<E> {
    private E[] data;
    private int size;

    public MyArray(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public MyArray(E[] nums){
        data = (E[])new Object[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        size = nums.length;
    }

    public MyArray(){
        this(10);
    }

    public int getSize(){return size;}

    public boolean isEmpty(){return size == 0;}

    public int getCapacity(){return data.length;}

    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, index illegal");

        if (size == data.length){
            resize(data.length * 2);
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(E e){add(0, e);}

    public void addLast(E e){add(size, e);}

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed, index illegal");
        E ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[size - 1] = null;
        size--;


        if (size == data.length/4 && data.length/2 != 0)
            resize(data.length/2);

        return ret;
    }

    public E removeFirst(){return remove(0);}

    public E removeLast(){return remove(size - 1);}

    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index Illegal");
        return data[index];
    }

    public void swap(int i, int j){
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index Illegal");
        E e = data[i];
        data[i] = data[j];
        data[j] = e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1){
                res.append(", ");
            }
        }
        res.append(" ] size: " + getSize() + " capacity: " + getCapacity());
        return res.toString();
    }
}
