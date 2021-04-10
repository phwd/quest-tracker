package com.oculus.platform;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;

public class OVRPackageUtils {
    private static final String SERVICE_PACKAGE_FINGERPRINT = "4A:BF:66:84:F2:8E:13:0A:3A:37:2B:27:62:7F:34:DD:D6:77:52:26";
    private static final String SERVICE_PACKAGE_FINGERPRINT_DEBUG = "5E:8F:16:06:2E:A3:CD:2C:4A:0D:54:78:76:BA:A6:F3:8C:AB:F6:25";

    public static boolean validateServiceSignature(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        String certFingerprint = getCertificateSHA1Fingerprint(packageManager, packageName);
        return certFingerprint.equals(SERVICE_PACKAGE_FINGERPRINT) || certFingerprint.equals(SERVICE_PACKAGE_FINGERPRINT_DEBUG);
    }

    public static Signature getSignature(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        return packageManager.getPackageInfo(packageName, 64).signatures[0];
    }

    public static String getCertificateSHA1Fingerprint(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        try {
            try {
                return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(getSignature(packageManager, packageName).toByteArray()))).getEncoded()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (CertificateEncodingException e2) {
                throw new RuntimeException(e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(((arr.length * 2) + arr.length) - 1);
        for (int i = 0; i < arr.length; i++) {
            byte b = arr[i];
            int value = b;
            if (b < 0) {
                value = b + 256;
            }
            String h = Integer.toHexString(value == 1 ? 1 : 0);
            if (value < 16) {
                str.append("0");
            }
            str.append(h);
            if (i < arr.length - 1) {
                str.append(":");
            }
        }
        return str.toString().toUpperCase(new Locale("en"));
    }
}
