package ecommerce;

/**
 * Created by pc on 3/21/2021.
 */
public interface Queue <E> {
    boolean isEmpty ();
    int size ();
    E first ();
    void enqueue (E e);
    E dequeue ();
}
