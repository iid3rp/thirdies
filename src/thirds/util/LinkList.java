package thirds.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * doubly linked list for the efficiency of the data structures
 * @param <E>
 */
public class LinkList<E> implements Iterable<E>
{
    int size = 0;
    transient volatile Node<E> first;
    transient volatile Node<E> last;

    public LinkList() {}

    private Iterator<E> it = new Iterator<E>()
    {
        int i = 0;
        Node<E> e;

        @Override
        public boolean hasNext()
        {
            if(first == null)
                return false;
            if(i == 0)
                e = first;
            if(i < size)
                return true;
            else {
                reset();
                return false;
            }
        }

        public void reset()
        {
            i = 0;
            e = null;
        }

        @Override
        public E next()
        {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = e.e;
            e = e.next;
            i++;
            return element;
        }
    };

    @SafeVarargs
    public LinkList(E... elements)
    {
        for(E e : elements)
            add(e);
    }

    public void clear()
    {
        for(Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.e = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public boolean add(E e)
    {
        if(size == 0)
            addFirst(e);
        else
            addLast(e);
        return true;
    }

    public boolean add(E e, int index)
    {
        if(index == size)
            return addLast(e);
        else return addBefore(e, node(index));
    }

    public boolean addFirst(E e)
    {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(e, f, null);
        first = newNode;
        if(f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        return true;
    }

    public boolean addLast(E e)
    {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(e, null, l);
        last = newNode;
        if(l == null)
            first = newNode;
        else l.next = newNode;
        size++;
        return true;
    }

    private boolean addBefore(E e, Node<E> node)
    {
        assert node != null;
        final Node<E> pred = node.prev;
        final Node<E> newNode = new Node<>(e, node, pred);
        node.prev = newNode;
        if(pred == null)
            first = newNode;
        else pred.next = newNode;
        size++;
        return true;
    }

    private Node<E> node(int index)
    {
        assert index <= size && size >= 0;
        Node<E> n;
        if(index < (size >> 1)) {
            n = first;
            for(int i = 0; i < index; i++) {
                n = n.next;
            }
        }
        else {
            n = last;
            for(int i = size - 1; i > index; i--) {
                n = n.prev;
            }
        }
        return n;
    }

    public E pop()
    {
        return removeLast();
    }

    private E unlink(Node<E> e)
    {
        assert e != null;
        final E element = e.e;
        final Node<E> next = e.next;
        final Node<E> prev = e.prev;

        if(prev == null)
            first = next;
        else {
            prev.next = next;
            e.prev = null;
        }

        if(next == null)
            last = prev;
        else {
            next.prev = prev;
            e.next = null;
        }

        e.e = null;
        size--;
        return element;
    }

    public E removeFirst()
    {
        return unlink(first);
    }

    public E removeLast()
    {
        return unlink(last);
    }

    // implemented methods from the Iterable class
    @Override
    public Iterator<E> iterator()
    {
        return it;
    }

    @Override
    public void forEach(Consumer<? super E> action)
    {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator()
    {
        return Iterable.super.spliterator();
    }

    public int size()
    {
        return size;
    }

    public int length()
    {
        int len = size;
        return len;
    }

    @SuppressWarnings("unchecked")
    public E[] getArray()
    {
        int ind = 0;
        E[] elements = (E[]) new Object[size];
        for(E e : this)
            elements[ind++] = e;
        return elements;
    }

    public E getFirst()
    {
        return first.e;
    }

    public E getLast()
    {
        return last.e;
    }

    public E get(int i)
    {
        checkElementIndex(i);
        return node(i).e;
    }

    private void checkElementIndex(int i)
    {
        if (!(i >= 0 && i < size))
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public E remove(E e)
    {
        for(E element : this)
            if(e.equals(element))
                return element;
        return null;
    }

    private static class Node<E>
    {
        E e;
        Node<E> next;
        Node<E> prev;

        public Node(E e, Node<E> next, Node<E> prev)
        {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }
    }
}
