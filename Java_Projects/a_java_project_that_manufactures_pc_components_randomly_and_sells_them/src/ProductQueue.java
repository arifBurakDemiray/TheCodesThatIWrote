

public class ProductQueue<T> implements IQueue<T> {
	
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private static final int DEF_CAP = 50;
	
	public ProductQueue() {
		this(DEF_CAP);
	}
	
	public ProductQueue(int size) {
		
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[size+1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = size;
	}
	public T[] getQu() {
		return this.queue;
	}
	public int getBack() {
		return this.backIndex;
	}

	public void enqueue(T newEntry) {
		
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
		
	}


	public T dequeue() {
		T front =  null;
		
		if(!isEmpty()) {
			front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
		}
		
		return front;
	}


	public T getFront() {
		T front =  null;
		
		if(!isEmpty()) {
			front = queue[frontIndex];
		}
		
		return front;
	}


	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % queue.length);
	}


	public void clear() {
		
		while(!isEmpty()) {
			dequeue();
		}
		
	}
	

	private void ensureCapacity() {
		
		if(frontIndex == (backIndex + 2) % queue.length) {
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[2 * oldSize];
			queue = tempQueue;
			
			for(int index = 0; index < oldSize - 1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize -2;
		}
	}

}

            
