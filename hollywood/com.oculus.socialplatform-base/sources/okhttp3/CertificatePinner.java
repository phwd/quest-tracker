package okhttp3;

import X.AnonymousClass006;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    public final CertificateChainCleaner certificateChainCleaner;
    public final Set<Pin> pins;

    public static final class Builder {
        public final List<Pin> pins = new ArrayList();

        public Builder add(String str, String... strArr) {
            if (str != null) {
                for (String str2 : strArr) {
                    this.pins.add(new Pin(str, str2));
                }
                return this;
            }
            throw new NullPointerException("pattern == null");
        }

        public CertificatePinner build() {
            return new CertificatePinner(new LinkedHashSet(this.pins), null);
        }
    }

    public static final class Pin {
        public static final String WILDCARD = "*.";
        public final String canonicalHostname;
        public final ByteString hash;
        public final String hashAlgorithm;
        public final String pattern;

        public boolean equals(Object obj) {
            if (obj instanceof Pin) {
                Pin pin = (Pin) obj;
                if (!this.pattern.equals(pin.pattern) || !this.hashAlgorithm.equals(pin.hashAlgorithm) || !this.hash.equals(pin.hash)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.pattern.hashCode()) * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
        }

        public boolean matches(String str) {
            if (!this.pattern.startsWith(WILDCARD)) {
                return str.equals(this.canonicalHostname);
            }
            String str2 = this.canonicalHostname;
            return str.regionMatches(false, str.indexOf(46) + 1, str2, 0, str2.length());
        }

        public String toString() {
            return AnonymousClass006.A07(this.hashAlgorithm, this.hash.base64());
        }

        public Pin(String str, String str2) {
            StringBuilder sb;
            int i;
            this.pattern = str;
            if (str.startsWith(WILDCARD)) {
                sb = new StringBuilder();
                sb.append("http://");
                str = str.substring(2);
            } else {
                sb = new StringBuilder();
                sb.append("http://");
            }
            sb.append(str);
            this.canonicalHostname = HttpUrl.parse(sb.toString()).host;
            if (str2.startsWith("sha1/")) {
                this.hashAlgorithm = "sha1/";
                i = 5;
            } else if (str2.startsWith(FbCertificatePinnerFactory.SHA_256_PIN_PREFIX)) {
                this.hashAlgorithm = FbCertificatePinnerFactory.SHA_256_PIN_PREFIX;
                i = 7;
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A07("pins must start with 'sha256/' or 'sha1/': ", str2));
            }
            ByteString decodeBase64 = ByteString.decodeBase64(str2.substring(i));
            this.hash = decodeBase64;
            if (decodeBase64 == null) {
                throw new IllegalArgumentException(AnonymousClass006.A07("pins must be base64: ", str2));
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof CertificatePinner)) {
                return false;
            }
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (!Util.equal(this.certificateChainCleaner, certificatePinner.certificateChainCleaner) || !this.pins.equals(certificatePinner.pins)) {
                return false;
            }
        }
        return true;
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass006.A07(FbCertificatePinnerFactory.SHA_256_PIN_PREFIX, sha256((X509Certificate) certificate).base64());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public int hashCode() {
        int i;
        CertificateChainCleaner certificateChainCleaner2 = this.certificateChainCleaner;
        if (certificateChainCleaner2 != null) {
            i = certificateChainCleaner2.hashCode();
        } else {
            i = 0;
        }
        return (i * 31) + this.pins.hashCode();
    }

    public CertificatePinner withCertificateChainCleaner(CertificateChainCleaner certificateChainCleaner2) {
        if (Util.equal(this.certificateChainCleaner, certificateChainCleaner2)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner2);
    }

    public CertificatePinner(Set<Pin> set, CertificateChainCleaner certificateChainCleaner2) {
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner2;
    }

    public static ByteString sha1(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    public static ByteString sha256(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }

    public List<Pin> findMatchingPins(String str) {
        List<Pin> emptyList = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                emptyList.add(pin);
            }
        }
        return emptyList;
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        boolean equals;
        List<Pin> findMatchingPins = findMatchingPins(str);
        if (!findMatchingPins.isEmpty()) {
            CertificateChainCleaner certificateChainCleaner2 = this.certificateChainCleaner;
            if (certificateChainCleaner2 != null) {
                list = certificateChainCleaner2.clean(list, str);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i);
                int size2 = findMatchingPins.size();
                ByteString byteString = null;
                ByteString byteString2 = null;
                for (int i2 = 0; i2 < size2; i2++) {
                    Pin pin = findMatchingPins.get(i2);
                    if (pin.hashAlgorithm.equals(FbCertificatePinnerFactory.SHA_256_PIN_PREFIX)) {
                        if (byteString == null) {
                            byteString = sha256(x509Certificate);
                        }
                        equals = pin.hash.equals(byteString);
                    } else if (pin.hashAlgorithm.equals("sha1/")) {
                        if (byteString2 == null) {
                            byteString2 = sha1(x509Certificate);
                        }
                        equals = pin.hash.equals(byteString2);
                    } else {
                        throw new AssertionError();
                    }
                    if (equals) {
                        return;
                    }
                }
            }
            StringBuilder sb = new StringBuilder("Certificate pinning failure!");
            sb.append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (int i3 = 0; i3 < size3; i3++) {
                X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
                sb.append("\n    ");
                sb.append(pin(x509Certificate2));
                sb.append(": ");
                sb.append(x509Certificate2.getSubjectDN().getName());
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            int size4 = findMatchingPins.size();
            for (int i4 = 0; i4 < size4; i4++) {
                Pin pin2 = findMatchingPins.get(i4);
                sb.append("\n    ");
                sb.append(pin2);
            }
            throw new SSLPeerUnverifiedException(sb.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }
}
