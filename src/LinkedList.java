import java.util.NoSuchElementException;

/**
 * Linked list class that
 * operates similar to the
 * java lib list class, this
 * class has a private Node class
 * to keep track of the elements.
 *
 * @param <T> generic type.
 * @version 1.0.
 * @author Nick
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs a new empty list.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Inserts the given object at the beginning of this list.
     *
     * @param obj the object to be inserted.
     */
    public void insertFirst(T obj) {
        Node<T> newNode = new Node<T>(obj);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.data = obj;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Inserts the given object at the end of this list.
     *
     * @param obj the object to be inserted.
     */
    public void insertLast(T obj) {
        Node<T> newNode = new Node<T>(obj);
        Node<T> last = head;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * Inserts the given object at the given index in this list.
     *
     * @param index the position in the list where the object should be inserted.
     * @return true if the object is inserted, false otherwise.
     */
    public T getAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Removes the first object in this list.
     *
     * @return the removed object.
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T lastData = tail.data;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
        return lastData;
    }

    /**
     * Removes the last object in this list.
     */
    public void removeFirst() {
        try {
            if (size == 1) {
                tail = null;
            }
            Node<T> temp = head.next;
            head = null;
            head = temp;
        } catch (NoSuchElementException e) {
            System.out.println("List empty.");
            e.printStackTrace();
        }
        size--;
    }

    /**
     * Removes the object at the given index from this list.
     *
     * @param index the position of the object to be removed.
     */
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<T> temp = current.next;
            current.next = temp.next;
            temp = null;
            size--;
        }
    }

    /**
     * Returns a space-separated list of the elements in the list. If the list contains no elements,
     * return an empty string ""
     */
    public String toString() {
        String s = "";
        //create a local var for traversal
        Node<T> current = head;

        while (current != null) {
            s += current.data;
            current = current.next;
        }
        return s;
    }

    /**
     * Returns true if this list contains no elements, false otherwise.
     *
     * @return true if this list contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void emptyList() {
        while (!isEmpty()) {
            removeLast();
        }
    }

    /**
     * Private Node class used by the LinkedList class.
     *
     * @param <T> generic type.
     */
    private class Node<T> {
        public T data;
        public Node<T> next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
