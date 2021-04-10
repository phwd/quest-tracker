package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.util.HashMap;
import java.util.Map;
import sun.misc.HexDumpEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class X509CertInfo implements CertAttrSet {
    private static final Map map = new HashMap();
    protected CertificateAlgorithmId algId = null;
    protected CertificateExtensions extensions = null;
    protected CertificateValidity interval = null;
    protected X500Name issuer = null;
    protected UniqueIdentity issuerUniqueId = null;
    protected CertificateX509Key pubKey = null;
    private byte[] rawCertInfo = null;
    protected CertificateSerialNumber serialNum = null;
    protected X500Name subject = null;
    protected UniqueIdentity subjectUniqueId = null;
    protected CertificateVersion version = new CertificateVersion();

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "info";
    }

    static {
        map.put("version", 1);
        map.put("serialNumber", 2);
        map.put("algorithmID", 3);
        map.put("issuer", 4);
        map.put("validity", 5);
        map.put("subject", 6);
        map.put("key", 7);
        map.put("issuerID", 8);
        map.put("subjectID", 9);
        map.put("extensions", 10);
    }

    public X509CertInfo() {
    }

    public X509CertInfo(byte[] bArr) {
        try {
            parse(new DerValue(bArr));
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    public X509CertInfo(DerValue derValue) {
        try {
            parse(derValue);
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        if (this.rawCertInfo == null) {
            DerOutputStream derOutputStream = new DerOutputStream();
            emit(derOutputStream);
            this.rawCertInfo = derOutputStream.toByteArray();
        }
        outputStream.write((byte[]) this.rawCertInfo.clone());
    }

    public byte[] getEncodedInfo() {
        try {
            if (this.rawCertInfo == null) {
                DerOutputStream derOutputStream = new DerOutputStream();
                emit(derOutputStream);
                this.rawCertInfo = derOutputStream.toByteArray();
            }
            return (byte[]) this.rawCertInfo.clone();
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        } catch (CertificateException e2) {
            throw new CertificateEncodingException(e2.toString());
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof X509CertInfo) {
            return equals((X509CertInfo) obj);
        }
        return false;
    }

    public boolean equals(X509CertInfo x509CertInfo) {
        byte[] bArr;
        if (this == x509CertInfo) {
            return true;
        }
        byte[] bArr2 = this.rawCertInfo;
        if (bArr2 == null || (bArr = x509CertInfo.rawCertInfo) == null || bArr2.length != bArr.length) {
            return false;
        }
        int i = 0;
        while (true) {
            byte[] bArr3 = this.rawCertInfo;
            if (i >= bArr3.length) {
                return true;
            }
            if (bArr3[i] != x509CertInfo.rawCertInfo[i]) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int i = 1;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.rawCertInfo;
            if (i >= bArr.length) {
                return i2;
            }
            i2 += bArr[i] * i;
            i++;
        }
    }

    public String toString() {
        if (this.subject == null || this.pubKey == null || this.interval == null || this.issuer == null || this.algId == null || this.serialNum == null) {
            throw new NullPointerException("X.509 cert is incomplete");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append("  " + this.version.toString() + "\n");
        sb.append("  Subject: " + this.subject.toString() + "\n");
        sb.append("  Signature Algorithm: " + this.algId.toString() + "\n");
        sb.append("  Key:  " + this.pubKey.toString() + "\n");
        sb.append("  " + this.interval.toString() + "\n");
        sb.append("  Issuer: " + this.issuer.toString() + "\n");
        sb.append("  " + this.serialNum.toString() + "\n");
        if (this.issuerUniqueId != null) {
            new StringBuilder().append("  Issuer Id:\n");
            this.issuerUniqueId.toString();
            throw null;
        } else if (this.subjectUniqueId == null) {
            CertificateExtensions certificateExtensions = this.extensions;
            if (certificateExtensions != null) {
                int i = 0;
                Extension[] extensionArr = (Extension[]) certificateExtensions.getAllExtensions().toArray(new Extension[0]);
                sb.append("\nCertificate Extensions: " + extensionArr.length);
                while (i < extensionArr.length) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\n[");
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
                Map unparseableExtensions = this.extensions.getUnparseableExtensions();
                if (!unparseableExtensions.isEmpty()) {
                    sb.append("\nUnparseable certificate extensions: " + unparseableExtensions.size());
                    int i3 = 1;
                    for (Extension extension2 : unparseableExtensions.values()) {
                        sb.append("\n[" + i3 + "]: ");
                        sb.append(extension2);
                        i3++;
                    }
                }
            }
            sb.append("\n]");
            return sb.toString();
        } else {
            new StringBuilder().append("  Subject Id:\n");
            this.subjectUniqueId.toString();
            throw null;
        }
    }

    public Object get(String str) {
        X509AttributeName x509AttributeName = new X509AttributeName(str);
        int attributeMap = attributeMap(x509AttributeName.getPrefix());
        if (attributeMap != 0) {
            String suffix = x509AttributeName.getSuffix();
            switch (attributeMap) {
                case 1:
                    if (suffix == null) {
                        return this.version;
                    }
                    return this.version.get(suffix);
                case 2:
                    if (suffix == null) {
                        return this.serialNum;
                    }
                    return this.serialNum.get(suffix);
                case 3:
                    if (suffix == null) {
                        return this.algId;
                    }
                    return this.algId.get(suffix);
                case 4:
                    if (suffix == null) {
                        return this.issuer;
                    }
                    return getX500Name(suffix, true);
                case 5:
                    if (suffix == null) {
                        return this.interval;
                    }
                    return this.interval.get(suffix);
                case 6:
                    if (suffix == null) {
                        return this.subject;
                    }
                    return getX500Name(suffix, false);
                case 7:
                    if (suffix == null) {
                        return this.pubKey;
                    }
                    return this.pubKey.get(suffix);
                case 8:
                    return this.issuerUniqueId;
                case 9:
                    return this.subjectUniqueId;
                case 10:
                    if (suffix == null) {
                        return this.extensions;
                    }
                    CertificateExtensions certificateExtensions = this.extensions;
                    if (certificateExtensions == null) {
                        return null;
                    }
                    return certificateExtensions.get(suffix);
                default:
                    return null;
            }
        } else {
            throw new CertificateParsingException("Attribute name not recognized: " + str);
        }
    }

    private Object getX500Name(String str, boolean z) {
        if (str.equalsIgnoreCase("dname")) {
            return z ? this.issuer : this.subject;
        }
        if (!str.equalsIgnoreCase("x500principal")) {
            throw new IOException("Attribute name not recognized.");
        } else if (z) {
            return this.issuer.asX500Principal();
        } else {
            return this.subject.asX500Principal();
        }
    }

    private void parse(DerValue derValue) {
        if (derValue.tag == 48) {
            this.rawCertInfo = derValue.toByteArray();
            DerInputStream derInputStream = derValue.data;
            DerValue derValue2 = derInputStream.getDerValue();
            if (derValue2.isContextSpecific((byte) 0)) {
                this.version = new CertificateVersion(derValue2);
                derValue2 = derInputStream.getDerValue();
            }
            this.serialNum = new CertificateSerialNumber(derValue2);
            this.algId = new CertificateAlgorithmId(derInputStream);
            this.issuer = new X500Name(derInputStream);
            if (!this.issuer.isEmpty()) {
                this.interval = new CertificateValidity(derInputStream);
                this.subject = new X500Name(derInputStream);
                if (this.version.compare(0) != 0 || !this.subject.isEmpty()) {
                    this.pubKey = new CertificateX509Key(derInputStream);
                    if (derInputStream.available() == 0) {
                        return;
                    }
                    if (this.version.compare(0) != 0) {
                        DerValue derValue3 = derInputStream.getDerValue();
                        if (derValue3.isContextSpecific((byte) 1)) {
                            this.issuerUniqueId = new UniqueIdentity(derValue3);
                            if (derInputStream.available() != 0) {
                                derValue3 = derInputStream.getDerValue();
                            } else {
                                return;
                            }
                        }
                        if (derValue3.isContextSpecific((byte) 2)) {
                            this.subjectUniqueId = new UniqueIdentity(derValue3);
                            if (derInputStream.available() != 0) {
                                derValue3 = derInputStream.getDerValue();
                            } else {
                                return;
                            }
                        }
                        if (this.version.compare(2) == 0) {
                            if (derValue3.isConstructed() && derValue3.isContextSpecific((byte) 3)) {
                                this.extensions = new CertificateExtensions(derValue3.data);
                            }
                            verifyCert(this.subject, this.extensions);
                            return;
                        }
                        throw new CertificateParsingException("Extensions not allowed in v2 certificate");
                    }
                    throw new CertificateParsingException("no more data allowed for version 1 certificate");
                }
                throw new CertificateParsingException("Empty subject DN not allowed in v1 certificate");
            }
            throw new CertificateParsingException("Empty issuer DN not allowed in X509Certificates");
        }
        throw new CertificateParsingException("signed fields invalid");
    }

    private void verifyCert(X500Name x500Name, CertificateExtensions certificateExtensions) {
        if (!x500Name.isEmpty()) {
            return;
        }
        if (certificateExtensions != null) {
            try {
                SubjectAlternativeNameExtension subjectAlternativeNameExtension = (SubjectAlternativeNameExtension) certificateExtensions.get("SubjectAlternativeName");
                GeneralNames generalNames = subjectAlternativeNameExtension.get("subject_name");
                if (generalNames == null || generalNames.isEmpty()) {
                    throw new CertificateParsingException("X.509 Certificate is incomplete: subject field is empty, and SubjectAlternativeName extension is empty");
                } else if (!subjectAlternativeNameExtension.isCritical()) {
                    throw new CertificateParsingException("X.509 Certificate is incomplete: SubjectAlternativeName extension MUST be marked critical when subject field is empty");
                }
            } catch (IOException unused) {
                throw new CertificateParsingException("X.509 Certificate is incomplete: subject field is empty, and SubjectAlternativeName extension is absent");
            }
        } else {
            throw new CertificateParsingException("X.509 Certificate is incomplete: subject field is empty, and certificate has no extensions");
        }
    }

    private void emit(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        this.version.encode(derOutputStream2);
        this.serialNum.encode(derOutputStream2);
        this.algId.encode(derOutputStream2);
        if (this.version.compare(0) == 0 && this.issuer.toString() == null) {
            throw new CertificateParsingException("Null issuer DN not allowed in v1 certificate");
        }
        this.issuer.encode(derOutputStream2);
        this.interval.encode(derOutputStream2);
        if (this.version.compare(0) == 0 && this.subject.toString() == null) {
            throw new CertificateParsingException("Null subject DN not allowed in v1 certificate");
        }
        this.subject.encode(derOutputStream2);
        this.pubKey.encode(derOutputStream2);
        UniqueIdentity uniqueIdentity = this.issuerUniqueId;
        if (uniqueIdentity != null) {
            uniqueIdentity.encode(derOutputStream2, DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1));
        }
        UniqueIdentity uniqueIdentity2 = this.subjectUniqueId;
        if (uniqueIdentity2 != null) {
            uniqueIdentity2.encode(derOutputStream2, DerValue.createTag(Byte.MIN_VALUE, false, (byte) 2));
        }
        CertificateExtensions certificateExtensions = this.extensions;
        if (certificateExtensions != null) {
            certificateExtensions.encode(derOutputStream2);
        }
        derOutputStream.write((byte) 48, derOutputStream2);
    }

    private int attributeMap(String str) {
        Integer num = (Integer) map.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }
}
