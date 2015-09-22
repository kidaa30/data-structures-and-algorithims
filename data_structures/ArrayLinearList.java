package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayLinearList<E> implements LinearListADT<E> {
	private E obj;
	// change to private later
	private E[] storage;
	private int head,size,maxIndex;
	// For ease of acccess
	private int midIndex,iteratorIndex;

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
		System.out.println("head is: " + head);
	};


	/* The adding and removing functions are basically the same, just reverses of each other with slight
	differences. */
	public boolean addFirst(E obj){
		if(this.isFull()){
			return false;
		};
		size++;
		head = head == 0 ? maxIndex + 1 : head;
		storage[--head] = obj;
		return true;
	};

	public boolean addLast(E obj){
		if(this.isFull()){
			return false;
		};
		size++;
		int rear = head + size;
		// If we above the max index just take the difference which will wrap us around
		rear = rear > maxIndex ? rear - maxIndex - 1 : rear;
		storage[rear] = obj;
		return true;
	};   

	public E removeFirst(){
		if(this.isEmpty())
			return null;
		E oldElement = storage[head];
		head = head == maxIndex ? 0 : head++;
		size--;
		return oldElement;
	};   

	public E removeLast(){
		int rear = head + size;
		// If we above the max index just take the difference which will wrap us around
		rear = rear > maxIndex ? rear - maxIndex - 1 : rear;
		size--;
		return this.isEmpty() ? null : storage[rear];
	};   

	public E remove(E obj){
		// iterator index gets set here
		obj = find(obj);
		if(obj == null){
			return null;
		}
		else{
			storage = shiftOver(storage,iteratorIndex);
		}
		return obj;
	};

	public E peekFirst(){
		return storage[head];
	};

	public E peekLast(){
		return storage[head + size];
	};

	public boolean contains(E obj){
		return find(obj) != null;
	};  

	public E find(E obj){
		Iterator storageIterator = iterator();
		while(storageIterator.hasNext()){
			E current = (E)(storageIterator.next());
			if( ((Comparable<E>)obj).compareTo(current) == 0){
				return current;
			};
		};
		return null;
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
		up. Alternatively we can do a shift from the index but we would still have to see which direction
		we move in in order to pick the best side. */
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
		// Returning an anon class of Iterator
		return new Iterator<E>(){
			// We don't want to touch the the ArrayLinearList instance variables
			int index = head - 1;
			int count = -1;
			E[] iteratorStorage = storage;

			public boolean hasNext(){
				if(size == -1){
					return false;
				};
				int nextIndex = index + 1;
				// Making sure index wraps
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex - 1: nextIndex;
				return count != size;
			};
			/* Tries to return next, regardless if there is a value there*/
			public E next(){
				if(!this.hasNext()){
					throw new NoSuchElementException();
				};
				int nextIndex = ++index;
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex - 1: nextIndex;
				iteratorIndex = nextIndex;
				count++;
				return iteratorStorage[nextIndex];
			};

			public void remove(){
				// Shift basically handles the removal, we just need to pass it our local array and the index
				iteratorStorage = shiftOver(storage,index);
				storage = iteratorStorage;
			};
		};
	};
}



