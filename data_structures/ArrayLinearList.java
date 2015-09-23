/* Michael Green
   masc0338
*/
package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayLinearList < E > implements LinearListADT < E > {
	private E[] storage;
	private int head, size, maxIndex;
	// For ease of acccess
	private int midIndex, iteratorIndex;

	public ArrayLinearList(int size) {
		// Unchecked cast
		storage = (E[]) new Object[size];
		head = midIndex = (size >> 1);
		maxIndex = size - 1;
		this.size = -1;
	};

	/* The adding and removing functions are basically the same, just reverses of each other with slight
	differences. */
	public boolean addFirst(E obj) {
		if (this.isFull()) {
			return false;
		};
		size++;
		head = head == 0 ? maxIndex + 1 : head;
		storage[--head] = obj;
		return true;
	};

	public boolean addLast(E obj) {
		if (this.isFull()) {
			return false;
		};
		size++;
		int rear = head + size;
		// If we above the max index just take the difference which will wrap us around
		rear = rear > maxIndex ? rear - maxIndex - 1 : rear;
		storage[rear] = obj;
		return true;
	};

	public E removeFirst() {
		if (this.isEmpty()) return null;
		E oldElement = storage[head];
		head = head == maxIndex ? 0 : head + 1;
		size--;
		return oldElement;
	};

	public E removeLast() {
		int rear = head + size;
		// If we above the max index just take the difference which will wrap us around
		rear = rear > maxIndex ? rear - maxIndex - 1 : rear;
		size--;
		return this.isEmpty() ? null : storage[rear];
	};

	public E remove(E obj) {
		// iterator index gets set here
		obj = find(obj);
		if (obj == null) {
			return null;
		} else {
			storage = shiftOver(storage, iteratorIndex);
		}
		return obj;
	};

	public E peekFirst() {
		return storage[head];
	};

	public E peekLast() {
		return storage[head + size];
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
		return size + 1;
	};

	// used to help remove elements in the fastest possible way
	private E[] shiftOver(E[] localArray, int index) {
		/* We want to shift the least number of elements as possible so we just shift towards
		whatever side is closer i.g: if index being removed is in the lower half of the array shift the bottom
		up. Alternatively we can do a shift from the index but we would still have to see which direction
		we move in in order to pick the best side. */

		if ((size + 1 >> 1) > index) {
			E lastElement = storage[head++];
			for (int e = 0; e < size - 1; e++) {
				int wrappedE = e + head > maxIndex ? e + head - maxIndex - 1 : e + head;
				E elementHolder = localArray[wrappedE];
				localArray[wrappedE] = lastElement;
				lastElement = elementHolder;
			};
			// If we shift up we need to move the head up one index
			head++;
		} else {
			E lastElement = storage[size];
			/* Add two because index and size are both zeo indexed and we want element number.
			Also there is no need to decrease size if we are shifting to the left */
			for (int e = index - size + 2; e >= 0; e--) {
				int wrappedE = e <= 0 ? maxIndex + e : e;
				E elementHolder = localArray[wrappedE];
				localArray[wrappedE] = lastElement;
				lastElement = elementHolder;
			}
		};
		// Size will always decrease on shifting and removing
		size--;
		return localArray;
	};

	public Iterator < E > iterator() {
		// Returning an anon class of Iterator
		return new Iterator < E > () {
			// We don't want to touch the the ArrayLinearList instance variables
			int index = head - 1;
			E[] iteratorStorage = storage;

			public boolean hasNext() {
				if (size == -1) {
					return false;
				};
				int nextIndex = index + 1;
				// Making sure index wraps
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex - 1 : nextIndex;
				return index - head != size;
			};
			/* Tries to return next, regardless if there is a value there*/
			public E next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException();
				};
				int nextIndex = ++index;
				nextIndex = nextIndex > maxIndex ? nextIndex - maxIndex - 1 : nextIndex;
				iteratorIndex = nextIndex;
				return iteratorStorage[nextIndex];
			};

			public void remove() {
				// Shift basically handles the removal, we just need to pass it our local array and the index
				iteratorStorage = shiftOver(storage, index);
				storage = iteratorStorage;
			};
		};
	};
}