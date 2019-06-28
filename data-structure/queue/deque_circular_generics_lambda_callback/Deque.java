import java.util.function.Consumer;

public class Deque<T> {
    private DequeCircularElement<T> head;
    private DequeCircularElement<T> tail;
	public Deque() {
		head = tail = null;
	}
	public boolean isEmpty() {
		return head == null && tail == null;
	}
	public boolean isFull() {
		return false;
	}
	public void addFirst(T o) {
		System.out.println("");
        System.out.println("Added in first: " + o);
        DequeCircularElement<T> e = new DequeCircularElement<T>(o);
        if (head != null) {
            head.setPrev(e); 
        }
        e.setNext(head);
        head = e;
        if (tail == null) {
            tail = head;
        }
        tail.setNext(head);
        head.setPrev(tail);
        System.out.println("New head: " + head.getValue());
        System.out.println("New tail: " + tail.getValue());
    }
    public void addLast(T o) {
    	System.out.println("");
        System.out.println("Added in last: " + o);
        DequeCircularElement<T> e = new DequeCircularElement<T>(o);
        if (tail != null) {
        	tail.setNext(e);
        }
        e.setPrev(tail);
        tail = e; 
        if(head == null) {
        	head = tail;
        }
        tail.setNext(head);
        head.setPrev(tail);
        System.out.println("New head: " + head.getValue());
        System.out.println("New tail: " + tail.getValue());
    }
	public T removeFirst() throws DequeRemoveEmptyListException {
        if (this.isEmpty()) {
            throw new DequeRemoveEmptyListException("The deque is empty!");
        } else {
        	T o = head.getValue();
            head = head.getNext(); 
            if (head == null) { 
                tail = null;
            } else {
                head.setPrev(null); 
            }
            System.out.println("\nRemoved in first: " + o);
            System.out.println("New head: " + head.getValue());
            System.out.println("New tail: " + tail.getValue());
            return o;
            //evitar o loop remove do circular
        }
    }
    public T removeLast() throws DequeRemoveEmptyListException {
        if (this.isEmpty()) {
            throw new DequeRemoveEmptyListException("The deque is empty!");
        } else {
        	T o = tail.getValue();
            tail = tail.getPrev(); 
            if (tail == null) {
                head = null;
            } else {
                tail.setNext(null); 
            }
            System.out.println("");
            System.out.println("Removed in last: " + o);
            System.out.println("New head: " + head.getValue());
            System.out.println("New tail: " + tail.getValue());
            return o;
           //evitar o loop remove do circular
        }
    }
    public void clear() {
        System.out.println("");
        while (!this.isEmpty()) {
            try {
				this.removeFirst();
			} catch (DequeRemoveEmptyListException e) {
				e.printStackTrace();
			}
        }
        System.out.println("The deque is clean!");
    }
    public void listing(Consumer<T> lambda) {
        DequeCircularElement<T> e = head;
        System.out.println("Starting listing... ");
        while (e != null) {
            lambda.accept(e.getValue());
            e = e.getNext();  
            if(e == head) {
            	break;
            }
        }
        System.out.println("Endend.\n");
    } 
}
