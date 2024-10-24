package thirds.util;

import java.util.ArrayList;
import java.util.List;

public class Tag<T, O>
{
    public static final int MAX_VALUE = 1 << 24;
    T tag;
    O obj;
    Hash<T> hash;

    public Tag() {}

    private static int hash(Object tag)
    {
        return ((~1 ^ tag.hashCode()) + 1) % MAX_VALUE;
    }


    private static class Hash<T>
    {
        T[] arr;
        Entry<T> entry;

        @SuppressWarnings("unchecked")
        public Hash()
        {
            arr = (T[]) new Object[MAX_VALUE];
        }

        public void add(T tag)
        {
             T hashObj = arr[hash(tag)];
             if(hashObj != null && hashObj != tag)
                 if(entry != null)
                     entry.add(tag);
                 else entry = new Entry<>(hashObj, tag);
             else arr[hash(tag)] = tag;
        }
    }

    private static class Entry<T>
    {
        List<T> list;

        public Entry(T primary, T another)
        {
            list = new ArrayList<>();
            list.add(primary);
            list.add(another);
        }

        public void add(T obj)
        {
            list.add(obj);
        }

        public T get(T obj)
        {
            for(T t : list)
                if(obj.equals(t))
                    return t;
            return null;
        }
    }
}
