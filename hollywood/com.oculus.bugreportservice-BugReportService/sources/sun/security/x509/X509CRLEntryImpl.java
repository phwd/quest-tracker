package sun.security.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class X509CRLEntryImpl extends X509CRLEntry implements Comparable {
    private X500Principal certIssuer;
    private CRLExtensions extensions = null;
    private Date revocationDate = null;
    private byte[] revokedCert = null;
    private SerialNumber serialNumber = null;

    public X509CRLEntryImpl(DerValue derValue) {
        try {
            parse(derValue);
        } catch (IOException e) {
            this.revokedCert = null;
            throw new CRLException("Parsing error: " + e.toString());
        }
    }

    public void encode(DerOutputStream derOutputStream) {
        try {
            if (this.revokedCert == null) {
                DerOutputStream derOutputStream2 = new DerOutputStream();
                this.serialNumber.encode(derOutputStream2);
                if (this.revocationDate.getTime() < 2524636800000L) {
                    derOutputStream2.putUTCTime(this.revocationDate);
                } else {
                    derOutputStream2.putGeneralizedTime(this.revocationDate);
                }
                if (this.extensions != null) {
                    this.extensions.encode(derOutputStream2, false);
                }
                DerOutputStream derOutputStream3 = new DerOutputStream();
                derOutputStream3.write((byte) 48, derOutputStream2);
                this.revokedCert = derOutputStream3.toByteArray();
            }
            derOutputStream.write(this.revokedCert);
        } catch (IOException e) {
            throw new CRLException("Encoding error: " + e.toString());
        }
    }

    @Override // java.security.cert.X509CRLEntry
    public byte[] getEncoded() {
        return (byte[]) getEncoded0().clone();
    }

    private byte[] getEncoded0() {
        if (this.revokedCert == null) {
            encode(new DerOutputStream());
        }
        return this.revokedCert;
    }

    /* access modifiers changed from: package-private */
    public void setCertificateIssuer(X500Principal x500Principal, X500Principal x500Principal2) {
        if (x500Principal.equals(x500Principal2)) {
            this.certIssuer = null;
        } else {
            this.certIssuer = x500Principal2;
        }
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber.getNumber();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.serialNumber.toString());
        sb.append("  On: " + this.revocationDate.toString());
        if (this.certIssuer != null) {
            sb.append("\n    Certificate issuer: " + this.certIssuer);
        }
        CRLExtensions cRLExtensions = this.extensions;
        if (cRLExtensions != null) {
            int i = 0;
            Extension[] extensionArr = (Extension[]) cRLExtensions.getAllExtensions().toArray(new Extension[0]);
            sb.append("\n    CRL Entry Extensions: " + extensionArr.length);
            while (i < extensionArr.length) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\n    [");
                int i2 = i + 1;
                sb2.append(i2);
                sb2.append("]: ");
                sb.append(sb2.toString());
                Extension extension = extensionArr[i];
                try {
                    if (OIDMap.getClass(extension.getExtensionId()) == null) {
                        sb.append(extension.toString());
                        byte[] extensionValue = extension.getExtensionValue();
                        if (extensionValue != null) {
                            DerOutputStream derOutputStream = new DerOutputStream();
                            derOutputStream.putOctetString(extensionValue);
                            byte[] byteArray = derOutputStream.toByteArray();
                            HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
                            new StringBuilder().append("Extension unknown: DER encoded OCTET string =\n");
                            hexDumpEncoder.encodeBuffer(byteArray);
                            throw null;
                        }
                    } else {
                        sb.append(extension.toString());
                    }
                } catch (Exception unused) {
                    sb.append(", Error parsing this extension");
                }
                i = i2;
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public Extension getExtension(ObjectIdentifier objectIdentifier) {
        CRLExtensions cRLExtensions = this.extensions;
        if (cRLExtensions == null) {
            return null;
        }
        return cRLExtensions.get(OIDMap.getName(objectIdentifier));
    }

    private void parse(DerValue derValue) {
        if (derValue.tag != 48) {
            throw new CRLException("Invalid encoded RevokedCertificate, starting sequence tag missing.");
        } else if (derValue.data.available() != 0) {
            this.revokedCert = derValue.toByteArray();
            this.serialNumber = new SerialNumber(derValue.toDerInputStream().getDerValue());
            byte peekByte = (byte) derValue.data.peekByte();
            if (peekByte == 23) {
                this.revocationDate = derValue.data.getUTCTime();
            } else if (peekByte == 24) {
                this.revocationDate = derValue.data.getGeneralizedTime();
            } else {
                throw new CRLException("Invalid encoding for revocation date");
            }
            if (derValue.data.available() != 0) {
                this.extensions = new CRLExtensions(derValue.toDerInputStream());
            }
        } else {
            throw new CRLException("No data encoded for RevokedCertificates");
        }
    }

    /* access modifiers changed from: package-private */
    public CertificateIssuerExtension getCertificateIssuerExtension() {
        return (CertificateIssuerExtension) getExtension(PKIXExtensions.CertificateIssuer_Id);
    }

    public int compareTo(X509CRLEntryImpl x509CRLEntryImpl) {
        int compareTo = getSerialNumber().compareTo(x509CRLEntryImpl.getSerialNumber());
        if (compareTo != 0) {
            return compareTo;
        }
        try {
            byte[] encoded0 = getEncoded0();
            byte[] encoded02 = x509CRLEntryImpl.getEncoded0();
            int i = 0;
            while (i < encoded0.length && i < encoded02.length) {
                int i2 = encoded0[i] & 255;
                int i3 = encoded02[i] & 255;
                if (i2 != i3) {
                    return i2 - i3;
                }
                i++;
            }
            return encoded0.length - encoded02.length;
        } catch (CRLException unused) {
            return -1;
        }
    }
}
