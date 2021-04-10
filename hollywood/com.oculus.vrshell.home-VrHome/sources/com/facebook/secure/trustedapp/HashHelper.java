package com.facebook.secure.trustedapp;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashHelper {
    public static byte[] getHash(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(data);
        return md.digest();
    }

    public static String getBase64Hash(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        return Base64.encodeToString(getHash(data, algorithm), 11);
    }
}
