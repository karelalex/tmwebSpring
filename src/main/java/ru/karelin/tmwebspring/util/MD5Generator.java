package ru.karelin.tmwebspring.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {
    public static String generate(String input){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] byteOut = new byte[0];
        if (md5 != null) {
            byteOut = md5.digest(input.getBytes());
        }
        return String.format("%032x", new BigInteger(1, byteOut));
    }
}
