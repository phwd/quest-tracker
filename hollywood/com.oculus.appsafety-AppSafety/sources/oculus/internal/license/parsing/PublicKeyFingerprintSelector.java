package oculus.internal.license.parsing;

import java.security.MessageDigest;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.util.Arrays;

/* access modifiers changed from: package-private */
public class PublicKeyFingerprintSelector implements CertSelector {
    MessageDigest digester;
    byte[] fingerprint;

    PublicKeyFingerprintSelector(byte[] fp, MessageDigest md) {
        this.fingerprint = fp;
        this.digester = md;
    }

    @Override // java.security.cert.CertSelector
    public Object clone() {
        return new PublicKeyFingerprintSelector(this.fingerprint, this.digester);
    }

    @Override // java.security.cert.CertSelector
    public boolean match(Certificate cert) {
        this.digester.reset();
        return Arrays.equals(this.fingerprint, this.digester.digest(cert.getPublicKey().getEncoded()));
    }
}
