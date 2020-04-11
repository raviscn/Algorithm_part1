/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    private Item[] items;
    private int N = 0;
    private int count = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (items.length == N) {
            resize(true);
        }
        items[N++] = item;
        count++;
    }

    private void resize(boolean add) {
        int k = 0;
        if (add) {
            Item[] items1 = (Item[]) new Object[2 * N];
            for (int i = 0; i < N; i++) {
                if (items[i] != null) {
                    k = i;
                    items1[i] = items[i];
                }
            }
            items = items1;
            N = k + 1;
        }
        else {
            Item[] items1 = (Item[]) new Object[N / 2];
            for (int i = 0; i < N / 2; i++) {
                if (items[i] != null) {
                    k = i;
                    items1[i] = items[i];
                }
            }
            items = items1;
            N = k;
        }


    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0)
            throw new java.util.NoSuchElementException();
        count--;
        while (true) {
            int randVal = StdRandom.uniform(0, N);
            if (items[randVal] != null) {
                Item val = items[randVal];
                items[randVal] = null;
                if (count <= items.length / 4 && items.length > 1) {
                    resize(false);
                }
                return val;
            }
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (count == 0)
            throw new java.util.NoSuchElementException();
        if (N == 0)
            return items[0];
        while (true) {
            int randVal = StdRandom.uniform(0, N);
            if (items[randVal] != null) {
                return items[randVal];
            }
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] item1;
        private int k = 0;

        private ArrayIterator() {
            item1 = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                if (items[j] != null)
                    item1[i++] = items[j];
            }
            StdRandom.shuffle(item1);
        }

        public boolean hasNext() {
            return k < i;
        }

        public void remove() { /* not supported */
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return item1[k++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }


        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        StdOut.println(rq.size());// ==> 0
        rq.enqueue(0);
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        rq.enqueue(3);

    }

}

