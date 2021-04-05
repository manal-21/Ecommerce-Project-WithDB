package ecommerce;

/**
 * Created by pc on 3/21/2021.
 */
public class Product {
    int id;
    String name;
    double price;
    int qunantete;

    public Product(int id, String name, double price, int qunantete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qunantete =qunantete;
    }

    public Product() {
    }

    public Product(String id, String string, String price) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQunantete() {
        return qunantete;
    }

    public void setQunantete(int qunantete) {
        this.qunantete = qunantete;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", desc='" + qunantete + '\'' +
                '}';
    }
}

