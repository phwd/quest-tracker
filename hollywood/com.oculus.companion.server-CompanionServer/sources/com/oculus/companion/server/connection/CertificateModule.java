package com.oculus.companion.server.connection;

import android.content.Context;
import android.util.Log;
import com.oculus.companion.server.CompanionUtil;
import com.oculus.devicecertservice.DeviceCert;
import com.oculus.devicecertservice.DeviceCertInterface;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateModule {
    private static final DeviceCertInterface.SecureState CERT_STATE = DeviceCertInterface.SecureState.CURRENT;
    private static boolean PROVISIONED = false;
    private static final String TAG = "CertificateModule";
    private static KeyStore keyStore;
    private final String certFile = "cert";
    private final String certHollywoodFile = "cert_hollywood";
    private final Context context;
    private DeviceCert deviceCert = null;
    private final String keystoreFile = "keystore";
    private final String keystoreFilePassword = "qwerty";
    private final String keystoreHollywoodFile = "keystore_hollywood";

    public CertificateModule(Context context2) {
        this.context = context2;
        try {
            this.deviceCert = new DeviceCert();
            if (this.deviceCert != null && this.deviceCert.containsAlias("device_identity")) {
                PROVISIONED = true;
            }
            Log.d(TAG, "Successfully loaded DeviceCert()");
        } catch (RuntimeException e) {
            PROVISIONED = false;
            String str = TAG;
            Log.e(str, "Can not load DeviceCert module (RT): " + e.getMessage());
        } catch (Exception e2) {
            PROVISIONED = false;
            String str2 = TAG;
            Log.e(str2, "Can not load DeviceCert module: " + e2.getMessage());
        }
    }

    public byte[] sign(byte[] bArr) {
        if (!PROVISIONED) {
            return signTemp(bArr);
        }
        try {
            return this.deviceCert.sign("device_identity", "SHA256withRSA", bArr);
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Exception during signing: " + e.getMessage());
            return null;
        }
    }

    public byte[] loadCertificate() {
        if (!PROVISIONED) {
            Log.e(TAG, "!!!DeviceCert is null. Returning temp certificate!!!");
            return loadCertificateTemp();
        }
        try {
            X509Certificate loadCertificate = this.deviceCert.loadCertificate("device_identity", CERT_STATE);
            if (loadCertificate != null) {
                return loadCertificate.getEncoded();
            }
            return null;
        } catch (CertificateEncodingException e) {
            String str = TAG;
            Log.e(str, "Certificate encoding error: " + e.getMessage());
            return null;
        } catch (RuntimeException e2) {
            String str2 = TAG;
            Log.e(str2, "Runtime exception loading certificate: " + e2.getMessage());
            return null;
        } catch (Exception e3) {
            String str3 = TAG;
            Log.e(str3, "Exception loading certificate: " + e3.getMessage());
            return null;
        }
    }

    public byte[] getCertificateHash() {
        try {
            return MessageDigest.getInstance("SHA-256").digest(loadCertificate());
        } catch (NoSuchAlgorithmException e) {
            String str = TAG;
            Log.e(str, "Sha256 not supported: " + e.getMessage());
            return null;
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "Exception: " + e2.getMessage());
            return null;
        }
    }

    private byte[] signTemp(byte[] bArr) {
        if (keyStore == null) {
            loadKeyStoreTemp();
        }
        try {
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initSign((PrivateKey) keyStore.getKey("test", "qwerty".toCharArray()));
            instance.update(bArr);
            byte[] sign = instance.sign();
            instance.initVerify(keyStore.getCertificate("test").getPublicKey());
            instance.update(bArr);
            if (instance.verify(sign)) {
                return sign;
            }
            Log.e(TAG, "Signature verification failed!");
            return null;
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Exception in signTemp: " + e.getMessage());
            return null;
        }
    }

    private void loadKeyStoreTemp() {
        try {
            InputStream openRawResource = this.context.getResources().openRawResource(this.context.getResources().getIdentifier(CompanionUtil.isHollywoodDevice(this.context) ? "keystore_hollywood" : "keystore", "raw", this.context.getPackageName()));
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(openRawResource, "qwerty".toCharArray());
            openRawResource.close();
        } catch (KeyStoreException e) {
            String str = TAG;
            Log.e(str, "KeyStoreException " + e.getMessage());
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "Exception " + e2.getMessage());
        }
    }

    private byte[] loadCertificateTemp() {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            InputStream openRawResource = this.context.getResources().openRawResource(this.context.getResources().getIdentifier(CompanionUtil.isHollywoodDevice(this.context) ? "cert_hollywood" : "cert", "raw", this.context.getPackageName()));
            Certificate generateCertificate = instance.generateCertificate(openRawResource);
            openRawResource.close();
            return generateCertificate.getEncoded();
        } catch (CertificateException e) {
            String str = TAG;
            Log.e(str, "CertificateException: " + e.getMessage());
            return null;
        } catch (IOException e2) {
            String str2 = TAG;
            Log.e(str2, "IOException " + e2.getMessage());
            return null;
        }
    }
}
