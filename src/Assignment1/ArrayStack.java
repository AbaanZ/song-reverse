package Assignment1;
import java.util.EmptyStackException;

public class ArrayStack implements BKStack {
	
	// Initial capacity is set to 50.
	final int INITIAL_CAPACITY = 50;
	// Creating initial array that has the INITIAL_CAPACITY size of 50
	double[] stack = new double [INITIAL_CAPACITY];
	int length = stack.length;
	// The new capacity is set to the initial capacity; will come in handy for extending
	int newCapacity = INITIAL_CAPACITY;
	// index starts with zero
	int index = 0;
	
	// Method to double array size if the limit is reached
	public void extend() {
		// the new capacity is the current capacity times 2
		newCapacity = newCapacity * 2;
		// New array
		double [] newStack = new double [newCapacity];
		// Copy the contents of the old array into the new array
		for (int i = 0; i < stack.length; i++) {
			newStack[i] = stack[i];
		}
		
		// The stack is now the new stack
		stack = newStack;
	}
	
	@Override
	public boolean isEmpty() {
		return index == -1;
	}

	@Override
	public void push(double d) {
		// If the stack is at its limit, extend it by doubling the size
		if (index == newCapacity) {
			extend();
		}
		
		// Push element
		stack[index] = d;
		index++;
	}

	@Override
	public double pop() {
		if (isEmpty())
			throw new EmptyStackException();
		
		// Pop element and return the popped element value
		double temp = stack[index];
		index--;
		return temp;
	}

	// Return top of stack
	@Override
	public double peek() {
		if (isEmpty())
			throw new EmptyStackException();
		return stack[index-1];
	}
}
