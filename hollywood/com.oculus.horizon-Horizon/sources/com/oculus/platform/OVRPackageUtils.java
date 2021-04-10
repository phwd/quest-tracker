package com.oculus.platform;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Locale;

public class OVRPackageUtils {
    public static final String HORIZON_PACKAGE = "com.oculus.horizon";
    public static final String HORIZON_PACKAGE_FINGERPRINT = "4A:BF:66:84:F2:8E:13:0A:3A:37:2B:27:62:7F:34:DD:D6:77:52:26";
    public static final String HORIZON_PACKAGE_FINGERPRINT_DEBUG = "5E:8F:16:06:2E:A3:CD:2C:4A:0D:54:78:76:BA:A6:F3:8C:AB:F6:25";
    public static final String SHELL_HOME_PACKAGE = "com.oculus.vrshell.home";
    public static final String SHELL_HOME_PACKAGE_FINGERPRINT = "33:16:60:B6:DD:3B:D5:82:F3:DF:D3:CB:AE:45:46:72:46:68:A0:21";
    public static final String SHELL_HOME_PACKAGE_FINGERPRINT_DEBUG = "5E:8F:16:06:2E:A3:CD:2C:4A:0D:54:78:76:BA:A6:F3:8C:AB:F6:25";
    public static final String SOCIAL_PLATFORM_FINGERPRINT_DEBUG = "5E:8F:16:06:2E:A3:CD:2C:4A:0D:54:78:76:BA:A6:F3:8C:AB:F6:25";
    public static final String SOCIAL_PLATFORM_PACKAGE = "com.oculus.socialplatform";
    public static final String SOCIAL_PLATFORM_PACKAGE_FINGERPRINT = "33:16:60:B6:DD:3B:D5:82:F3:DF:D3:CB:AE:45:46:72:46:68:A0:21";

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

    public static boolean validateServiceSignature(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        String certificateSHA1Fingerprint;
        boolean equals;
        if ("com.oculus.horizon".equals(str)) {
            certificateSHA1Fingerprint = getCertificateSHA1Fingerprint(packageManager, "com.oculus.horizon");
            equals = certificateSHA1Fingerprint.equals(HORIZON_PACKAGE_FINGERPRINT);
        } else {
            String str2 = "com.oculus.vrshell.home";
            if (!str2.equals(str)) {
                str2 = "com.oculus.socialplatform";
                if (!str2.equals(str)) {
                    throw new PackageManager.NameNotFoundException();
                }
            }
            certificateSHA1Fingerprint = getCertificateSHA1Fingerprint(packageManager, str2);
            equals = certificateSHA1Fingerprint.equals("33:16:60:B6:DD:3B:D5:82:F3:DF:D3:CB:AE:45:46:72:46:68:A0:21");
        }
        if (equals || certificateSHA1Fingerprint.equals("5E:8F:16:06:2E:A3:CD:2C:4A:0D:54:78:76:BA:A6:F3:8C:AB:F6:25")) {
            return true;
        }
        return false;
    }

    public static String getCertificateSHA1Fingerprint(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(getSignature(packageManager, str).toByteArray())).getEncoded()));
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
    }
}
