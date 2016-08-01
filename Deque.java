/******************************************************************************
 *  Name:    Nick Barnett
 *  NetID:   nrbarnett
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Deque. Implements a double-ended queue or deque 
 *  (pronounced "deck") that supports adding and removing items from either the
 *  front or the back of the data structure. 
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int N; //instance variables
    private Node first;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next; //pointer the next Node
        private Node before; //pointer to the previous Node
    }
    
    public Deque() { // construct an empty deque
        first = new Node();
        last = new Node();
        first.item = null;
        last.item = null;
        first.before = null;
        first.next = last; //link first and last to begin the linked list
        last.before = first;
        last.next = null;
        N = 0;
    }
    
    public boolean isEmpty() { // is the deque empty?
        return N == 0;
    }
    
    public int size() { // return the number of items on the deque
        return N;
    }
    
    public void addFirst(Item item) { // add the item to the front
        if (item == null) { throw new NullPointerException("Missing Data"); }
        if (isEmpty()) {
            first = new Node(); 
//If the linked list is empty, the first and last must refer to the same Node
            first.item = item;
            last = first;
        }
        else {
        Node oldfirst = first; //temp
        first = new Node(); //add new node
        first.item = item; //initialize item field for new node
        first.next = oldfirst; //initialize next field for new node
        //first.before = null; //still equals null from constructor
//the first Node will always point to a null value, the link that comes before
//it
        oldfirst.before = first; //link oldfirst and first
        }
        N++; //increment the # of links in the list
    }
    
    public void addLast(Item item) { // add the item to the end
        if (item == null) { throw new NullPointerException("Missing Data"); }
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
        Node oldlast = last;
        last = new Node();
        last.item = item;        
        oldlast.next = last;
        //last.next = null;
        last.before = oldlast;
        }
        N++;        
    }
    
    public Item removeFirst() { // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException("Dequeue Underflow");
        Item item = first.item;
//        first.item = null; // free space
//        first.next = null; //free space
        first = first.next;
        //first.before = null;
        N--;
        return item;       
    }
    
    public Item removeLast() { // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException("Dequeue Underflow");
        Item item = last.item; //temp
        last = last.before;
        //last.next = null;
        N--;
        return item;
    }
    
    public Iterator<Item> iterator() { 
// return an iterator over items in order from front to end
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> { 
// Support LIFO iteration.
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() { throw new UnsupportedOperationException(); }
    }
    
    public static void main(String[] args) { // unit testing (required)
        Deque<String> deque = new Deque<String>();
        deque.addFirst("Ebay");
        deque.addLast("PayPal");
        deque.addFirst("Google");
        deque.addLast("Yahoo");
        deque.addFirst("IBM");
        deque.addLast("Facebook");
        
        // ListIterator approach
        System.out.println("Iterator Approach: ");
        Iterator<String> dequeIterator = deque.iterator();
        while (dequeIterator.hasNext()) {
            System.out.println(dequeIterator.next());
        }
        
        for (String s: deque) { System.out.println(s); }
    }
}
