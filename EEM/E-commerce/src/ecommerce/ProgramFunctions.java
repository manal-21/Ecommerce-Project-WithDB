package ecommerce;

import net.ucanaccess.commands.UpdateCommand;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by pc on 2/20/2021.
 */

public class ProgramFunctions {

    static ArrayList<Category> categories;
    static ArrayList<Product>products;
    static CircularQ<Orders> queue ;

    public ProgramFunctions() {
        categories =new ArrayList<>();
        products=new ArrayList<>();
        queue = new CircularQ<>();
    }

    protected static Connection connection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
        return con;
    }

    private boolean checkNumber (String x)
    {
        int count = 0;
        char [] ch = x.toCharArray();

        for (int i = 0; i <ch.length ; i++) {
            if (ch[i] > 47 & ch [i] < 58)
            {
                count++;
            }
            else
                break;
        }

        if (count == ch.length)
            return true;
        else
            return false;
    }

    protected boolean checkString (String x)
    {
        int count = 0;
        char [] ch = x.toCharArray();
        for (int i = 0; i <ch.length ; i++) {
            if ( (ch[i] >= 65 && ch[i] <= 90) || (ch[i] >= 97 && ch[i] <= 122) )
            {
                count++;
            }
            else
                break;
        }

        if (count == ch.length)
            return true;
        else
            return false;
    }

    private boolean valid22 (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        else return pat.matcher(email).matches();

    }

    protected boolean checkEmail (String x) throws SQLException {
        boolean f = false;
        if (valid22(x) == true)
        {
            Connection con = connection();
            if (con != null)
            {
                int count = 0;
                String s = "select email from Customer";
                Statement st = con.createStatement();
                ResultSet r = st.executeQuery(s);
                ArrayList<String> emails = new ArrayList<>();

                while (r.next())
                {
                    emails.add(r.getString("email"));
                }

                for (String y:emails
                        ) {
                    if (x.equals(y))
                    {
                        count++;
                        break;
                    }
                    else
                        continue;
                }
                if (count == 0)
                    f = true;
                else
                    f = false;

            }
            else
            {
                JOptionPane j = new JOptionPane();
                j.setSize(200,100);
                j.showMessageDialog(null,"problem in connecting to the Database!\nWe will fix it as soon as possible.\nPlease check " +
                                "out later. "
                        ,"Connection problem",2);
            }
        }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Please check your Email."
                    ,"Invalid Email Form",2);
        }
        return f;
    }

    protected boolean checkPasswordValidity (String x)
    {
        char [] ch = x.toCharArray();
        int cInt = 0 ;
        int cChar = 0 ;
        if (ch.length >= 9)
        {
            for (int i = 0; i <ch.length ; i++) {
                if (ch[i] >= 48 & ch [i] <= 57)
                {
                    cInt++;
                }
                else
                    cChar++;
            }

            if ( cInt != 0 && cChar != 0 )
                return true;
            else
                return false;
        }
        else
            return false;
    }

    protected boolean checkPhone (String x)
    {
        char [] ch = x.toCharArray();
        if (checkNumber(x))
            if ( ch.length >=7 && ch.length <= 15 )
            {
                return true;
            }
            else
                return false;
        else
            return false;
    }

    protected boolean checkTownName (String x)
    {
        int count = 0;
        int chCount = 0;
        char [] ch = x.toCharArray();

        for (int i = 0; i < ch.length ; i++) {
            if (ch[i] > 47 & ch [i] < 58)
                break;
            else
            {
                if ( (ch[i] >= 65 && ch[i] <= 90) || (ch[i] >= 97 && ch[i] <= 122) )
                {
                    count++;
                }
                else
                    chCount++;
            }
        }

        if ( (count == ch.length-1 && chCount == 1) || (count == ch.length && chCount == 0))
            return true;
        else
            return false;
    }

    protected boolean checkCountry (String country) throws SQLException {
        int count = 0;
        boolean f = false;

        if (checkTownName(country))
        {
            Connection con = connection();
            if (con != null)
            {
                String s = "select countryName from Country";
                Statement st = con.createStatement();
                ResultSet r = st.executeQuery(s);
                ArrayList <String> x = new ArrayList<>();

                while (r.next())
                {
                    x.add(r.getString("countryName"));
                }

                for (String str:x
                        ) {
                    if (str.equals(country))
                    {
                        count++;
                        break;
                    }

                    else
                        continue;
                }

                if (count != 0)
                {
                    f = true;
                }

                else
                    f = false;
            }
            else
            {
                JOptionPane j = new JOptionPane();
                j.setSize(200,100);
                j.showMessageDialog(null,"problem in connecting to the Database!\nWe will fix it as soon as possible.\nPlease check " +
                                "out later. "
                        ,"Connection problem",2);
            }
        }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Please check your country name."
                    ,"Invalid Country name.",2);
        }

        return f;
    }

    protected boolean checkCity (String city , String country) throws SQLException {
        boolean f = false;
        if ( checkTownName(city) )
        {
            if ( checkCityInDB(city) )
            {
                Connection con = connection();
                if (con != null)
                {
                    int countryId = countryId(country);
                    int countryCityId = countryCityId(city);

                    if ( countryId == countryCityId )
                    {
                        f = true;
                    }
                    else
                    {
                        JOptionPane j = new JOptionPane();
                        j.setSize(200,100);
                        j.showMessageDialog(null,"No Match Between Country and City."
                                ,"Connection problem",2);
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

            }

            else
            {
                JOptionPane j = new JOptionPane();
                j.setSize(200,100);
                j.showMessageDialog(null,"This City is Not in Our List."
                        ,"Connection problem",2);
            }
        }
        else
        {
            JOptionPane j = new JOptionPane();
            j.setSize(200,100);
            j.showMessageDialog(null,"Invalid City Name."
                    ,"Connection problem",2);
        }
        return f;
    }

    private boolean checkCityInDB (String city) throws SQLException {
        boolean f = false;
        Connection con = connection();
        int count = 0;
        if ( con != null )
        {
            String s = "select cityName from City";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(s);
            ArrayList<String> names = new ArrayList<>();

            while (r.next())
            {
                names.add(r.getString("cityName"));
            }

            for (String str:names
                 ) {
                if ( str.equals(city))
                {
                    count++;
                    break;
                }
                else
                    continue;
            }
        }
        if (count != 0)
            f = true;
        else
            f = false;
        return f;
    }

    protected boolean checkAge (int x)
    {
        if ( x >= 18 && x <= 120)
        {
            return true;
        }
        else
            return false;
    }

    private int countryId (String country) throws SQLException {
        Connection con = connection();
        int id = -1;
        if (con != null)
        {
            String s = "select * from Country";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(s);
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Integer> ids = new ArrayList<>();

            while (r.next())
            {
                names.add(r.getString("countryName"));
                ids.add(r.getInt("ID"));
            }

            int i = names.indexOf(country);
            id = ids.get(i);
        }
        return id;
    }

    private int countryCityId (String city) throws SQLException {
        int id = -1 ;
        Connection con = connection();
        if (con != null)
        {
            String s = "select * from City";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(s);
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Integer> ids = new ArrayList<>();

            while (r.next())
            {
                names.add(r.getString("cityName"));
                ids.add(r.getInt("countryId"));
            }

            int i = names.indexOf(city);
            id = ids.get(i);
        }
        return id;
    }

    public void selectcategory() {
        boolean f=true;
        if (f == true)
        {
            try {
                Connection con = DriverManager.getConnection
                        ("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
                Statement st = con.createStatement();
                ResultSet r = st.executeQuery("select * from category");

                while (r.next())
                {
                    categories.add(new Category(r.getString("category name"), r.getString("ID")));
                }



            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


    }

    public void selectproduct(){
        boolean f=true;
        if (f == true)
        {
            try {
                Connection con = DriverManager.getConnection
                        ("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
                Statement st = con.createStatement();
                ResultSet r = st.executeQuery("select ID,productName,price from product");

                while (r.next())
                {
                    products.add(new Product(r.getString("ID"), r.getString("productName"),r.getString("price")));
                }

                for (Product p:products
                     ) {
                    System.out.println(p.toString());
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void show ()
    {
        for (int i = 0; i < queue.size() ; i++) {
            System.out.println(queue.first());
            queue.rotate();
        }
    }

    public Orders finishOrder ()
    {
        if (queue.isEmpty())
            return null;
        return queue.dequeue();
    }

    public static void main(String[] args) throws SQLException {
        Connection con = connection();
        String s = "select * from Customer"+
                "where ? = ?";
        PreparedStatement prs = con.prepareStatement(s);
        System.out.println();
        UpdateCommand u ;
    }

}
