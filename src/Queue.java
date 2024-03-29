import java.util.LinkedList;

// Here is some code for two very conventional data structures
public class Queue<E> extends LinkedList<E> {
    public Queue() {}
    
    public void put(E o) {
        addLast(o);
    }
    
    public E remove() {
        if (!this.isEmpty()) {
            return removeFirst();
        } else {
            System.err.println("You can\'t do that!");
            return null;
        } 
    }
    
    public E peek() {
        return getFirst();
    }
}
