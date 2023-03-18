/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import java.security.MessageDigest;
import java.util.logging.Logger;


/**
 *
 * @author Usuario
 */
public class Login {
    
    public static String sha1(String message) {
        try{
            return encrypt(message, "SHA1");
        }catch(NullPointerException e){
            return null;
        }
    }
    
    public static String md5(String message) {
        return encrypt(message, "MD5");
    }
    
    private static String encrypt(String message, String type){
        try {
            MessageDigest md;
            byte[] buffer, digest;
            StringBuilder hash = new StringBuilder();
            try {
                buffer = message.getBytes("UTF-8");
                md = MessageDigest.getInstance(type);
            }catch(Exception e) {
                throw new RuntimeException(e);
            }
            md.update(buffer);
            digest = md.digest();
            for (byte b:digest) {
                hash.append(String.format("%02x", b & 0xff));
            }
            return hash.toString();
        }catch(NullPointerException e){
            return null;
        }
    }
 
    
    
}
