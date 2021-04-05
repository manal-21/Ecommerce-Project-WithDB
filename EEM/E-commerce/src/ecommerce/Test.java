package ecommerce;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by pc on 2/5/2021.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList <Supplier> arrayList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\project\\EEM\\E-commerce11.accdb");
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("select * from Supplier");
            while (r.next())
            {
                arrayList.add(new Supplier(r.getString("supplierName"),r.getString("Email"),r.getString("phone"),r.getString("country"),r.getString("city")
                ,r.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Supplier E:arrayList
             ) {
            System.out.println(E.toString());
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
