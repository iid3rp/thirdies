package thirds.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Map<K, V> implements Iterable<K>
{
    private static final int MAX_VALUE = 1 << 24;
    public Hash<K, V> hash;

    public Map()
    {
        hash = new Hash<>();
    }

    public Map(int bits)
    {
        hash = new Hash<>(bits);
    }

    public void put(K key, V value)
    {
        hash.put(key, value);
    }

    public void remove(K key)
    {
        hash.remove(key);
    }

    public V get(K key)
    {
        return hash.get(key);
    }

    @Override
    public Iterator<K> iterator()
    {
        return hash.keys.iterator();
    }

    public static class Hash<K, V>
    {
        public Singly<Node<K, V>>[] table;
        public Singly<K> keys;

        @SuppressWarnings("unchecked")
        public Hash() {
            table = new Singly[MAX_VALUE];
            keys = new Singly<>();
        }

        @SuppressWarnings("unchecked")
        public Hash(int bits)
        {
            if(bits > 24)
                throw new OutOfMemoryError("exceeding memory heap from JVM");
            if(bits < 1)
                throw new IllegalArgumentException("bits must be at more than 1");
            table = new Singly[1 << bits];
            keys = new Singly<>();
        }

        private int hash(Object obj)
        {
            return (obj.hashCode() & Integer.MAX_VALUE) % table.length;
        }

        public void put(K key, V value) {
            int index = hash(key);
            Singly<Node<K, V>> bucket = table[index];

            if (bucket == null) {
                bucket = new Singly<>();
                table[index] = bucket;
            } else {
                for (Node<K, V> node : bucket) {
                    if (node.key.equals(key)) {
                        node.value = value;
                        return;
                    }
                }
            }

            Node<K, V> newNode = new Node<>(key, value);
            bucket.add(newNode);
            keys.add(key);
        }

        public void remove(K key) {
            int index = hash(key);
            Singly<Node<K, V>> bucket = table[index];

            if (bucket != null) {
                Iterator<Node<K, V>> iterator = bucket.iterator();
                while (iterator.hasNext()) {
                    Node<K, V> node = iterator.next();
                    if (node.key.equals(key)) {
                        iterator.remove();
                        keys.remove(key);
                        return;
                    }
                }
            }
        }

        public V get(K key) {
            int index = hash(key);
            Singly<Node<K, V>> bucket = table[index];

            if (bucket != null)
                for (Node<K, V> node : bucket)
                    if (node.key.equals(key))
                        return node.value;
            return null;
        }
    }

    public static class Node<K, V>
    {
        K key;
        V value;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public static class Singly<E> implements Iterable<E>
    {
        private Item<E> first;
        private int size;

        public void add(E e)
        {
            Item<E> newItem = new Item<>(e, first);
            first = newItem;
            size++;
        }

        public E get(int index)
        {
            Item<E> current = first;
            for (int i = 0; i < index; i++)
            {
                if (current == null)
                    throw new IndexOutOfBoundsException();
                current = current.next;
            }
            return current.e;
        }

        public void remove(E e) {
            if (first == null) return;

            if (first.e.equals(e)) {
                first = first.next;
                size--;
                return;
            }

            Item<E> current = first;
            while (current.next != null)
            {
                if (current.next.e.equals(e))
                {
                    current.next = current.next.next;
                    size--;
                    return;
                }
                current = current.next;
            }
        }

        private static class Item<E>
        {
            E e;
            Item<E> next;

            public Item(E e, Item<E> next) {
                this.e = e;
                this.next = next;
            }
        }

        @Override
        public Iterator<E> iterator()
        {
            return new Iterator<>()
            {
                private Item<E> current = first;

                @Override
                public boolean hasNext()
                {
                    return current != null;
                }

                @Override
                public E next() {
                    if (current == null)
                        throw new NoSuchElementException();
                    E e = current.e;
                    current = current.next;
                    return e;
                }
            };
        }
    }
}
