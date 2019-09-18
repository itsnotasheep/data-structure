public class HashMap<K, V> implements Map<K, V> {
    private HashTable<K, V> hashTable;

    public HashMap(){
        hashTable = new HashTable();
    }
    @Override
    public void add(K key, V value) {
        hashTable.add(key, value);
    }

    @Override
    public V remove(K key) {
        return hashTable.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return hashTable.contains(key);
    }

    @Override
    public V get(K key) {
        return hashTable.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        hashTable.set(key, newValue);
    }

    @Override
    public int getSize() {
        return hashTable.getSize();
    }

    @Override
    public boolean isEmpty() {
        return hashTable.getSize() == 0;
    }

    @Override
    public boolean isBST() {
        return false;
    }

    @Override
    public boolean isBalanced() {
        return false;
    }
}
