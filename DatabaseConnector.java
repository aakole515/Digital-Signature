/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital_signature_generation;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Akshay Akole
 */
public class DatabaseConnector {
    String query;
    byte[] plain_text= null;
    byte[] public_key=null;
    byte [] signature;
    String text =null;
    public DatabaseConnector(String text, byte[] pub , byte[] signature)
    {
         query = "insert into key_info values (?, ?, ?)";
         plain_text = text.getBytes();
         this.signature=signature;
         this.public_key =pub;
    }
    public DatabaseConnector(String text)
    {
        query = "select * from key_info where text = '"+text+"'";
    }
    public void insert_Record()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            Statement st = con.createStatement();
           // String query = "INSERT INTO userinfo values ('"+user1+"','"+pswd+"' )";
           /* st.executeUpdate(query);
            System.out.println("Record Inserted");*/
           PreparedStatement pt = con.prepareStatement(query);
           pt.setBytes(1,plain_text);
           pt.setBytes(2,public_key);
           pt.setBytes(3, signature);
           System.out.println("Plain text");
           for(int i=0;i<plain_text.length;i++)
                System.out.print(plain_text[i]);
           System.out.println("\nPublic Key\n");
           for(int i=0;i<public_key.length;i++)
                System.out.print(public_key[i]);
          System.out.println("\nSignature\n");
          for(int i=0;i<signature.length;i++)
                System.out.print(signature[i]);
          System.out.println("\nPublic Key\n");
          for(int i=0;i<public_key.length;i++)
            System.out.print(public_key[i]);
           if(pt.executeUpdate()==1)
           {
            System.out.println("Record Inserted");
             
           }
           else
              System.out.println("Record not inserted");
        }catch(Exception e){
        System.out.println("Error : "+e);
        }
    }
    
    public void fetch_record()
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            Statement st = con.createStatement();
            //String query = "select * from userinfo where username = '"+user1+"' and password ='"+psed+"'";
            //st.executeUpdate(query);
            //System.out.println("Record Inserted");
            ResultSet rs = st.executeQuery(query);
            //if(rs.next())
           // {
                rs.next();
                Blob blob2 = rs.getBlob("text");
                plain_text = blob2.getBytes(1l, (int)blob2.length());
                
                Blob blob = rs.getBlob("public_key");
                public_key = blob.getBytes(1l, (int)blob.length());
                
                Blob blob1 = rs.getBlob("signature");
                signature = blob1.getBytes(1l, (int)blob1.length());
               // signature = rs.getBytes("signature");
               
               System.out.print("Text:\n");
               for(int i=0; i<plain_text.length;i++)
                    System.out.print(plain_text[i]);
               
               System.out.print("\nPublic Key:\n");
               for(int i=0; i<public_key.length;i++)
                    System.out.print(public_key[i]);
               
               System.out.print("\nSignature:\n");
               for(int i=0; i<signature.length;i++)
                    System.out.print(signature[i]);
               System.out.println("\n");
            //}
        }catch(Exception e){
        System.out.println("Signature Varifies: False");
        }
    }
    
    public byte[] getPlainText()
    {
        return plain_text;
    }
    
    public byte[] getPublicKey()
    {
        return public_key;
    }
    
    public byte[] getSignature()
    {
        return signature;
    }
}
