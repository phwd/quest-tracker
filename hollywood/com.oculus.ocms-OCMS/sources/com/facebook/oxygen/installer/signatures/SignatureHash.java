package com.facebook.oxygen.installer.signatures;

import android.content.pm.Signature;
import android.util.Base64;
import com.facebook.secure.trustedapp.HashHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureHash {
    public static String hashBase64(Signature signature) {
        try {
            MessageDigest instance = MessageDigest.getInstance(HashHelper.SHA1);
            byte[] byteArray = signature.toByteArray();
            instance.update(byteArray, 0, byteArray.length);
            return convertToBase64(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertToBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }
}
