package data_structures;

import java.util.Iterator;

public class ArrayLinearList<E> implements LinearListADT<E> {
	private E obj;
	private E[] storage;
	private int head,tail,size;

	/* Tells us whether or not we have gone around the entire circle which is useful
	for determining if the list is full or empty. We don't need to initiliaze this
	as the default value is false. */
	private boolean wrapped;


	// Creates a generic array with a fixed size.
	public ArrayLinearList(int size){
		// Unchecked cast of generic element array to an object
		storage = (E[]) new Object[size];
		// Head and tail both get intialized to the middle of the array (n/2 - 1)
		head = this.tail = (size >> 1) - 1 ;
		this.size = 0;
	};

	public boolean addFirst(E obj){
		if(!this.isFull()){
			//Toggle our wrapped flag if we reach the start of the array
			boolean checkWrap = head == 0;
			wrapped = checkWrap ? !wrapped : wrapped;
			head = checkWrap ? --size : --head;
			storage[head] = obj;
			return true;
		}
		return false;
	};

	public boolean addLast(E obj){
		if(!this.isFull()){
			// Toggle our wrapped flag if its at the end of the array 
			boolean checkWrap = tail == --size;
			wrapped = checkWrap ? !wrapped : wrapped;
			tail = checkWrap ? 0 : ++tail;
			storage[tail] = obj;
			return true;
		}
		return false;
	};   

	public E removeFirst(){
		E first = storage[head++];
		return first;
	};   

	public E removeLast(){
		E last = storage[tail--];
		return last;
	};   

	public E remove(E obj){
		return obj;
	};

	public E peekFirst(){
		return obj;
	};

	public E peekLast(){
		return obj;
	};

	public boolean contains(E obj){
		return true;
	};  

	public E find(E obj){
		return obj;
	};       

	public void clear(){
	};

	public boolean isEmpty(){
		return head == tail && !wrapped;
	};

	public boolean isFull(){
		return head == tail && wrapped;
	};    

	public int size(){
		return size;
	};

	public Iterator<E> iterator(){
		return new Iterator<E>(){
			int index = 0;
			E[] storage = this.storage;

			public boolean hasNext(){
				return storage.length - index == 0 ? false : true;
			};

			public E next(){
				if(this.hasNext()){
					return storage[index++];
				}
				return obj;
			};

			public void remove(){
				// removes this index
			};
		};
	};    

}



















