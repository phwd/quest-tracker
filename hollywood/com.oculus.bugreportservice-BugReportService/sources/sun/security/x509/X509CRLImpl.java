package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class X509CRLImpl extends X509CRL implements DerEncoder {
    private CRLExtensions extensions = null;
    private AlgorithmId infoSigAlgId;
    private X500Name issuer = null;
    private X500Principal issuerPrincipal = null;
    private Date nextUpdate = null;
    private boolean readOnly = false;
    private List revokedList = new LinkedList();
    private Map revokedMap = new TreeMap();
    private AlgorithmId sigAlgId = null;
    private byte[] signature = null;
    private byte[] signedCRL = null;
    private byte[] tbsCertList = null;
    private Date thisUpdate = null;
    private int version;

    private X509CRLImpl() {
    }

    public X509CRLImpl(DerValue derValue) {
        try {
            parse(derValue);
        } catch (IOException e) {
            this.signedCRL = null;
            throw new CRLException("Parsing error: " + e.getMessage());
        }
    }

    public byte[] getEncodedInternal() {
        byte[] bArr = this.signedCRL;
        if (bArr != null) {
            return bArr;
        }
        throw new CRLException("Null CRL to encode");
    }

    @Override // java.security.cert.X509CRL
    public byte[] getEncoded() {
        return (byte[]) getEncodedInternal().clone();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        sb.append("X.509 CRL v");
        int i = 1;
        sb.append(this.version + 1);
        sb.append("\n");
        stringBuffer.append(sb.toString());
        if (this.sigAlgId != null) {
            stringBuffer.append("Signature Algorithm: " + this.sigAlgId.toString() + ", OID=" + this.sigAlgId.getOID().toString() + "\n");
        }
        if (this.issuer != null) {
            stringBuffer.append("Issuer: " + this.issuer.toString() + "\n");
        }
        if (this.thisUpdate != null) {
            stringBuffer.append("\nThis Update: " + this.thisUpdate.toString() + "\n");
        }
        if (this.nextUpdate != null) {
            stringBuffer.append("Next Update: " + this.nextUpdate.toString() + "\n");
        }
        if (this.revokedList.isEmpty()) {
            stringBuffer.append("\nNO certificates have been revoked\n");
        } else {
            stringBuffer.append("\nRevoked Certificates: " + this.revokedList.size());
            Iterator it = this.revokedList.iterator();
            while (it.hasNext()) {
                stringBuffer.append("\n[" + i + "] " + ((X509CRLEntry) it.next()).toString());
                i++;
            }
        }
        CRLExtensions cRLExtensions = this.extensions;
        if (cRLExtensions != null) {
            Object[] array = cRLExtensions.getAllExtensions().toArray();
            stringBuffer.append("\nCRL Extensions: " + array.length);
            int i2 = 0;
            while (i2 < array.length) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\n[");
                int i3 = i2 + 1;
                sb2.append(i3);
                sb2.append("]: ");
                stringBuffer.append(sb2.toString());
                Extension extension = (Extension) array[i2];
                try {
                    if (OIDMap.getClass(extension.getExtensionId()) == null) {
                        stringBuffer.append(extension.toString());
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
                        stringBuffer.append(extension.toString());
                    }
                } catch (Exception unused) {
                    stringBuffer.append(", Error parsing this extension");
                }
                i2 = i3;
            }
        }
        if (this.signature == null) {
            stringBuffer.append("NOT signed yet\n");
            return stringBuffer.toString();
        }
        HexDumpEncoder hexDumpEncoder2 = new HexDumpEncoder();
        new StringBuilder().append("\nSignature:\n");
        hexDumpEncoder2.encodeBuffer(this.signature);
        throw null;
    }

    public X500Principal getIssuerX500Principal() {
        if (this.issuerPrincipal == null) {
            this.issuerPrincipal = this.issuer.asX500Principal();
        }
        return this.issuerPrincipal;
    }

    private void parse(DerValue derValue) {
        if (this.readOnly) {
            throw new CRLException("cannot over-write existing CRL");
        } else if (derValue.getData() == null || derValue.tag != 48) {
            throw new CRLException("Invalid DER-encoded CRL data");
        } else {
            this.signedCRL = derValue.toByteArray();
            DerValue[] derValueArr = {derValue.data.getDerValue(), derValue.data.getDerValue(), derValue.data.getDerValue()};
            if (derValue.data.available() != 0) {
                throw new CRLException("signed overrun, bytes = " + derValue.data.available());
            } else if (derValueArr[0].tag == 48) {
                this.sigAlgId = AlgorithmId.parse(derValueArr[1]);
                this.signature = derValueArr[2].getBitString();
                if (derValueArr[1].data.available() != 0) {
                    throw new CRLException("AlgorithmId field overrun");
                } else if (derValueArr[2].data.available() == 0) {
                    this.tbsCertList = derValueArr[0].toByteArray();
                    DerInputStream derInputStream = derValueArr[0].data;
                    this.version = 0;
                    if (((byte) derInputStream.peekByte()) == 2) {
                        this.version = derInputStream.getInteger();
                        if (this.version != 1) {
                            throw new CRLException("Invalid version");
                        }
                    }
                    AlgorithmId parse = AlgorithmId.parse(derInputStream.getDerValue());
                    if (parse.equals(this.sigAlgId)) {
                        this.infoSigAlgId = parse;
                        this.issuer = new X500Name(derInputStream);
                        if (!this.issuer.isEmpty()) {
                            byte peekByte = (byte) derInputStream.peekByte();
                            if (peekByte == 23) {
                                this.thisUpdate = derInputStream.getUTCTime();
                            } else if (peekByte == 24) {
                                this.thisUpdate = derInputStream.getGeneralizedTime();
                            } else {
                                throw new CRLException("Invalid encoding for thisUpdate (tag=" + ((int) peekByte) + ")");
                            }
                            if (derInputStream.available() != 0) {
                                byte peekByte2 = (byte) derInputStream.peekByte();
                                if (peekByte2 == 23) {
                                    this.nextUpdate = derInputStream.getUTCTime();
                                } else if (peekByte2 == 24) {
                                    this.nextUpdate = derInputStream.getGeneralizedTime();
                                }
                                if (derInputStream.available() != 0) {
                                    byte peekByte3 = (byte) derInputStream.peekByte();
                                    if (peekByte3 == 48 && (peekByte3 & 192) != 128) {
                                        DerValue[] sequence = derInputStream.getSequence(4);
                                        X500Principal issuerX500Principal = getIssuerX500Principal();
                                        X500Principal x500Principal = issuerX500Principal;
                                        for (DerValue derValue2 : sequence) {
                                            X509CRLEntryImpl x509CRLEntryImpl = new X509CRLEntryImpl(derValue2);
                                            x500Principal = getCertIssuer(x509CRLEntryImpl, x500Principal);
                                            x509CRLEntryImpl.setCertificateIssuer(issuerX500Principal, x500Principal);
                                            this.revokedMap.put(new X509IssuerSerial(x500Principal, x509CRLEntryImpl.getSerialNumber()), x509CRLEntryImpl);
                                            this.revokedList.add(x509CRLEntryImpl);
                                        }
                                    }
                                    if (derInputStream.available() != 0) {
                                        DerValue derValue3 = derInputStream.getDerValue();
                                        if (derValue3.isConstructed() && derValue3.isContextSpecific((byte) 0)) {
                                            this.extensions = new CRLExtensions(derValue3.data);
                                        }
                                        this.readOnly = true;
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        throw new CRLException("Empty issuer DN not allowed in X509CRLs");
                    }
                    throw new CRLException("Signature algorithm mismatch");
                } else {
                    throw new CRLException("Signature field overrun");
                }
            } else {
                throw new CRLException("signed CRL fields invalid");
            }
        }
    }

    public static byte[] getEncodedInternal(X509CRL x509crl) {
        if (x509crl instanceof X509CRLImpl) {
            return ((X509CRLImpl) x509crl).getEncodedInternal();
        }
        return x509crl.getEncoded();
    }

    private X500Principal getCertIssuer(X509CRLEntryImpl x509CRLEntryImpl, X500Principal x500Principal) {
        CertificateIssuerExtension certificateIssuerExtension = x509CRLEntryImpl.getCertificateIssuerExtension();
        return certificateIssuerExtension != null ? ((X500Name) certificateIssuerExtension.get("issuer").get(0).getName()).asX500Principal() : x500Principal;
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream outputStream) {
        byte[] bArr = this.signedCRL;
        if (bArr != null) {
            outputStream.write((byte[]) bArr.clone());
            return;
        }
        throw new IOException("Null CRL to encode");
    }

    /* access modifiers changed from: private */
    public static final class X509IssuerSerial implements Comparable {
        volatile int hashcode = 0;
        final X500Principal issuer;
        final BigInteger serial;

        X509IssuerSerial(X500Principal x500Principal, BigInteger bigInteger) {
            this.issuer = x500Principal;
            this.serial = bigInteger;
        }

        /* access modifiers changed from: package-private */
        public X500Principal getIssuer() {
            return this.issuer;
        }

        /* access modifiers changed from: package-private */
        public BigInteger getSerial() {
            return this.serial;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof X509IssuerSerial)) {
                return false;
            }
            X509IssuerSerial x509IssuerSerial = (X509IssuerSerial) obj;
            return this.serial.equals(x509IssuerSerial.getSerial()) && this.issuer.equals(x509IssuerSerial.getIssuer());
        }

        public int hashCode() {
            if (this.hashcode == 0) {
                this.hashcode = ((629 + this.issuer.hashCode()) * 37) + this.serial.hashCode();
            }
            return this.hashcode;
        }

        public int compareTo(X509IssuerSerial x509IssuerSerial) {
            int compareTo = this.issuer.toString().compareTo(x509IssuerSerial.issuer.toString());
            if (compareTo != 0) {
                return compareTo;
            }
            return this.serial.compareTo(x509IssuerSerial.serial);
        }
    }
}
