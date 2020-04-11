/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    private Node<Item> first, last;
    private int count;

    public Deque() {

    }

    private class Node<Item> {
        Node<Item> Next;
        Node<Item> Previous;
        Item item;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> oldFirst = first;
        Node<Item> newItem = new Node<Item>();
        newItem.item = item;
        if (count == 0) {
            first = newItem;
            last = newItem;

        }
        else {
            newItem.Next = oldFirst;
            oldFirst.Previous = newItem;
            first = newItem;

        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> oldLast = last;
        Node<Item> newItem = new Node<Item>();
        newItem.item = item;
        if (count == 0) {
            first = newItem;
            last = newItem;
        }
        else {
            oldLast.Next = newItem;
            newItem.Previous = oldLast;
            last = newItem;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (count == 0)
            throw new NoSuchElementException();
        count--;
        Item item = first.item;
        if (count == 0) {
            last = null;
            first = null;
        }
        else
            first = first.Next;
        // first.Previous = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (count == 0)
            throw new NoSuchElementException();
        count--;

        Item item = last.item;
        if (count == 0) {
            last = null;
            first = null;
        }
        else {
            last = last.Previous;
            last.Next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { /* not supported */
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.Next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);

        deque.removeLast();

        //    StdOut.println(deque.size());  //  ==
        for (int a : deque) {
            StdOut.println(a);
        }

    }
}
