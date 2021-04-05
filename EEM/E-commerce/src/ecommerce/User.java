package ecommerce;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by pc on 2/12/2021.
 */
public class User extends ProgramFunctions {
    private String name ;
    private int age ;
    private String phone;
    private String email;
    private String country ;
    private String city;
    private String address;
    private String password;
    private String loggingDate ;
    private LinkedList<Product> favorites;

    public User(String name, int age,
                String phone, String email, String country, String city, String address,
                String password, String loggingDate) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.city = city;
        this.address = address;
        this.password = password;
        this.loggingDate = loggingDate;
        favorites = new LinkedList<>();
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
        this.name = "";
        this.age = 0;
        this.phone = "";
        this.email = "";
        this.country = "";
        this.city = "";
        this.address = "";
        this.password = "";
        this.loggingDate = "";
    }

    public void inserting () throws SQLException {
        Connection con = connection();
        if ( con != null )
        {
            String s = "insert into Customer (customerName,customerAge,phone,email,country,city,address,password,logDate)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement prs = null;

                prs = con.prepareStatement(s) ;
                prs.setString(1,this.name);
                prs.setInt(2,this.age);
                prs.setString(3,this.phone);
                prs.setString(4,this.email);
                prs.setString(5,this.country);
                prs.setString(6,this.city);
                prs.setString(7,this.address);
                prs.setString(8,this.password);
                prs.setString(9,this.loggingDate);
                prs.execute();
        }
        else
            System.out.println("problem in connecting to the Database!\nWe will fix it as soon as possible.\nPlease check " +
                    "out later. ");
    }

    public boolean signIn () throws SQLException {
        boolean f = false;
        int count = 0 ;
        if (checkString(name)) {    count++;    }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Please enter your name properly.\nYour name can not include numbers or symbols."
                ,"Invalid name",2);
        }

        if (checkAge(age)) {    count++;    }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Your age must be between 18 and 120 years."
                    ,"Invalid age",2);
        }

        if (checkPhone(phone)) {    count++;    }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Please check your phone number."
                    ,"Invalid phone number",2);
        }

        if (checkEmail(email)) {    count++;    }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"You have registered using this Email before.\nYou can't have two accounts " +
                            "with the same Email."
                    ,"Invalid Email",2);
        }

        if (checkCountry(country)) {    count++;    }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"The country you entered is not in our list. "
                    ,"Invalid County",2);
        }

        if (checkPasswordValidity(password)) {  count++;    }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Your Password should contain 9 characters,\n at least  one letter" +
                            "and one number. "
                    ,"Invalid Password",2);
        }

        if ( checkCity(city,country) )
            count++;

        if ( count == 7)
        {
            f = true;
        }

        else
        {
            f = false;
        }

        return f;
    }

    public boolean logIn () throws SQLException {
        boolean f = false;
        Connection con = connection();
        if ( con != null )
        {
            String s = "select * from Customer";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(s);
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<String> passwords = new ArrayList<>();

            while (r.next())
            {
                names.add(r.getString("customerName"));
                emails.add(r.getString("email"));
                passwords.add(r.getString("password"));
            }

            int iName = names.indexOf(name);
            int iEmail = emails.indexOf(email);
            int iPass = passwords.indexOf(password);

            if ( iName == iEmail )
            {
                if ( iName == iPass )
                    f = true;
                else
                {
                    JOptionPane j = new JOptionPane();
                    j.setSize(200,100);
                    j.showMessageDialog(null,"No Match Between Password and User Name."
                            ,"Invalid Password",2);
                }
            }
            else
            {
                JOptionPane j = new JOptionPane();
                j.setSize(200,100);
                j.showMessageDialog(null,"No Match Between Email and User Name."
                        ,"Invalid Password",2);
            }
        }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"problem in connecting to the Database!\nWe will fix it as soon as possible.\nPlease check " +
                            "out later. "
                    ,"Connection problem",2);
        }
        return f;
    }

    public void addFav (Product p){
        favorites.push(p);
        System.out.println("Product Added.");
    }

    public Product removeFav (Product p){
        Product x ;
        if (favorites.indexOf(p) != -1)
        {
            x = favorites.get(favorites.indexOf(p));
            favorites.remove(p);
            return x;
        }
        return null;
    }
}
