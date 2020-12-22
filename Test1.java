/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital_signature_generation;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

/**
 *
 * @author Akshay Akole
 */
public class Test1 {
    
    public static void main(String [] ar)
    {
     String str ="KL";
        String query = "select * from key_info where text ='"+str+"'";
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                System.out.println("Pk="+rs.getString("public_key"));
                Blob blob = rs.getBlob("signature");
                byte [] bytes = blob.getBytes(1l, (int)blob.length());
                for(int i=0; i<bytes.length;i++)
                    System.out.println(bytes[i]);
            }
        }
        catch(Exception e){}
             
    
    }
}
