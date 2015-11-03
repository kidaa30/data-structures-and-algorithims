/* Michael Green
   masc0338
 * A class assignment for CS310, a linked list implementation using an interface provided to us called
 * ArrayLinearList
 * @author Michael Green
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements Iterable<E>{

	private LinearListADT<E> list = new LinearList<E>();

      // inserts the object obj into the stack
    public void push(E obj){
    	list.addFirst(obj);
    };
    
    // pops and returns the element on the top of the stack    
    public E pop(){
    	return list.removeFirst();
    }
    
    // returns the number of elements currently in the stack
    public int size(){
    	return list.size();
    }
    
    // return true if the stack is empty, otherwise false
    public boolean isEmpty(){
    	return list.isEmpty();
    }
    
    // returns but does not remove the element on the top of the stack    
    public E peek(){
    	return list.peekFirst();
    };
     
    // returns true if the object obj is in the stack,
    // otherwise false   
    public boolean contains(E obj){
    	return list.contains(obj);
    };
    
    // returns the stack to an empty state    
    public void makeEmpty(){
    	list.clear();
    } 
    
    // removes the Object obj if it is in the stack and
    // returns true, otherwise returns false.
    public boolean remove(E obj){
    	return list.remove(obj) == null ? false : true;
    }
    
    // returns a iterator of the elements in the stack.  The elements
    // must be in the same sequence as pop() would return them.    
    public Iterator<E> iterator(){
    	return list.iterator();
    } 

}
