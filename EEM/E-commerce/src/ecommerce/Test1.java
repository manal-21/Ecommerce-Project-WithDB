package ecommerce;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pc on 2/12/2021.
 */
public class Test1 {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
            Scanner sc = new Scanner(System.in);
            String c = "Yemen";
            String s = "select email from Customer";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(s);
            ArrayList<String> x = new ArrayList<>();
            String y = "alsaeedimanal96@gmail.com";
            int count = 0;

            while (r.next())
            {
                x.add(r.getString("email"));
            }

            for (String str:x
                 ) {
                System.out.println(str);
                if (str.equals(y) )
                    count = count +1;
            }
            System.out.println(count);
        } catch (SQLException e) {
            System.out.println("Connection Failed.");
        }

    }
}
