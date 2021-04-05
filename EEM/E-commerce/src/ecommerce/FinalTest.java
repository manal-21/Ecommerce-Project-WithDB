package ecommerce;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by pc on 4/2/2021.
 */
public class FinalTest {
    public static void main(String[] args) throws SQLException {
        User user = new User("Manal","alsaeedimanal96@gmail.com","123456789m");

        if (user.logIn() == true)
        {
            Product p1 = new Product();
            Product p2 = new Product();
            Product p3 = new Product();

            Date date = new Date();

            Orders order = new Orders("1",user,date);

            order.addProduct(p1);
            order.addProduct(p2);
            order.addProduct(p3);


        }
    }
}
