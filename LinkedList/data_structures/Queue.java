/* Michael Green
   masc0338
 * A class assignment for CS310, a linked list implementation using an interface provided to us called
 * ArrayLinearList
 * @author Michael Green
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<E> implements Iterable<E>{

	// We don't even need a constructor.
	private LinearList<E> list = new LinearList<E>();

	// inserts the object obj into the queue
    public void enqueue(E obj){
    	list.addLast(obj);
    };
     
    // removes and returns the object at the front of the queue   
    public E dequeue(){
    	return list.removeFirst();
    };
    
    // returns the number of objects currently in the queue    
    public int size(){
    	return list.size();
    };
    
    // returns true if the queue is empty, otherwise false   
    public boolean isEmpty(){
    	return list.isEmpty();
    };
    
    // returns but does not remove the object at the front of the queue   
    public E peek(){
    	return list.peekFirst();
    };
    
    // returns true if the Object obj is in the queue    
    public boolean contains(E obj){
    	return list.contains(obj);
    } ;
     
    // returns the queue to an empty state  
    public void makeEmpty(){
    	list.clear();
    };
    
    // removes the Object obj if it is in the queue and
    // returns true, otherwise returns false.
    public boolean remove(E obj){
    	return list.remove(obj) == null ? false : true;
    };
    
    // returns an iterator of the elements in the queue.  The elements
    // must be in the same sequence as dequeue would return them.    
    public Iterator<E> iterator(){
		return list.iterator();
    };
}
