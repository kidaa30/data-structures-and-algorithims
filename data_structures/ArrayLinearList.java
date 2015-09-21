package data_structures;

import java.util.Iterator;

public class ArrayLinearList<E> implements LinearListADT<E> {
	private E obj;
	private E[] storage;
	private int head,tail, size;

	// Creates a generic array with a fixed size.
	public ArrayLinearList(int size){
		// Unchecked cast of generic element array to an object
		storage = (E[]) new Object[size];
		// Head and tail both get intialized to the middle of the array (n/2 - 1)
		head = this.tail = (size >> 1) - 1 ;
		this.size = 0;
	};

	public boolean checkWrap(boolean side){
		// Depending on the side passed it determines if there is a wrap at that side
		return side ? tail == storage.length  - 1 : head == 0;
	}
	/* 
	The adding and removing functions are basically the same, just reverses of each other with slight
	differences.
	*/
	public boolean addFirst(E obj){
		if(this.isEmpty()){
			storage[head] = obj;
			size++;
			return true;
		}
		if(this.isFull())
			return false;
		head = checkWrap(false) ? storage.length  - 1 : --head;
		storage[head] = obj;
		size++;
		return true;
	};

	public boolean addLast(E obj){
		if(this.isEmpty()){
			storage[head] = obj;
			size++;
			return true;
		}
		if(this.isFull())
			return false;
		tail = checkWrap(true) ? 0 : ++tail;
		storage[tail] = obj;
		size++;
		return true;
	};   

	public E removeFirst(){
		if(this.isEmpty())
			return null;
		E removedElement = storage[head];
		head = checkWrap(true) ? 0 : ++head;
		size--;
		return removedElement;
	};   

	public E removeLast(){
		if(this.isEmpty())
			return null;
		E removedElement = storage[tail];
		tail = checkWrap(false) ? 0 : --tail;
		size--;
		return removedElement;
	};   

	public E remove(E obj){
		return obj;
	};

	public E peekFirst(){
		return storage[head];
	};

	public E peekLast(){
		return storage[tail];
	};

	public boolean contains(E obj){
		return true;
	};  

	public E find(E obj){
		this.iterator();
		return obj;
	};       

	public void clear(){
		head = tail = storage.length/2 - 1;
		size = 0;
	};

	public boolean isEmpty(){
		return size == 0;
	};

	public boolean isFull(){
		return size == storage.length;
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



















