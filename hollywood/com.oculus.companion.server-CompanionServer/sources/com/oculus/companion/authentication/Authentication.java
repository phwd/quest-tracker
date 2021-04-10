package com.oculus.companion.authentication;

import android.util.Log;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Authentication {
    private byte[] authChallenge = null;
    private byte[] privatekey = null;
    private final int privatekeysize;
    private byte[] publickey = null;
    private final int publickeysize;
    private byte[] sharedSecret = null;

    public static native byte[] nativeDecrypt(byte[] bArr, byte[] bArr2);

    public static native byte[] nativeEncrypt(byte[] bArr, byte[] bArr2);

    public static native byte[] nativeGenerateKeyPair();

    public static native int nativeGetPublicKeySize();

    public static native int nativeGetSecretKeySize();

    public static native byte[] nativeKeyExchange(byte[] bArr, byte[] bArr2);

    public Authentication() {
        System.loadLibrary("authenticate");
        this.publickeysize = nativeGetPublicKeySize();
        this.privatekeysize = nativeGetSecretKeySize();
    }

    public void keyExchangeLS(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("peer public key null or empty");
        }
        this.sharedSecret = nativeKeyExchange(bArr, this.privatekey);
    }

    public byte[] getPublickeyLS() {
        return this.publickey;
    }

    public byte[] generateECDHKeysLS() throws InvalidAlgorithmParameterException {
        byte[] nativeGenerateKeyPair = nativeGenerateKeyPair();
        if (nativeGenerateKeyPair != null) {
            int length = nativeGenerateKeyPair.length;
            int i = this.publickeysize;
            int i2 = this.privatekeysize;
            if (length == i + i2) {
                this.publickey = new byte[i];
                this.privatekey = new byte[i2];
                System.arraycopy(nativeGenerateKeyPair, 0, this.publickey, 0, i);
                System.arraycopy(nativeGenerateKeyPair, this.publickeysize, this.privatekey, 0, this.privatekeysize);
                return this.publickey;
            }
            Log.e("CompanionAuthentication", "Expected return length of " + this.publickeysize + this.privatekeysize + " but got " + nativeGenerateKeyPair.length);
            throw new InvalidAlgorithmParameterException();
        }
        Log.e("CompanionAuthentication", "keys is null");
        throw new InvalidAlgorithmParameterException();
    }

    public byte[] encryptLS(byte[] bArr) {
        byte[] bArr2 = this.sharedSecret;
        if (bArr2 == null) {
            Log.e("CompanionAuthentication", "encryptLS: sharedSecret is NULL!!");
            throw new IllegalArgumentException("encryptLS: sharedSecret is NULL!!");
        } else if (bArr != null && bArr.length != 0) {
            return nativeEncrypt(bArr2, bArr);
        } else {
            Log.e("CompanionAuthentication", "encryptLS: content is null or empty");
            throw new IllegalArgumentException("encryptLS: content is NULL or empty");
        }
    }

    public byte[] decryptLS(byte[] bArr) {
        byte[] bArr2 = this.sharedSecret;
        if (bArr2 == null) {
            Log.e("CompanionAuthentication", "decryptLS: sharedSecret is NULL!!");
            throw new IllegalArgumentException("decryptLS: sharedSecret is NULL!!");
        } else if (bArr != null && bArr.length != 0) {
            return nativeDecrypt(bArr2, bArr);
        } else {
            Log.e("CompanionAuthentication", "decryptLS: cipher is null or empty");
            throw new IllegalArgumentException("decryptLS: cipher is NULL or empty");
        }
    }

    public byte[] generateAuthenticationSession() {
        this.authChallenge = new byte[16];
        new SecureRandom().nextBytes(this.authChallenge);
        return this.authChallenge;
    }

    public boolean authenticationSessionInProgress() {
        return this.authChallenge != null;
    }

    public boolean verifyAuthenticationSession(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            Log.e("CompanionAuthentication", "verifyAuthenticationSession: InvalidParameter(s)");
            throw new IllegalArgumentException("verifyAuthenticationSession: InvalidParameter(s)");
        } else if (authenticationSessionInProgress()) {
            try {
                Mac instance = Mac.getInstance("HmacSHA256");
                instance.init(new SecretKeySpec(bArr2, "AES"));
                return MessageDigest.isEqual(instance.doFinal(this.authChallenge), bArr);
            } catch (NoSuchAlgorithmException e) {
                Log.e("CompanionAuthentication", "No such algorithm: " + e.getMessage());
                throw new IllegalArgumentException("verifyAuthenticationSession: No such algorithm: " + e.getMessage());
            } catch (InvalidKeyException e2) {
                Log.e("CompanionAuthentication", "Invalid key: " + e2.getMessage());
                throw new IllegalArgumentException("verifyAuthenticationSession: Invalid key: " + e2.getMessage());
            }
        } else {
            Log.e("CompanionAuthentication", "verifyAuthenticationSession: no authentication session in progress");
            throw new IllegalArgumentException("verifyAuthenticationSession: no authentication session in progress");
        }
    }
}
