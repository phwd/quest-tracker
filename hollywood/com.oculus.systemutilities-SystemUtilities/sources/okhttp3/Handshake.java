package okhttp3;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.Util;

public final class Handshake {
    private final CipherSuite cipherSuite;
    private final List<Certificate> localCertificates;
    private final List<Certificate> peerCertificates;
    private final TlsVersion tlsVersion;

    private Handshake(TlsVersion tlsVersion2, CipherSuite cipherSuite2, List<Certificate> peerCertificates2, List<Certificate> localCertificates2) {
        this.tlsVersion = tlsVersion2;
        this.cipherSuite = cipherSuite2;
        this.peerCertificates = peerCertificates2;
        this.localCertificates = localCertificates2;
    }

    public static Handshake get(SSLSession session) {
        Certificate[] peerCertificates2;
        List<Certificate> peerCertificatesList;
        List<Certificate> localCertificatesList;
        String cipherSuiteString = session.getCipherSuite();
        if (cipherSuiteString == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        CipherSuite cipherSuite2 = CipherSuite.forJavaName(cipherSuiteString);
        String tlsVersionString = session.getProtocol();
        if (tlsVersionString == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        TlsVersion tlsVersion2 = TlsVersion.forJavaName(tlsVersionString);
        try {
            peerCertificates2 = session.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            peerCertificates2 = null;
        }
        if (peerCertificates2 != null) {
            peerCertificatesList = Util.immutableList(peerCertificates2);
        } else {
            peerCertificatesList = Collections.emptyList();
        }
        Certificate[] localCertificates2 = session.getLocalCertificates();
        if (localCertificates2 != null) {
            localCertificatesList = Util.immutableList(localCertificates2);
        } else {
            localCertificatesList = Collections.emptyList();
        }
        return new Handshake(tlsVersion2, cipherSuite2, peerCertificatesList, localCertificatesList);
    }

    public CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    public List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Handshake)) {
            return false;
        }
        Handshake that = (Handshake) other;
        if (!Util.equal(this.cipherSuite, that.cipherSuite) || !this.cipherSuite.equals(that.cipherSuite) || !this.peerCertificates.equals(that.peerCertificates) || !this.localCertificates.equals(that.localCertificates)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((this.tlsVersion != null ? this.tlsVersion.hashCode() : 0) + 527) * 31) + this.cipherSuite.hashCode()) * 31) + this.peerCertificates.hashCode()) * 31) + this.localCertificates.hashCode();
    }
}
