package X;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public final class Kf implements HostnameVerifier {
    public static final Pattern A00 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static boolean A01(String str, String str2) {
        int length;
        if (str != null && str.length() != 0 && !str.startsWith(".") && !str.endsWith("..") && str2 != null && str2.length() != 0 && !str2.startsWith(".") && !str2.endsWith("..")) {
            if (!str.endsWith(".")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append('.');
                str = sb.toString();
            }
            if (!str2.endsWith(".")) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append('.');
                str2 = sb2.toString();
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            if (!lowerCase.contains("*")) {
                return str.equals(lowerCase);
            }
            if (lowerCase.startsWith("*.") && lowerCase.indexOf(42, 1) == -1 && (length = str.length()) >= lowerCase.length() && !"*.".equals(lowerCase)) {
                String substring = lowerCase.substring(1);
                if (str.endsWith(substring)) {
                    int length2 = length - substring.length();
                    return length2 <= 0 || str.lastIndexOf(46, length2 - 1) == -1;
                }
            }
        }
        return false;
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            return A02(str, (X509Certificate) sSLSession.getPeerCertificates()[0]).A01;
        } catch (SSLException unused) {
            return false;
        }
    }

    public static List A00(X509Certificate x509Certificate, int i) {
        Number number;
        Object obj;
        ArrayList arrayList = new ArrayList();
        Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        if (subjectAlternativeNames == null) {
            return Collections.emptyList();
        }
        for (List<?> list : subjectAlternativeNames) {
            if (!(list == null || list.size() < 2 || (number = (Number) list.get(0)) == null || number.intValue() != i || (obj = list.get(1)) == null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01b2, code lost:
        if (A01(r4, r5) != false) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0097, code lost:
        r2 = r2 - r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0215Kg A02(java.lang.String r18, java.security.cert.X509Certificate r19) {
        /*
        // Method dump skipped, instructions count: 520
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Kf.A02(java.lang.String, java.security.cert.X509Certificate):X.Kg");
    }
}
