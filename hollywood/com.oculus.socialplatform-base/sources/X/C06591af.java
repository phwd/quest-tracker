package X;

import com.oculus.localmedia.LocalMediaManager;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import okhttp3.CertificatePinner;

/* renamed from: X.1af  reason: invalid class name and case insensitive filesystem */
public final class C06591af implements HostnameVerifier {
    public static final Pattern A00 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static boolean A01(String str, String str2) {
        int length;
        if (str != null && str.length() != 0 && !str.startsWith(".") && !str.endsWith(LocalMediaManager.PARENT_FOLDER_NAME) && str2 != null && str2.length() != 0 && !str2.startsWith(".") && !str2.endsWith(LocalMediaManager.PARENT_FOLDER_NAME)) {
            if (!str.endsWith(".")) {
                str = AnonymousClass006.A01(str, '.');
            }
            if (!str2.endsWith(".")) {
                str2 = AnonymousClass006.A01(str2, '.');
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            if (!lowerCase.contains("*")) {
                return str.equals(lowerCase);
            }
            if (lowerCase.startsWith(CertificatePinner.Pin.WILDCARD) && lowerCase.indexOf(42, 1) == -1 && (length = str.length()) >= lowerCase.length() && !CertificatePinner.Pin.WILDCARD.equals(lowerCase)) {
                String substring = lowerCase.substring(1);
                if (str.endsWith(substring)) {
                    int length2 = length - substring.length();
                    return length2 <= 0 || str.lastIndexOf(46, length2 - 1) == -1;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01f4, code lost:
        if (A01(r4, r5) != false) goto L_0x0220;
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x01ec A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean verify(java.lang.String r20, javax.net.ssl.SSLSession r21) {
        /*
        // Method dump skipped, instructions count: 551
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06591af.verify(java.lang.String, javax.net.ssl.SSLSession):boolean");
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
