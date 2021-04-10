package oculus.internal.license.signing;

import oculus.internal.license.parsing.LicenseBlobParser;

public final class LicenseSignature {
    public final LicenseBlobParser.SignatureScheme scheme;
    public final byte[] signature;

    public LicenseSignature(LicenseBlobParser.SignatureScheme scheme2, byte[] signature2) {
        this.scheme = scheme2;
        this.signature = signature2;
    }
}
