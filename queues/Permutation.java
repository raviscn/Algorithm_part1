/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {

            queue.enqueue(StdIn.readString());
        }
        //A B C D E F G H I
        //  queue.enqueue("A");
        // queue.enqueue("B");
        for (int i = 0; i < n; i++) {
            StdOut.println(queue.dequeue());
        }

    }
}
