/* Michael Green
   masc0338
 * A class assignment for CS310, a linked list implementation using an interface provided to us called
 * ArrayLinearList
 * @author Michael Green
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearList < E > implements LinearListADT < E >{
	private Node head, tail;
	private int size;

	/* Linked List constructor, we start with two head and tail nodes that point to each other. */
	public LinearList() {
		size = 0;
		head = tail = new Node();
		head.next = tail;
		tail.prev = head;
	};

	/* Wrapper class that allows us to store data along with the location of other data relatively */
	private class Node {
		Node prev, next;
		E data;

		public Node() {};

		/* This parameterized constructor lets us create and assign values more quickly for new nodes. */
		public Node(E data, Node prev, Node next) {
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
	};

	public boolean addFirst(E obj) {
		Node oldFirst = head.next, newNode = new Node(obj, head, oldFirst);
		head.next = newNode;
		oldFirst.prev = newNode;
		size++;
		/* We aren't compensating for lack of memory, so we always return true when adding */
		return true;
	};

	public boolean addLast(E obj) {
		Node oldLast = tail.prev, newNode = new Node(obj, oldLast, tail);
		tail.prev = newNode;
		oldLast.next = newNode;
		size++;
		return true;
	};

	public E removeFirst() {
		return isEmpty() ? null : removeLink(head.next);
	};

	public E removeLast() {
		return isEmpty() ? null : removeLink(tail.prev);
	};

	public E peekFirst() {
		return head.next.data;
	};

	public E peekLast() {
		return tail.prev.data;
	}

	public void clear() {
		size = 0;
		head.next = tail;
		tail.prev = head;
	};

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return false;
	};
	// Only reason we keep a size is this method
	public int size() {
		return size;
	};
	/* Both the find and remove methods just use the iterator we created */
	public E remove(E obj) {
		Iterator < E > links = iterator();
		E match = null;
		while (links.hasNext()) {
			if (((Comparable < E > ) obj).compareTo(links.next()) == 0) {
				match = obj;
				links.remove();
			}
		}
		return match;
	};

	public E find(E obj) {
		for (E nodeData: this) {
			if (((Comparable < E > ) obj).compareTo(nodeData) == 0) {
				return nodeData;
			}
		};
		return null;
	};

	public boolean contains(E obj) {
		return find(obj) == null ? false : true;
	};

	/* Utility function that facilitates node deletion by deleting all links to that node */
	private E removeLink(Node node) {
		Node linkPrev = node.prev, linkNext = node.next;
		linkNext.prev = linkPrev;
		linkPrev.next = linkNext;
		size--;
		return node.data;
	};

	public Iterator < E > iterator() {

		return new Iterator < E > () {
			Node current = head;

			public boolean hasNext() {
				return current.next != tail;
			};

			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				};
				current = current.next;
				return current.data;
			};

			public void remove() {
				removeLink(current);
			};
		};
	};
}