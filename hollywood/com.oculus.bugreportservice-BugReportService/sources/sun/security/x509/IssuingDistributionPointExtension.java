package sun.security.x509;

import java.io.OutputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class IssuingDistributionPointExtension extends Extension implements CertAttrSet {
    private DistributionPointName distributionPoint;
    private boolean hasOnlyAttributeCerts;
    private boolean hasOnlyCACerts;
    private boolean hasOnlyUserCerts;
    private boolean isIndirectCRL;
    private ReasonFlags revocationReasons;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "IssuingDistributionPoint";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.IssuingDistributionPoint_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    private void encodeThis() {
        if (this.distributionPoint != null || this.revocationReasons != null || this.hasOnlyUserCerts || this.hasOnlyCACerts || this.hasOnlyAttributeCerts || this.isIndirectCRL) {
            DerOutputStream derOutputStream = new DerOutputStream();
            if (this.distributionPoint == null) {
                if (this.hasOnlyUserCerts) {
                    DerOutputStream derOutputStream2 = new DerOutputStream();
                    derOutputStream2.putBoolean(this.hasOnlyUserCerts);
                    derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), derOutputStream2);
                }
                if (this.hasOnlyCACerts) {
                    DerOutputStream derOutputStream3 = new DerOutputStream();
                    derOutputStream3.putBoolean(this.hasOnlyCACerts);
                    derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 2), derOutputStream3);
                }
                if (this.revocationReasons == null) {
                    if (this.isIndirectCRL) {
                        DerOutputStream derOutputStream4 = new DerOutputStream();
                        derOutputStream4.putBoolean(this.isIndirectCRL);
                        derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 4), derOutputStream4);
                    }
                    if (this.hasOnlyAttributeCerts) {
                        DerOutputStream derOutputStream5 = new DerOutputStream();
                        derOutputStream5.putBoolean(this.hasOnlyAttributeCerts);
                        derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 5), derOutputStream5);
                    }
                    DerOutputStream derOutputStream6 = new DerOutputStream();
                    derOutputStream6.write((byte) 48, derOutputStream);
                    this.extensionValue = derOutputStream6.toByteArray();
                    return;
                }
                this.revocationReasons.encode(new DerOutputStream());
                throw null;
            }
            this.distributionPoint.encode(new DerOutputStream());
            throw null;
        }
        this.extensionValue = null;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("IssuingDistributionPoint [\n  ");
        DistributionPointName distributionPointName = this.distributionPoint;
        if (distributionPointName != null) {
            sb.append(distributionPointName);
        }
        ReasonFlags reasonFlags = this.revocationReasons;
        if (reasonFlags != null) {
            sb.append(reasonFlags);
        }
        sb.append(this.hasOnlyUserCerts ? "  Only contains user certs: true" : "  Only contains user certs: false");
        sb.append("\n");
        sb.append(this.hasOnlyCACerts ? "  Only contains CA certs: true" : "  Only contains CA certs: false");
        sb.append("\n");
        sb.append(this.hasOnlyAttributeCerts ? "  Only contains attribute certs: true" : "  Only contains attribute certs: false");
        sb.append("\n");
        sb.append(this.isIndirectCRL ? "  Indirect CRL: true" : "  Indirect CRL: false");
        sb.append("\n");
        sb.append("]\n");
        return sb.toString();
    }
}
