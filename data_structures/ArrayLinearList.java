package data_structures;

import java.util.Iterator;

public class ArrayLinearList<E> implements LinearListADT<E> {
	private E obj;
	private E[] storage;
	private int head,size,maxIndex,midIndex;

	public ArrayLinearList(int size){
		// Unchecked cast
		storage = (E[]) new Object[size];
		midIndex = (size >> 1) - 1;
		head = maxIndex = size - 1;
		this.size = -1;
	};

	/* The adding and removing functions are basically the same, just reverses of each other with slight
	differences. */
	public boolean addFirst(E obj){
		head = head == 0 ? maxIndex : head;
		storage[head--] = obj;
		size++;
		return true;
	};

	public boolean addLast(E obj){
		int rear = head + size;
		// If we above the max index just take the difference which will wrap us around
		rear = rear >= maxIndex ? rear - maxIndex : rear;
		storage[rear] = obj;
		size++;
		return true;
	};   

	public E removeFirst(){
		if(this.isEmpty())
			return null;
		head = head == maxIndex ? -1 : head;
		size--;
		return storage[head++];
	};   

	public E removeLast(){
		return this.isEmpty() ? null : storage[1 + head + size--];
	};   

	public E remove(E obj){
		return obj;
	};

	public E peekFirst(){
		return storage[head];
	};

	public E peekLast(){
		return storage[head + size];
	};

	public boolean contains(E obj){
		return true;
	};  

	public E find(E obj){
		this.iterator();
		return obj;
	};       

	public void clear(){
		head = midIndex;
		size = 0;
	};

	public boolean isEmpty(){
		return size == -1;
	};

	public boolean isFull(){
		return size == maxIndex;
	};    

	public int size(){
		return size + 1;
	};

	// used to help remove elements in the fastest possible way
	public static E[] shift(E[] unshiftedArray,int index){
		/* We want to shift the least number of elements as possible so we just shift towards
		whatever index is closer to a head or head + size */
		int side = (size >> 1) > index ? head : head + size;
		for(int e = 0; e < index; e++){
			E oldElement = unshiftedArray[e];
			E unshiftedArray[e] = unshiftedArray[e + side];
		};
	};

	// this needs to get finished
	public Iterator<E> iterator(){
		return new Iterator<E>(){
			int index = -1;
			E[] storage = this.storage;

			public boolean hasNext(){
				int nextIndex = index + head + 1;
				// Making sure index wraps
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex : nextIndex;
				return nextIndex <= size && nextIndex != head;
			};
			/* Tries to return next, regardless if there is a value there*/
			public E next(){
				if(!this.hasNext()){
					throw new NullPointerException("No more values.");
				};
				int nextIndex = ++index + head;
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex : nextIndex;
				return storage[nextIndex];
			};

			public void remove(){
				// shift(storage, index);
				// make the local iterator storage the instance of the list storage
				this.storage = storage;
				index++;
			};
		};
	};    

}



