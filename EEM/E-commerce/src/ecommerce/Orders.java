package ecommerce;

import java.util.Date;

/**
 * Created by pc on 3/21/2021.
 */
public class Orders {

    private String id;
    private User customer ;
    private Date date ;
    private LinkedStack<Product> stack ;

    public Orders(String id, User customer, Date date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.stack = new LinkedStack<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct (){
        return stack.pop();
    }

    public void addProduct (Product p){
        stack.push(p);
    }

    public Product removeProduct (Product p){
        if (stack.isEmpty())
            return null;
        else
        {
            if (p == stack.top())
                return stack.pop();
            LinkedStack<Product> stack1 = new LinkedStack<>();
            while (!stack.isEmpty() && p != stack.top())
                stack1.push(stack.pop());
            if (stack.isEmpty())
                return null;
            Product x = stack.pop();
            while (!stack1.isEmpty())
                stack.push(stack1.pop());
            return x;
        }
    }

    public void show (){
        LinkedStack<Product> p = new LinkedStack<>();

        while (!stack.isEmpty())
        {
            System.out.println(stack.top().toString());
            p.push(stack.pop());
        }

        while (!p.isEmpty())
        {
            stack.push(p.pop());
        }
    }

    public void print ()
    {
        String s = "id : '" + id + '\n' +
                "customer : " + customer +
                "date : " + date +
                '\n';
        System.out.println(s);
        show();
    }

}
