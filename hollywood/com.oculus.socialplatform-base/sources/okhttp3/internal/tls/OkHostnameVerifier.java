package okhttp3.internal.tls;

import X.AnonymousClass006;
import com.oculus.localmedia.LocalMediaManager;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import okhttp3.CertificatePinner;
import okhttp3.internal.Util;

public final class OkHostnameVerifier implements HostnameVerifier {
    public static final int ALT_DNS_NAME = 2;
    public static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    public static List<String> allSubjectAltNames(X509Certificate x509Certificate) {
        List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        List<String> subjectAltNames2 = getSubjectAltNames(x509Certificate, 2);
        ArrayList arrayList = new ArrayList(subjectAltNames.size() + subjectAltNames2.size());
        arrayList.addAll(subjectAltNames);
        arrayList.addAll(subjectAltNames2);
        return arrayList;
    }

    private boolean verifyIpAddress(String str, X509Certificate x509Certificate) {
        List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 7);
        int size = subjectAltNames.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(subjectAltNames.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getSubjectAltNames(X509Certificate x509Certificate, int i) {
        Number number;
        Object obj;
        ArrayList arrayList = new ArrayList();
        try {
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
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    private boolean verifyHostname(String str, String str2) {
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
                    if (length2 <= 0 || str.lastIndexOf(46, length2 - 1) == -1) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private boolean verifyHostname(String str, X509Certificate x509Certificate) {
        String findMostSpecific;
        String lowerCase = str.toLowerCase(Locale.US);
        List<String> subjectAltNames = getSubjectAltNames(x509Certificate, 2);
        int size = subjectAltNames.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            if (verifyHostname(lowerCase, subjectAltNames.get(i))) {
                return true;
            }
            i++;
            z = true;
        }
        if (z || (findMostSpecific = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn")) == null) {
            return false;
        }
        return verifyHostname(lowerCase, findMostSpecific);
    }

    public boolean verify(String str, X509Certificate x509Certificate) {
        if (Util.verifyAsIpAddress(str)) {
            return verifyIpAddress(str, x509Certificate);
        }
        return verifyHostname(str, x509Certificate);
    }

    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }
}
