package Assignment1;
import java.util.EmptyStackException;

public class ListStack implements BKStack {

		// Doubly linked with a top and bottom
		// Visual representation:
		// [top] - [] - [] -[] - [bottom]
		private ListStackNode top;
		private ListStackNode bottom;
		
		// Linked Stack constructor sets the top and bottom pointing to null
		public ListStack() {
			this.top = null;
			this.bottom = null;
		}
		
		// If the top node is null then there is no nodes in the list
		@Override
		public boolean isEmpty() {
			return top == null;
		}

		// Method to insert a node
		@Override
		public void push(double data) {
			// Creating a new node
			ListStackNode newListStackNode = new ListStackNode(data);
			
			if(isEmpty()) {
				//If the list is empty, we set the new node to point to null and its previous element is also null
				newListStackNode.next = null;
				newListStackNode.prev = null;
				// The top and bottom of the list are both the same node as there is only one node in the entire list as of yet
				top = newListStackNode;
				bottom = newListStackNode;
			} else {
				// If there are already nodes in the list:
				
				// The current top node will point to the newly created node
				top.next = newListStackNode;
				// The new node will point to null
				newListStackNode.next = null;
				// The node before the new node is the node that used to be the top
				newListStackNode.prev = top;
				// The top is now the new node
				top = newListStackNode;
			}
		}

		// Method to pop a node from the stack
		@Override
		public double pop() {
			// The current element is the top element that will be popped, there is a temp element which is the value of the popped element that we will return
			ListStackNode temp = top;
			ListStackNode curr = top;
			
			if (isEmpty()) {
				throw new EmptyStackException();
			} 
			// If there is only one node in the list we just set it to null
			else if (top == bottom) { 
				top = null;
				bottom = null;
				curr = null;
			}
			else {
				// The top element's previous element will point to null
				top.prev.next = null;
				// The top element is now the current element's previous node 
				top = curr.prev;
				// The current node is detached from the stack
				curr.prev = null;
				curr.next = null;
			}
			// Return the data stored in the top that was popped
			return temp.data;
		}

		// Return top of the stack
		@Override
		public double peek() {
			if (isEmpty()) {
				throw new EmptyStackException();
			}
			return top.data;
		}
		

		private class ListStackNode {
			// Node values for data, next pointer, previous pointer
			private double data;
			private ListStackNode next;
			private ListStackNode prev;
			
			// Setter
			public ListStackNode(double data) {
				this.data = data;
			}
		}

	
}
