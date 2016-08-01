/******************************************************************************
 *  Name:    Nick Barnett
 *  NetID:   nrbarnett
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  RandomizedQueue. Implements a randomized queue. Removed items
 *  are chosen uniformly at random from items in the data structure.
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] rq;
    private int N;
    //private final int initCap = 100;
    
    public RandomizedQueue() { // construct an empty randomized queue
        rq = (Item[]) new Object[2];
        N = 0;
    }
    
    public boolean isEmpty() { // is the queue empty?
        return N == 0;
    }
    
    public int size() { // return the number of items on the queue
        return N;
    }
    
    public void enqueue(Item item) { // add the item
        if (item == null) { throw new NullPointerException("Invalid input"); }
        //System.out.println("Size of Array : " + rq.length); Testing if resize
        //works correctly
        //for some odd reason, rq.length cannot be resolved or is not a field 
        //when implemented in main
        if (N == rq.length) resize(2*rq.length);
        rq[N] = item;
        N++; 
    }
    
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = rq[i];
        }
        rq = temp;
    }
    
    public Item dequeue() { // remove and return a random item
        if (isEmpty()) { throw new NoSuchElementException("Queue Underflow"); }
        int randIndex = StdRandom.uniform(N);
        Item temp = rq[randIndex];
        rq[randIndex] = rq[N-1]; 
        //To avoid having to create another array and 
        //copy the values from rq into it, I just replace the array element 
        //removed with the last element in the array, which shouldn't matter 
        //since elements are removed at random anyway
        rq[N-1] = null; //avoid loitering
        N--;
        if (N > 0 && N == rq.length/4) resize(rq.length/2); 
        //if after removing the element, the array is 1/4 full, resize it, to 
        //wasting too much memory
        return temp;   
    }
    
    public Item sample() { // return a random item (but do not remove it)
        if (isEmpty()) { throw new NoSuchElementException("Queue Underflow"); }
        int randIndex = StdRandom.uniform(N); //get a random index
        return rq[randIndex]; 
//use the random index to return a random element from rq
    }
    
    public Iterator<Item> iterator() { 
// return an independent iterator over items in random order
        return new RQIterator();
    }
    
    private class RQIterator implements Iterator<Item> {
        private int i;
        private int[] randIndices;
        
        public RQIterator() { 
//create an array whose elements correspond to their index. 
//Then randomize the elements in the array. To be used to refer to random 
//indices in rq
            randIndices = new int[N];
            for (int j = 0; j < N; j++) {
                randIndices[j] = j;
            }
            StdRandom.shuffle(randIndices);
        }
        
        public boolean hasNext() { return i < N; }
        
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = rq[randIndices[i]];
            i++;
            return item;
        }
        
        public void remove() { throw new UnsupportedOperationException(); }
    }
    
    public static void main(String[] args) { // unit testing (required)
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("Ebay");
        rq.enqueue("PayPal");
        rq.enqueue("Google");
        rq.enqueue("Yahoo");
        rq.enqueue("IBM");
        rq.enqueue("Facebook");
        
        //System.out.println("Size of Array : " + rq.length);
        
        //System.out.println(rq.sample());
        //Seems to work
        
        //System.out.println(rq.dequeue());
        //Seems to work
        
//        for (int i = 0; i < 3; i++) {
//            for (String s: rq) { System.out.println(s); }
//            System.out.println();
//        }
        //Iterator seems to be random
        
        
        
    }
}