package ecommerce;

/**
 * Created by pc on 3/21/2021.
 */
public class LinkedStack <E> implements Stack <E> {

    SinglyLinkedList <E> list = new SinglyLinkedList<E>();

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public void push(E element) {
        list.addFirst(element);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }
}
