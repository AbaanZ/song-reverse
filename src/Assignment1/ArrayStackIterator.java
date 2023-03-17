package Assignment1;
import java.util.EmptyStackException;
import java.util.Iterator;

public class ArrayStackIterator implements BKStack, Iterable {

	final int INITIAL_CAPACITY = 50;
	double[] stack = new double [INITIAL_CAPACITY];
	int length = stack.length;
	int newCapacity = INITIAL_CAPACITY;
	int index = 0;
	
	// Instantiate iterator
	MyArrayStackIterator iterator = iterator();
	
	// Iterator constructor
	@Override
	public MyArrayStackIterator iterator() {
		return new MyArrayStackIterator();
	}
	
	
	public void extend() {
		newCapacity = newCapacity * 2;
		double [] newStack = new double [newCapacity];
		for (int i = 0; i < stack.length; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
	}
	
	@Override
	public boolean isEmpty() {
		// If the iterator has a next element, the stack is NOT empty
		if (iterator.hasNext() == true)
			return false;
		return true;
	}

	@Override
	public void push(double d) {
		if (index == newCapacity) {
			extend();
		}
		// Iterator will add an element, then go next
		iterator.addElement(d);
		iterator.goNext();
	}

	@Override
	public double pop() {
		if (isEmpty())
			throw new EmptyStackException();
		// The temp will store the current element, current element is deleted, the popped value is returned
		double temp = stack[index];
		iterator.delete();
		return temp;
	}

	@Override
	public double peek() {
		if (isEmpty())
			throw new EmptyStackException();
		// Return top of the stack
		return (double)iterator.next();
	}
	
	public class MyArrayStackIterator implements Iterator {

		// If the index is not -1, there is a next element
		@Override
		public boolean hasNext() {
			if (index != -1)
				return true;
			return false;
		}

		// Next element top-down
		@Override
		public Object next() {
			return stack[index - 1];
		}
		
		// Add an element 
		public Object addElement(double d) {
			return stack[index] = d;
		}
		
		// Go to the next index
		public void goNext() {
			index++;
		}
		
		// Delete an element
		public void delete() {
			index--;
		}
		
	}


	

}
