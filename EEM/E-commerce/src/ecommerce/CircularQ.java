package ecommerce;

/**
 * Created by pc on 3/21/2021.
 */
public class CircularQ <E> implements CircularlyQueue <E> {

    private CircularlyLinkedList <E> list = new CircularlyLinkedList<E>();

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    @Override
    public void rotate() {
        list.rotate();
    }
}
