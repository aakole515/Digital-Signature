/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital_signature_generation;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.*;
import java.util.*;
/**
 *
 * @author Akshay Akole
 */
public class Key_Generator extends Generation {
    byte[] input;
    KeyPair pair;
    PrivateKey priv;
    PublicKey pub;
    //creation key pair geneartor object
    public Key_Generator(byte [] text)
    {
       input=text;
    }
    
    public byte[] generateKey() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, IOException
    {
        
        byte [] key;
        //KeyPairGeneartor signature getInstance("Algo","Provider");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA" , "SUN");
        
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyPairGenerator.initialize(1024, random);
        
        pair = keyPairGenerator.generateKeyPair();
        priv = pair.getPrivate();
        pub = pair.getPublic();
        /*System.out.println("PUUUUUUUUUUUUUUUUUUUUUUUUU"+pub);
        System.out.println("Public Key is :" + pub.getEncoded());
        //jLabel2.setText(pub.toString());
        System.out.println("Private key is : " + priv);*/
        
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(priv);
        
       // String text = jTextPane1.getText();
            byte[] buffer = new byte[1024];
            int len;
            //System.out.println("reader::::\n"+reader.read());
            dsa.update(input);
        byte[] realSig = dsa.sign();
           //String str = new String (realSig , "UTF8");
       /* System.out.println("Signature : \n");
        for(int i=0; i< realSig.length;i++)
        {
            System.out.print(realSig[i]);
            
        }*/
        System.out.print("\n");
        //System.out.println("Str="+str);
        //byte [] get = str.getBytes();
        //System.out.println("Byte ==="+get);
        return realSig;
    }
    
    byte[] getPublicKey()
    {
        return pub.getEncoded();
    }
    
    String getPrivateKey()
    {
        return priv.toString();
    }
}
