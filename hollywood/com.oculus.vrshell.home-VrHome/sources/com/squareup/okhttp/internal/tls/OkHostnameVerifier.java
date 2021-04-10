package com.squareup.okhttp.internal.tls;

import com.oculus.localmedia.LocalMediaManager;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private OkHostnameVerifier() {
    }

    public boolean verify(String host, SSLSession session) {
        try {
            return verify(host, (X509Certificate) session.getPeerCertificates()[0]);
        } catch (SSLException e) {
            return false;
        }
    }

    public boolean verify(String host, X509Certificate certificate) {
        if (verifyAsIpAddress(host)) {
            return verifyIpAddress(host, certificate);
        }
        return verifyHostName(host, certificate);
    }

    static boolean verifyAsIpAddress(String host) {
        return VERIFY_AS_IP_ADDRESS.matcher(host).matches();
    }

    private boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
        List<String> altNames = getSubjectAltNames(certificate, 7);
        int size = altNames.size();
        for (int i = 0; i < size; i++) {
            if (ipAddress.equalsIgnoreCase(altNames.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean verifyHostName(String hostName, X509Certificate certificate) {
        String cn;
        String hostName2 = hostName.toLowerCase(Locale.US);
        boolean hasDns = false;
        List<String> altNames = getSubjectAltNames(certificate, 2);
        int size = altNames.size();
        for (int i = 0; i < size; i++) {
            hasDns = true;
            if (verifyHostName(hostName2, altNames.get(i))) {
                return true;
            }
        }
        if (hasDns || (cn = new DistinguishedNameParser(certificate.getSubjectX500Principal()).findMostSpecific("cn")) == null) {
            return false;
        }
        return verifyHostName(hostName2, cn);
    }

    public static List<String> allSubjectAltNames(X509Certificate certificate) {
        List<String> altIpaNames = getSubjectAltNames(certificate, 7);
        List<String> altDnsNames = getSubjectAltNames(certificate, 2);
        List<String> result = new ArrayList<>(altIpaNames.size() + altDnsNames.size());
        result.addAll(altIpaNames);
        result.addAll(altDnsNames);
        return result;
    }

    private static List<String> getSubjectAltNames(X509Certificate certificate, int type) {
        Integer altNameType;
        String altName;
        List<String> result = new ArrayList<>();
        try {
            Collection<?> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) {
                return Collections.emptyList();
            }
            Iterator<?> it = subjectAltNames.iterator();
            while (it.hasNext()) {
                List<?> entry = (List) it.next();
                if (!(entry == null || entry.size() < 2 || (altNameType = (Integer) entry.get(0)) == null || altNameType.intValue() != type || (altName = (String) entry.get(1)) == null)) {
                    result.add(altName);
                }
            }
            return result;
        } catch (CertificateParsingException e) {
            return Collections.emptyList();
        }
    }

    private boolean verifyHostName(String hostName, String pattern) {
        if (hostName == null || hostName.length() == 0 || hostName.startsWith(".") || hostName.endsWith(LocalMediaManager.PARENT_FOLDER_NAME) || pattern == null || pattern.length() == 0 || pattern.startsWith(".") || pattern.endsWith(LocalMediaManager.PARENT_FOLDER_NAME)) {
            return false;
        }
        if (!hostName.endsWith(".")) {
            hostName = hostName + '.';
        }
        if (!pattern.endsWith(".")) {
            pattern = pattern + '.';
        }
        String pattern2 = pattern.toLowerCase(Locale.US);
        if (!pattern2.contains("*")) {
            return hostName.equals(pattern2);
        }
        if (!pattern2.startsWith("*.") || pattern2.indexOf(42, 1) != -1 || hostName.length() < pattern2.length() || "*.".equals(pattern2)) {
            return false;
        }
        String suffix = pattern2.substring(1);
        if (!hostName.endsWith(suffix)) {
            return false;
        }
        int suffixStartIndexInHostName = hostName.length() - suffix.length();
        return suffixStartIndexInHostName <= 0 || hostName.lastIndexOf(46, suffixStartIndexInHostName + -1) == -1;
    }
}
