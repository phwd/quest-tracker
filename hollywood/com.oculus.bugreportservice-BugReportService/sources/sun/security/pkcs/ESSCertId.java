package sun.security.pkcs;

import sun.misc.HexDumpEncoder;
import sun.security.util.DerValue;
import sun.security.x509.GeneralNames;
import sun.security.x509.SerialNumber;

/* compiled from: SigningCertificateInfo */
class ESSCertId {
    private static volatile HexDumpEncoder hexDumper;
    private byte[] certHash;
    private GeneralNames issuer;
    private SerialNumber serialNumber;

    ESSCertId(DerValue derValue) {
        this.certHash = derValue.data.getDerValue().toByteArray();
        if (derValue.data.available() > 0) {
            DerValue derValue2 = derValue.data.getDerValue();
            this.issuer = new GeneralNames(derValue2.data.getDerValue());
            this.serialNumber = new SerialNumber(derValue2.data.getDerValue());
        }
    }

    public String toString() {
        new StringBuffer().append("[\n\tCertificate hash (SHA-1):\n");
        if (hexDumper == null) {
            hexDumper = new HexDumpEncoder();
        }
        hexDumper.encode(this.certHash);
        throw null;
    }
}
