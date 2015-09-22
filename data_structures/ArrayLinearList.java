package data_structures;

import java.util.Iterator;

public class ArrayLinearList<E> implements LinearListADT<E> {
	private E obj;
	// change to private later
	private E[] storage;
	private int head,size,maxIndex,midIndex;

	public ArrayLinearList(int size){
		// Unchecked cast
		storage = (E[]) new Object[size];
		head = midIndex = (size >> 1);
		maxIndex = size - 1;
		this.size = -1;
	};

	public void printArray(){
		System.out.print("\nCurrent State of Array: [");
		for(E element : storage){
			System.out.print(element + ", ");
		}
		System.out.println("]\n");
	};


	/* The adding and removing functions are basically the same, just reverses of each other with slight
	differences. */
	public boolean addFirst(E obj){
		size++;
		head = head == 0 ? maxIndex : head;
		storage[--head] = obj;
		return true;
	};

	public boolean addLast(E obj){
		size++;
		int rear = head + size;
		// If we above the max index just take the difference which will wrap us around
		rear = rear >= maxIndex ? rear - maxIndex : rear;
		storage[rear] = obj;
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
		size = -1;
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
	public E[] shiftOver(E[] localArray,int index){
		/* We want to shift the least number of elements as possible so we just shift towards
		whatever side is closer i.g: if index being removed is in the lower half of the array shift the bottom
		up.*/
		if ( (size >> 1) > index ){
			for(int e = head; e < index; e++){
				int wrappedE = e > maxIndex ? e - maxIndex : e;
				E oldElement = localArray[wrappedE];
				localArray[wrappedE] = localArray[e];
				localArray[e] = oldElement;
			};
			// If we shift up we need to move the head up one index
			head++;
		}
		else {
			for(int e = size; e > index; e--){
				int wrappedE = e > maxIndex ? e - maxIndex : e;
				E oldElement = localArray[wrappedE];
				localArray[wrappedE] = localArray[e];
				localArray[e] = oldElement;
			}
		};
		// Size will always decrease on shifting and removing
		size--;
		return localArray;
	};

	// this needs to get finished
	public Iterator<E> iterator(){
		return new Iterator<E>(){
			// We don't want to touch the the ArrayLinearList instance variables
			int index = head;
			E[] storage = this.storage;

			public boolean hasNext(){
				int nextIndex = index + 1;
				// Making sure index wraps
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex : nextIndex;
				return nextIndex <= size && nextIndex != head;
			};
			/* Tries to return next, regardless if there is a value there*/
			public E next(){
				if(!this.hasNext()){
					throw new NullPointerException("No more values.");
				};
				int nextIndex = index++;
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex : nextIndex;
				return storage[nextIndex];
			};

			public void remove(){
				// Shift basically handles the removal, we just need to pass it our local array and the index
				this.storage = shiftOver(storage,index);
			};
		};
	};    

}



