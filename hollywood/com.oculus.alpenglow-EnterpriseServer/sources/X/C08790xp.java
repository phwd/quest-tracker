package X;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;

/* renamed from: X.0xp  reason: invalid class name and case insensitive filesystem */
public final class C08790xp implements HostnameVerifier {
    public static final Pattern A00 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

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

    public static List<String> A00(X509Certificate x509Certificate, int i) throws CertificateParsingException {
        Integer num;
        Object obj;
        ArrayList arrayList = new ArrayList();
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
    }

    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01ef, code lost:
        if (A01(r4, r17) != false) goto L_0x021b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean verify(java.lang.String r20, javax.net.ssl.SSLSession r21) {
        /*
        // Method dump skipped, instructions count: 546
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08790xp.verify(java.lang.String, javax.net.ssl.SSLSession):boolean");
    }
}
