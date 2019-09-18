import java.util.Comparator;

public class MinHeap<E extends Comparable<E>> {
    private MyArray<E> data;
    private Comparator<? super E> comparator;

    public MinHeap(){
        data = new MyArray<>();
        comparator = null;
    }

    public MinHeap(int capacity){
        data = new MyArray<>(capacity);
        comparator = null;
    }

    public MinHeap(Comparator<? super E> comparator){
        data = new MyArray<>();
        this.comparator = comparator;
    }

    public MinHeap(int capacity, Comparator<? super E> comparator){
        data = new MyArray<>(capacity);
        this.comparator = comparator;
    }

    public int Size(){return data.getSize();}

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){
        if (comparator == null){
            while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0){
                data.swap(k, parent(k));
                k = parent(k);
            }
        }else {
            while (k > 0 && comparator.compare(data.get(parent(k)), data.get(k)) > 0){
                data.swap(k, parent(k));
                k = parent(k);
            }
        }

    }

    public E remove(){
        E ret = data.get(0);

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){
        if (comparator == null){
            while (leftChild(k) < data.getSize()){
                int j = leftChild(k);
                if (j + 1 < data.getSize() && data.get(j).compareTo(data.get(j + 1)) > 0)
                    j = j + 1;

                if (data.get(k).compareTo(data.get(j)) <= 0)
                    break;

                data.swap(k, j);
                k = j;
            }
        }else {
            while (leftChild(k) < data.getSize()){
                int j = leftChild(k);
                if (j + 1 < data.getSize() && comparator.compare(data.get(j), data.get(j + 1)) > 0)
                    j = j + 1;

                if (comparator.compare(data.get(k), data.get(j)) <= 0)
                    break;

                data.swap(k, j);
                k = j;
            }
        }

    }

    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("Index Illegal");
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }


    @Override
    public String toString() {
        return data.toString();
    }
}
