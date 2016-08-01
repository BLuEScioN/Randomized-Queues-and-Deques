/******************************************************************************
 *  Name:    Nick Barnett
 *  NetID:   nrbarnett
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Subset. Takes a command-line integer k; reads in a sequence
 *  of strings from standard input using StdIn.readString(); and prints out
 *  exactly k of them, uniformly at random.
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
    
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) { 
// reads in a sequence of strings from standard input
            String item = StdIn.readString();
            if (item != null) { rq.enqueue(item); } 
//enqueue the item into the rq data type
        }
        
        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue()); 
//the dequeue instance method ensures that the printed items only appear once 
//in the output
        }     
    }
}