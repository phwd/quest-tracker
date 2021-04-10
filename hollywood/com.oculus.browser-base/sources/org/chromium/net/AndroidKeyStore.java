package org.chromium.net;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidKeyStore {
    public static byte[] encryptWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance(str);
            try {
                instance.init(1, privateKey);
                return instance.doFinal(bArr);
            } catch (Exception e) {
                StringBuilder k = AbstractC2531fV.k("Exception while encrypting input with ", str, " and ");
                k.append(privateKey.getAlgorithm());
                k.append(" private key (");
                k.append(privateKey.getClass().getName());
                k.append("): ");
                k.append(e);
                AbstractC1220Ua0.a("AndroidKeyStore", k.toString(), new Object[0]);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            AbstractC1220Ua0.a("AndroidKeyStore", "Cipher " + str + " not supported: " + e2, new Object[0]);
            return null;
        }
    }

    public static String getPrivateKeyClassName(PrivateKey privateKey) {
        return privateKey.getClass().getName();
    }

    public static boolean privateKeySupportsCipher(PrivateKey privateKey, String str) {
        try {
            Cipher.getInstance(str).init(1, privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        } catch (Exception e) {
            AbstractC1220Ua0.a("AndroidKeyStore", "Exception while checking support for " + str + ": " + e, new Object[0]);
            return false;
        }
    }

    public static boolean privateKeySupportsSignature(PrivateKey privateKey, String str) {
        try {
            Signature.getInstance(str).initSign(privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return false;
        } catch (Exception e) {
            AbstractC1220Ua0.a("AndroidKeyStore", "Exception while checking support for " + str + ": " + e, new Object[0]);
            return false;
        }
    }

    public static byte[] signWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Signature instance = Signature.getInstance(str);
            try {
                instance.initSign(privateKey);
                instance.update(bArr);
                return instance.sign();
            } catch (Exception e) {
                StringBuilder k = AbstractC2531fV.k("Exception while signing message with ", str, " and ");
                k.append(privateKey.getAlgorithm());
                k.append(" private key (");
                k.append(privateKey.getClass().getName());
                k.append("): ");
                k.append(e);
                AbstractC1220Ua0.a("AndroidKeyStore", k.toString(), new Object[0]);
                return null;
            }
        } catch (NoSuchAlgorithmException e2) {
            AbstractC1220Ua0.a("AndroidKeyStore", "Signature algorithm " + str + " not supported: " + e2, new Object[0]);
            return null;
        }
    }
}
