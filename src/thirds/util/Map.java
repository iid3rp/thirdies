package thirds.util;

import java.util.Iterator;

public class Map<K, V> implements Iterable<K>
{
    private static final int MAX_VALUE = 1 << 24;
    K key;
    V value;
    Hash<K, V> hash;

    public Map()
    {
        identity();
    }

    public void put(K key, V value)
    {
        hash.put(key, value);
    }

    public void remove(K key, V value)
    {
        hash.remove(key, value);
    }

    public V get(K key)
    {
        return hash.get(key);
    }

    private void identity()
    {
        hash = new Hash<>();
    }

    private static int hash(Object obj)
    {
        return ((~1 ^ obj.hashCode()) + 1) % MAX_VALUE;
    }

    @Override
    public Iterator<K> iterator()
    {
        return hash.keys.iterator();
    }

    public static class Item<K, V>
    {
        K key;
        V value;
        LinkList<Item<K, V>> list;

        public Item(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        public Item<K, V> get(K key)
        {
            for(Item<K, V> item : list)
                if(item.key.equals(key))
                    return item;
            return null;
        }
    }

    private static class Hash<K, V>
    {
        Item<K, V>[] arr;
        LinkList<K> keys;

        @SuppressWarnings("unchecked")
        public Hash()
        {
            arr = new Item[MAX_VALUE];
        }

        public void put(K key, V value)
        {
             Item<K, V> item = arr[hash(key)];
             if(item != null && item != key)
                 if(item.list != null)
                     item.list.add(new Item<>(key, value));
                 else item.list = new LinkList<>(
                     item, new Item<>(key, value));
             else arr[hash(key)] = new Item<>(key, value);
             keys.add(key);
        }

        public void remove(K key, V value)
        {
            Item<K, V> item = arr[hash(key)];
            if (item != null && item.key.equals(key))
                if (item.list != null)
                    item.list.remove(item);
                else
                    arr[hash(key)] = null;
            keys.remove(key);
        }

        public V get(K key)
        {
            Item<K, V> item = arr[hash(key)];
            if(item != null)
                if(item.list != null) {
                    for(Item<K, V> i : item.list)
                        if(i.key.equals(key))
                            return i.value;
                    return null;
                }
                else return item.value;
            return null;

        }
    }
}
