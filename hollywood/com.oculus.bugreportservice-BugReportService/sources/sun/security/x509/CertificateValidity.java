package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateValidity implements CertAttrSet {
    private Date notAfter;
    private Date notBefore;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "validity";
    }

    private Date getNotBefore() {
        return new Date(this.notBefore.getTime());
    }

    private Date getNotAfter() {
        return new Date(this.notAfter.getTime());
    }

    private void construct(DerValue derValue) {
        if (derValue.tag != 48) {
            throw new IOException("Invalid encoded CertificateValidity, starting sequence tag missing.");
        } else if (derValue.data.available() != 0) {
            DerValue[] sequence = new DerInputStream(derValue.toByteArray()).getSequence(2);
            if (sequence.length == 2) {
                if (sequence[0].tag == 23) {
                    this.notBefore = derValue.data.getUTCTime();
                } else if (sequence[0].tag == 24) {
                    this.notBefore = derValue.data.getGeneralizedTime();
                } else {
                    throw new IOException("Invalid encoding for CertificateValidity");
                }
                if (sequence[1].tag == 23) {
                    this.notAfter = derValue.data.getUTCTime();
                } else if (sequence[1].tag == 24) {
                    this.notAfter = derValue.data.getGeneralizedTime();
                } else {
                    throw new IOException("Invalid encoding for CertificateValidity");
                }
            } else {
                throw new IOException("Invalid encoding for CertificateValidity");
            }
        } else {
            throw new IOException("No data encoded for CertificateValidity");
        }
    }

    public CertificateValidity(DerInputStream derInputStream) {
        construct(derInputStream.getDerValue());
    }

    public String toString() {
        if (this.notBefore == null || this.notAfter == null) {
            return "";
        }
        return "Validity: [From: " + this.notBefore.toString() + ",\n               To: " + this.notAfter.toString() + "]";
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        if (this.notBefore == null || this.notAfter == null) {
            throw new IOException("CertAttrSet:CertificateValidity: null values to encode.\n");
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.notBefore.getTime() < 2524636800000L) {
            derOutputStream.putUTCTime(this.notBefore);
        } else {
            derOutputStream.putGeneralizedTime(this.notBefore);
        }
        if (this.notAfter.getTime() < 2524636800000L) {
            derOutputStream.putUTCTime(this.notAfter);
        } else {
            derOutputStream.putGeneralizedTime(this.notAfter);
        }
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream2.write((byte) 48, derOutputStream);
        outputStream.write(derOutputStream2.toByteArray());
    }

    public Date get(String str) {
        if (str.equalsIgnoreCase("notBefore")) {
            return getNotBefore();
        }
        if (str.equalsIgnoreCase("notAfter")) {
            return getNotAfter();
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateValidity.");
    }
}
