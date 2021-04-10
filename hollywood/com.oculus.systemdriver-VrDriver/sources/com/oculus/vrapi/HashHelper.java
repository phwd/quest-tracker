package com.oculus.vrapi;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HashHelper {
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";

    public static byte[] getSha1Hash(List<String> stringList) throws NoSuchAlgorithmException {
        return getHash(stringList, SHA1);
    }

    public static byte[] getSha256Hash(List<String> stringList) throws NoSuchAlgorithmException {
        return getHash(stringList, "SHA-256");
    }

    public static byte[] getHash(List<String> stringList, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        for (String str : stringList) {
            md.update(str.getBytes());
        }
        return md.digest();
    }

    public static byte[] getHash(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(data);
        return md.digest();
    }

    public static String getBase64Hash(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        return Base64.encodeToString(getHash(data, algorithm), 11);
    }
}
