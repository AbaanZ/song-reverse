package Assignment1;

import java.util.EmptyStackException;
import java.util.Iterator;

public class ListStackIterator implements BKStack, Iterable {
	// Doubly linked with a top and bottom
	// Visual representation:
	// [top] - [] - [] -[] - [bottom]
	private ListStackNode top;
	private ListStackNode bottom;
	private MyLLStackIterator iterator = iterator();
	private ListStackNode curr;
	
	// Iterator constructor
	@Override
	public MyLLStackIterator iterator() {
		return new MyLLStackIterator();
	}
	
	// Linked Stack constructor
	public ListStackIterator() {
		this.top = null;
		this.bottom = null;
	}
	
	// List is empty if the iterator does not have any next elements
	@Override
	public boolean isEmpty() {
		return !(iterator.hasNext());
	}

	@Override
	public void push(double data) {
		ListStackNode newListStackNode = new ListStackNode(data);
		
		if(isEmpty()) {
			newListStackNode.next = null;
			newListStackNode.prev = null;
			
			top = newListStackNode;
			bottom = newListStackNode;
		} else {
			// Iterator will add element, then go next
			iterator.addElement(newListStackNode);
			iterator.goNext(newListStackNode);
		}
	}

	@Override
	public double pop() {
		ListStackNode temp = top;
		curr = top;

		if (isEmpty()) {
			throw new EmptyStackException();
		} 
		else if (top == bottom) { 
			// Iterator deletes the only element in the list
			iterator.delete();
		}
		else {
			top.prev.next = null;
			// Iterator sets top equal to the next element top-down
			top = (ListStackNode) iterator.next();
			curr.prev = null;
			
		}
		return temp.data;
	}

	@Override
	public double peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return top.data;
	}

	private class ListStackNode {
		private double data;
		private ListStackNode next;
		private ListStackNode prev;
		
		public ListStackNode(double data) {
			this.data = data;
		}
	}
	
	// 	Iterator class
	public class MyLLStackIterator implements Iterator {
		
		// Check if node has a next element after it
		@Override
		public boolean hasNext() {
			if (top == null) 
				return false;
			return true;
		}
		
		// Go to the next element top-down
		@Override
		public Object next() {
			return curr.prev;
		}
		
		// Add new node in the list
		public Object addElement(ListStackNode newListStackNode) {
			return top.next = newListStackNode;
		}
		
		// Connect the current node to the new node and go there
		public Object goNext(ListStackNode newListStackNode) {
			newListStackNode.next = null;
			newListStackNode.prev = top;
			top = newListStackNode;
			return top;
		}
		
		// Remove a node
		public void delete() {
			top = null;
			bottom = null;
			curr = null;
		}
		
		
	}

}
