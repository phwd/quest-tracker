package com.oculus.secure.trustedapp;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HashHelper {
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";

    public static byte[] getSha1Hash(List<String> list) throws NoSuchAlgorithmException {
        return getHash(list, SHA1);
    }

    public static byte[] getSha256Hash(List<String> list) throws NoSuchAlgorithmException {
        return getHash(list, "SHA-256");
    }

    public static String getBase64Hash(byte[] bArr, String str) throws NoSuchAlgorithmException {
        return Base64.encodeToString(getHash(bArr, str), 11);
    }

    public static byte[] getHash(List<String> list, String str) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        for (String str2 : list) {
            instance.update(str2.getBytes());
        }
        return instance.digest();
    }

    public static byte[] getHash(byte[] bArr, String str) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }
}
