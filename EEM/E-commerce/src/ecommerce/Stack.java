package ecommerce;

/**
 * Created by pc on 3/21/2021.
 */
public interface Stack <E> {
    //any function in the interface is public by default so no need for declaring that

    boolean isEmpty ();
    int getSize ();
    E top ();
    void push (E element);
    E pop ();

}
