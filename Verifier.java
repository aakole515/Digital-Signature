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
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Akshay Akole
 */
public class Verifier {
    byte[] encKey;
    byte[] sigToVerify;
    byte[] buffer;
    byte[] plaintext;
    X509EncodedKeySpec pubKeySpec;
    KeyFactory keyFactory ;
    Signature sig;
    PublicKey pubKey;
    BufferedReader reader;
    Reader inputString;
    
    public Verifier(byte[] plain_text , byte[] signature , byte[] public_key)
    {
         encKey = public_key;
         plaintext=plain_text;
         //encKey = public_key.getBytes(Charset.forName("UTF-8"));
         sigToVerify = signature;
    }
    
    public void checkSignature() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, IOException, SignatureException
    {
        pubKeySpec = new X509EncodedKeySpec(encKey);
         
        keyFactory = KeyFactory.getInstance("DSA", "SUN");
        pubKey = keyFactory.generatePublic(pubKeySpec);
         
         sig = Signature.getInstance("SHA1withDSA", "SUN");
         sig.initVerify(pubKey);
         
         sig.update(plaintext);
         
         boolean verifies = sig.verify(sigToVerify);
         
         System.out.println("signature verifies: " + verifies);
 
    }
    
}
