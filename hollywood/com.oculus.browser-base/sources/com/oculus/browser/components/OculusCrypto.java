package com.oculus.browser.components;

import android.security.keystore.KeyGenParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OculusCrypto {

    /* renamed from: a  reason: collision with root package name */
    public static OculusCrypto f9718a;
    public byte[] b;

    public static String getEncryptionPassword() {
        if (f9718a == null) {
            f9718a = new OculusCrypto();
        }
        OculusCrypto oculusCrypto = f9718a;
        KeyStore.PrivateKeyEntry c = oculusCrypto.c();
        try {
            if (oculusCrypto.b == null) {
                File file = new File(oculusCrypto.d());
                if (!file.exists()) {
                    return oculusCrypto.a();
                }
                oculusCrypto.b = oculusCrypto.e(file);
            }
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidKeyStoreBCWorkaround");
            instance.init(2, c.getPrivateKey());
            return new String(instance.doFinal(oculusCrypto.b));
        } catch (NoSuchAlgorithmException e) {
            AbstractC1220Ua0.a("OculusCrypto", "No such Algorithm", e);
            return "";
        } catch (Exception e2) {
            AbstractC1220Ua0.a("OculusCrypto", "Exception loading secret: ", e2);
            return "";
        }
    }

    public static native void nativeInit();

    public String a() {
        KeyStore.PrivateKeyEntry c = c();
        if (c == null) {
            return "";
        }
        try {
            String b2 = b();
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidKeyStoreBCWorkaround");
            instance.init(1, c.getCertificate().getPublicKey());
            CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(d()), instance);
            cipherOutputStream.write(b2.getBytes("UTF-8"));
            cipherOutputStream.close();
            return b2;
        } catch (NoSuchAlgorithmException e) {
            AbstractC1220Ua0.a("OculusCrypto", "No such Algorithm", e);
            return "";
        } catch (Exception e2) {
            AbstractC1220Ua0.a("OculusCrypto", "Exception writing secret", e2);
            return "";
        }
    }

    public String b() {
        SecureRandom secureRandom = new SecureRandom();
        String str = "";
        for (int i = 0; i < 64; i++) {
            StringBuilder i2 = AbstractC2531fV.i(str);
            i2.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".charAt(secureRandom.nextInt(62)));
            str = i2.toString();
        }
        return str;
    }

    public KeyStore.PrivateKeyEntry c() {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load(null);
            if (!instance.containsAlias("oculus_crypto_password")) {
                KeyPairGenerator instance2 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                instance2.initialize(new KeyGenParameterSpec.Builder("oculus_crypto_password", 3).setEncryptionPaddings("PKCS1Padding").setKeySize(2048).build());
                instance2.generateKeyPair();
            }
            return (KeyStore.PrivateKeyEntry) instance.getEntry("oculus_crypto_password", null);
        } catch (Exception e) {
            AbstractC1220Ua0.a("OculusCrypto", "exception generating key", e);
            return null;
        }
    }

    public String d() {
        return ContextUtils.getApplicationContext().getDir("oc_secrets", 0) + "/crypto_secret";
    }

    public byte[] e(File file) {
        byte[] bArr = new byte[256];
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = 0;
        while (i < 256) {
            try {
                int read = fileInputStream.read(bArr, i, 256 - i);
                if (read == -1) {
                    break;
                }
                i += read;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        fileInputStream.close();
        return Arrays.copyOfRange(bArr, 0, i);
        throw th;
    }
}
