package ecommerce;

import java.sql.*;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by pc on 2/4/2021.
 */
public class Customer {
    private String name ;
    private int age ;
    private String phone;
    private String email;
    private String country ;
    private String city;
    private String address;
    private String password;
    private Date loggingDate ;


    private Connection con = null ;
    private Statement st = null;
    private String string ;
    private PreparedStatement prs = null ;
    private ResultSet rs = null;
    private int id ;

    public Customer(String name, int age, String phone,
                    String email, String country, String city, String address, String password, Date loggingDate) {
        int count =0 ;

        if ( checkString(name) )
        {
            this.name = name;
            count++;
        }

        if ( checkAge(age) )
        {
            this.age = age;
            count++;
        }

        if ( checkPhone(phone) )
        {
            this.phone = phone;
            count++;
        }

        if ( valid22(email) )
        {
            this.email = email;
            count++;
        }

        if ( checkCountry(country) )
        {
            this.country = country;
            count++;
        }

        if ( checkCountry(city) )
        {
            this.city = city;
            count++;
        }

        this.address = address;

        if ( checkPassword(password) )
        {
            this.password = password;
            count++;
        }

        this.loggingDate = loggingDate;

        if (count == 7)
        {
            string = "insert into Customer (customerName,customerAge,email01,country,city,address,phone,password01)"
                    + "values (?,?,?,?,?,?,?,?)";
            inserting();
        }
        else
            System.out.println("X");
    }

    public Customer() {
        this.name = "";
        this.age = 0;
        this.phone = "";
        this.email = "";
        this.country = "";
        this.city = "";
        this.address = "";
        this.password = "";
        this.loggingDate = new Date();
    }

    private void inserting ()
    {
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
            st = con.createStatement();
            prs = con.prepareStatement(string);
            prs.setString(1,this.name);
            prs.setInt(2,this.age);
            prs.setString(3,this.email);
            prs.setString(4,this.country);
            prs.setString(5,this.city);
            prs.setString(6,this.address);
            prs.setString(7, String.valueOf(this.loggingDate));
            prs.setString(8,this.phone);
            prs.setString(9,this.password);
            prs.execute();
            string = "";

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private boolean checkPassword (String x)
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

    private boolean checkPhone (String x)
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

    private boolean checkAge (int x)
    {
        if ( x >= 18 && x <= 120)
        {
            return true;
        }
        else
            return false;
    }

    private boolean checkString (String x)
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

    private boolean checkCountry (String x)
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
}
