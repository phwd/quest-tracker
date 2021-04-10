package X;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;

/* renamed from: X.0hD  reason: invalid class name and case insensitive filesystem */
public final class C04650hD implements HostnameVerifier {
    public static final C04650hD A00 = new C04650hD();

    public static boolean A01(String str, String str2) {
        int length;
        if (str != null && str.length() != 0 && !str.startsWith(".") && !str.endsWith("..") && str2 != null && str2.length() != 0 && !str2.startsWith(".") && !str2.endsWith("..")) {
            if (!str.endsWith(".")) {
                str = str + '.';
            }
            if (!str2.endsWith(".")) {
                str2 = str2 + '.';
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

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f2, code lost:
        r1 = new java.lang.String(r12, r3, r15 - r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean verify(java.lang.String r18, javax.net.ssl.SSLSession r19) {
        /*
        // Method dump skipped, instructions count: 496
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04650hD.verify(java.lang.String, javax.net.ssl.SSLSession):boolean");
    }

    public static List<String> A00(X509Certificate x509Certificate, int i) {
        Integer num;
        Object obj;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (!(list == null || list.size() < 2 || (num = (Integer) list.get(0)) == null || num.intValue() != i || (obj = list.get(1)) == null)) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }
}
