package com.squareup.okhttp;

import X.AnonymousClass06;
import X.WM;
import com.squareup.okhttp.internal.Util;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new CertificatePinner(new Builder());
    public final Map<String, Set<WM>> hostnameToPins;

    public static final class Builder {
        public final Map<String, Set<WM>> hostnameToPins = new LinkedHashMap();

        public Builder add(String str, String... strArr) {
            String str2;
            String str3;
            if (str != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Set<WM> put = this.hostnameToPins.put(str, Collections.unmodifiableSet(linkedHashSet));
                if (put != null) {
                    linkedHashSet.addAll(put);
                }
                for (String str4 : strArr) {
                    if (str4.startsWith("sha1/")) {
                        WM A02 = WM.A02(str4.substring(5));
                        if (A02 != null) {
                            linkedHashSet.add(A02);
                        } else {
                            str3 = "pins must be base64: ";
                        }
                    } else {
                        str3 = "pins must start with 'sha1/': ";
                    }
                    str2 = AnonymousClass06.A03(str3, str4);
                }
                return this;
            }
            str2 = "hostname == null";
            throw new IllegalArgumentException(str2);
        }

        public CertificatePinner build() {
            return new CertificatePinner(this);
        }
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass06.A03("sha1/", sha1((X509Certificate) certificate).A08());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public Set<WM> findMatchingPins(String str) {
        Set<WM> set;
        Set<WM> set2 = this.hostnameToPins.get(str);
        int indexOf = str.indexOf(46);
        if (indexOf != str.lastIndexOf(46)) {
            set = this.hostnameToPins.get(AnonymousClass06.A03("*.", str.substring(indexOf + 1)));
        } else {
            set = null;
        }
        if (set2 == null) {
            if (set == null) {
                return null;
            }
            return set;
        } else if (set == null) {
            return set2;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(set2);
            linkedHashSet.addAll(set);
            return linkedHashSet;
        }
    }

    public static WM sha1(X509Certificate x509Certificate) {
        return Util.sha1(WM.A05(x509Certificate.getPublicKey().getEncoded()));
    }

    public CertificatePinner(Builder builder) {
        this.hostnameToPins = Util.immutableMap(builder.hostnameToPins);
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        Set<WM> findMatchingPins = findMatchingPins(str);
        if (findMatchingPins != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (findMatchingPins.contains(sha1((X509Certificate) list.get(i)))) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder("Certificate pinning failure!");
            sb.append("\n  Peer certificate chain:");
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i2);
                sb.append("\n    ");
                sb.append(pin(x509Certificate));
                sb.append(": ");
                sb.append(x509Certificate.getSubjectDN().getName());
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            for (WM wm : findMatchingPins) {
                sb.append("\n    sha1/");
                sb.append(wm.A08());
            }
            throw new SSLPeerUnverifiedException(sb.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }
}
