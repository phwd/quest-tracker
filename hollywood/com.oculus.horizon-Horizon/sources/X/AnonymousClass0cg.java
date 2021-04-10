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

/* renamed from: X.0cg  reason: invalid class name */
public final class AnonymousClass0cg implements HostnameVerifier {
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

    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01f7, code lost:
        if (A01(r4, r17) != false) goto L_0x0223;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean verify(java.lang.String r20, javax.net.ssl.SSLSession r21) {
        /*
        // Method dump skipped, instructions count: 554
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0cg.verify(java.lang.String, javax.net.ssl.SSLSession):boolean");
    }

    public static List<String> A00(X509Certificate x509Certificate, int i) throws CertificateParsingException {
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
}
