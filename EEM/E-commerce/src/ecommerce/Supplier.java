package ecommerce;

import java.sql.*;

/**
 * Created by pc on 2/11/2021.
 */
public class Supplier extends ProgramFunctions {
    private String name;
    private String email;
    private String phone;
    private String country;
    private String city;
    private String address;

    Connection con = null;
    Statement st = null;
    ResultSet r = null;
    PreparedStatement prs = null;
    String s;

    public Supplier(String name, String email, String phone, String country, String city, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public Supplier() {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.country = "";
        this.city = "";
        this.address = "";
    }

    public void getName() {
        s = "select supplierName from Supplier";

        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
            st = con.createStatement();
            r = st.executeQuery(s);
            prs = con.prepareStatement(s);
            String x = r.getString("supplierName");
            System.out.println(x);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void connection (String string)
    {
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
            st = con.createStatement();
            r = st.executeQuery(string);
            prs = con.prepareStatement(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
