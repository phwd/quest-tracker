package com.facebook.secure.trustedapp;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {
    public static byte[] getHash(byte[] bArr, String str) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public static String getBase64Hash(byte[] bArr, String str) throws NoSuchAlgorithmException {
        return Base64.encodeToString(getHash(bArr, str), 11);
    }
}
