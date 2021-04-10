package com.oculus.platform;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Locale;

public class OVRPackageUtils {
    public static final String SERVICE_PACKAGE_FINGERPRINT = "4A:BF:66:84:F2:8E:13:0A:3A:37:2B:27:62:7F:34:DD:D6:77:52:26";
    public static final String SERVICE_PACKAGE_FINGERPRINT_DEBUG = "5E:8F:16:06:2E:A3:CD:2C:4A:0D:54:78:76:BA:A6:F3:8C:AB:F6:25";

    public static String byte2HexFormatted(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(((length << 1) + length) - 1);
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = b;
            if (b < 0) {
                i2 = b + 256;
            }
            String hexString = Integer.toHexString(i2 == 1 ? 1 : 0);
            if (i2 < 16) {
                sb.append("0");
            }
            sb.append(hexString);
            if (i < length - 1) {
                sb.append(":");
            }
        }
        return sb.toString().toUpperCase(new Locale("en"));
    }

    public static Signature getSignature(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        return packageManager.getPackageInfo(str, 64).signatures[0];
    }

    public static String getCertificateSHA1Fingerprint(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            try {
                return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(getSignature(packageManager, str).toByteArray())).getEncoded()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (CertificateEncodingException e2) {
                throw new RuntimeException(e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static boolean validateServiceSignature(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        String certificateSHA1Fingerprint = getCertificateSHA1Fingerprint(packageManager, str);
        if (certificateSHA1Fingerprint.equals(SERVICE_PACKAGE_FINGERPRINT) || certificateSHA1Fingerprint.equals(SERVICE_PACKAGE_FINGERPRINT_DEBUG)) {
            return true;
        }
        return false;
    }
}
