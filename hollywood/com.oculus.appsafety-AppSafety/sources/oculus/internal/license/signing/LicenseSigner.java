package oculus.internal.license.signing;

import java.security.SignatureException;
import java.security.cert.Certificate;

public interface LicenseSigner {
    Certificate getCertificate();

    LicenseSignature sign(byte[] bArr) throws SignatureException;
}
