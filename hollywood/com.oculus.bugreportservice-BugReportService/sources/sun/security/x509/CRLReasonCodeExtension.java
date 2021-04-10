package sun.security.x509;

import java.io.OutputStream;
import java.security.cert.CRLReason;
import sun.security.util.DerOutputStream;

public class CRLReasonCodeExtension extends Extension implements CertAttrSet {
    private static CRLReason[] values = CRLReason.values();
    private int reasonCode;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "CRLReasonCode";
    }

    private void encodeThis() {
        if (this.reasonCode == 0) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.putEnumerated(this.reasonCode);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        return super.toString() + "    Reason Code: " + getReasonCode();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.ReasonCode_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    public CRLReason getReasonCode() {
        int i = this.reasonCode;
        if (i > 0) {
            CRLReason[] cRLReasonArr = values;
            if (i < cRLReasonArr.length) {
                return cRLReasonArr[i];
            }
        }
        return CRLReason.UNSPECIFIED;
    }
}
