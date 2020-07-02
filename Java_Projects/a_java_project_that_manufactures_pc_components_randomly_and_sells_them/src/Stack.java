import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> implements IStack<T> {
	private T[] stack;
	private int topIndex;
	private boolean isInitialized;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	public Stack() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public Stack(int initialCapacity) {
		checkCapacity(initialCapacity);
		stack  = (T[]) new Object[initialCapacity];
		topIndex = -1;
		isInitialized = true;

	}
	public T[] getStack() {
		return this.stack;
	}

	public void push(T entry) {
		checkInitialization();
		ensureCapacity();
		stack[topIndex + 1] = entry;
		topIndex++;
	}

	public T peek() {
		checkInitialization();
		
		if(isEmpty()) {
			throw new EmptyStackException();
		}else {
			return stack[topIndex];
		}
	}
	
	public T pop() {
		checkInitialization();
		
		if(isEmpty()) {
			throw new EmptyStackException();
		}else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}
	
	public boolean isEmpty() {
		
		return topIndex < 0;
	}
	
	@Override
	public void clear() {
		
		while(!isEmpty()) {
			pop();
		}
		
	}
	
	private void checkCapacity(int initialCapacity) {

		if ((initialCapacity > MAX_CAPACITY) && (initialCapacity < 0)) {
			initialCapacity = DEFAULT_CAPACITY;
		}
	}

	private void ensureCapacity() {
		
		if(topIndex == stack.length -1) {
			int newCapacity = stack.length * 2;
			checkCapacity(newCapacity);
			stack = Arrays.copyOf(stack, newCapacity);
		}
	}
	
	private void checkInitialization() {
		
		if(!isInitialized) {
			@SuppressWarnings("unchecked")
			T[] tempStack = (T[]) new Object[DEFAULT_CAPACITY];
			stack = tempStack;
			topIndex = -1;
			isInitialized = true;
		}
	}


}
