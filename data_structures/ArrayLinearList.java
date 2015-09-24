/* Michael Green
   masc0338
*/
package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayLinearList < E > implements LinearListADT < E > {
	private E[] storage;
	/* Head is where our pseudo-array begins, size is the inclusive distance to the tail, or the number
	of elements in the list - 1, however you want to see it, we store it this way because it's much more
	useful as the only time you need the actual size is when using the size function. 
	*/
	private int head, size, maxIndex;
	/* For ease of acccess. iteratorIndex is set by the iterator so we don't need to basically rewrite
	the find method inside the remove function just to call remove(), instead we can just use what's 
	already there. */
	private int midIndex, iteratorIndex;

	public ArrayLinearList(int size) {
		// Unchecked cast
		storage = (E[]) new Object[size];
		head = midIndex = (size >> 1);
		maxIndex = size - 1;
		// accessing the instance's size rather than the local scope
		this.size = -1;
	};

	public boolean addFirst(E obj) {
		if (this.isFull()) {
			return false;
		};
		// We need to increase the size and decrease the head to add to the front
		size++;
		head = getWrappedHead(--head);
		storage[head] = obj;
		return true;
	};

	public boolean addLast(E obj) {
		if (this.isFull()) {
			return false;
		};
		// When we add to last, only size changes not head
		size++;
		storage[getWrappedTail(head)] = obj;
		return true;
	};

	public E removeFirst() {
		if (this.isEmpty()) return null;
		E oldElement = storage[head];
		head = getWrappedHead(++head);
		size--;
		return oldElement;
	};

	public E removeLast() {
		if(this.isEmpty()) return null;
		int tail = getWrappedTail(head);
		size--;
		return storage[tail];
	};

	public E remove(E obj) {
		/* iterator index gets set here, ideally we should check if the find function was
		the last function called with the same parameter and whether or not it was successful, 
		if it was we wouldn't need to call find again for removal. */
		obj = find(obj);
		if (obj == null) {
			return null;
		} else {
			shiftOver(iteratorIndex);
		}
		return obj;
	};

	public E peekFirst() {
		return  isEmpty() ? null : storage[head];
	};

	public E peekLast() {
		return isEmpty() ? null : storage[getWrappedTail(head)];
	};

	public boolean contains(E obj) {
		return find(obj) != null;
	};

	public E find(E obj) {
		Iterator storageIterator = iterator();
		while (storageIterator.hasNext()) {
			E current = (E)(storageIterator.next());
			if (((Comparable < E > ) obj).compareTo(current) == 0) {
				return current;
			};
		};
		return null;
	};

	public void clear() {
		head = midIndex;
		size = -1;
	};

	public boolean isEmpty() {
		return size == -1;
	};

	public boolean isFull() {
		return size == maxIndex;
	};

	public int size() {
		// because we started counting size at 0 rather than 1 we add 1 to the returned value
		return size + 1;
	};

	public Iterator < E > iterator() {
		// Returning an anon iterator
		return new Iterator < E > () {
			// We don't want to touch the instance of head
			int index = getWrappedHead(head - 1);

			public boolean hasNext() {
				/* We start at just below the head to make sure we iterate over the first element
				*/
				return index - head < size;
			};
			/* Tries to return next, regardless if there is a value there*/
			public E next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException();
				};				
				iteratorIndex = getWrappedHead(++index);
				return storage[iteratorIndex];
			};

			public void remove() {
				/* Shift basically handles the removal, we just need to pass it our local array and the index,
				Removal is also	*/
				shiftOver(getWrappedHead(index));
			};
		};
	};

	/***********************
		UTILITY FUNCTIONS	
	************************
	Trying to extrapulate some of the functionality...
	*/
	private int getWrappedTail(int head){
		int tail = head + size;
		// If we are above the max index just take the difference which will wrap us around
		return tail > maxIndex ? tail - maxIndex - 1 : tail;
	};
	// Different from tale as you have to compensate for going from 0 -> -n rather than just maxN -> 0+remainder
	private int getWrappedHead(int unwrappedHead){
		if(unwrappedHead < 0){
			return maxIndex;
		}
		else if(unwrappedHead > maxIndex){
			return unwrappedHead - maxIndex - 1;
		}
		else{
			return unwrappedHead;
		}
	};

	private void shiftOver(int removalIndex) {
		/* We want to shift the least number of elements as possible so we just shift towards
		whatever side the index is closer to i.g: if index being removed is closer to the tail than
		the head we just shift from the tail to the removalIndex and decrease the size. If it was closer
		to the head we just shift from the head up to the removedIndex, decrease the size, AND increment
		the head. This makes it so we only have to shift n/2 times. */
		if ( removalIndex + head <= removalIndex + getWrappedTail(head)) {
			for(int element = removalIndex; element > head; element--){
				element = getWrappedHead(element);
				storage[element] = storage[element - 1];
			};
			// If we shift up we need to move the head up one index
			head++;
		} else {
			for(int element = removalIndex; element != getWrappedTail(head); element++ ){
				element = getWrappedHead(element);
				storage[element] = storage[getWrappedHead(element + 1)];
			}
		};
		// Size will always decrease whichever side you shift towards
		size--;
	};
}