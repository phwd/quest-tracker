package com.squareup.okhttp;

import X.AnonymousClass006;
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
import okhttp3.CertificatePinner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new CertificatePinner(new Builder());
    public final Map<String, Set<ByteString>> hostnameToPins;

    public static final class Builder {
        public final Map<String, Set<ByteString>> hostnameToPins = new LinkedHashMap();

        public Builder add(String str, String... strArr) {
            if (str != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Set<ByteString> put = this.hostnameToPins.put(str, Collections.unmodifiableSet(linkedHashSet));
                if (put != null) {
                    linkedHashSet.addAll(put);
                }
                for (String str2 : strArr) {
                    if (str2.startsWith("sha1/")) {
                        ByteString decodeBase64 = ByteString.decodeBase64(str2.substring(5));
                        if (decodeBase64 != null) {
                            linkedHashSet.add(decodeBase64);
                        } else {
                            throw new IllegalArgumentException(AnonymousClass006.A07("pins must be base64: ", str2));
                        }
                    } else {
                        throw new IllegalArgumentException(AnonymousClass006.A07("pins must start with 'sha1/': ", str2));
                    }
                }
                return this;
            }
            throw new IllegalArgumentException("hostname == null");
        }

        public CertificatePinner build() {
            return new CertificatePinner(this);
        }
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass006.A07("sha1/", sha1((X509Certificate) certificate).base64());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public Set<ByteString> findMatchingPins(String str) {
        Set<ByteString> set;
        Set<ByteString> set2 = this.hostnameToPins.get(str);
        int indexOf = str.indexOf(46);
        if (indexOf != str.lastIndexOf(46)) {
            set = this.hostnameToPins.get(AnonymousClass006.A07(CertificatePinner.Pin.WILDCARD, str.substring(indexOf + 1)));
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

    public static ByteString sha1(X509Certificate x509Certificate) {
        return Util.sha1(ByteString.of(x509Certificate.getPublicKey().getEncoded()));
    }

    public CertificatePinner(Builder builder) {
        this.hostnameToPins = Util.immutableMap(builder.hostnameToPins);
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        Set<ByteString> findMatchingPins = findMatchingPins(str);
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
            for (ByteString byteString : findMatchingPins) {
                sb.append("\n    sha1/");
                sb.append(byteString.base64());
            }
            throw new SSLPeerUnverifiedException(sb.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }
}
