package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class X509CertImpl extends X509Certificate implements DerEncoder {
    private static final long serialVersionUID = -3457612960190864406L;
    protected AlgorithmId algId = null;
    private Set authInfoAccess;
    private List extKeyUsage;
    private ConcurrentHashMap fingerprints = new ConcurrentHashMap(2);
    protected X509CertInfo info = null;
    private Collection issuerAlternativeNames;
    private boolean readOnly = false;
    protected byte[] signature = null;
    private byte[] signedCert = null;
    private Collection subjectAlternativeNames;
    private boolean verificationResult;
    private String verifiedProvider;
    private PublicKey verifiedPublicKey;

    public X509CertImpl() {
    }

    public X509CertImpl(DerValue derValue, byte[] bArr) {
        try {
            parse(derValue, bArr);
        } catch (IOException e) {
            this.signedCert = null;
            throw new CertificateException("Unable to initialize, " + e, e);
        }
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream outputStream) {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            outputStream.write((byte[]) bArr.clone());
            return;
        }
        throw new IOException("Null certificate to encode");
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() {
        return (byte[]) getEncodedInternal().clone();
    }

    public byte[] getEncodedInternal() {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            return bArr;
        }
        throw new CertificateEncodingException("Null certificate to encode");
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey) {
        verify(publicKey, "");
    }

    public synchronized void verify(PublicKey publicKey, String str) {
        Signature signature2;
        if (str == null) {
            str = "";
        }
        if (this.verifiedPublicKey == null || !this.verifiedPublicKey.equals(publicKey) || !str.equals(this.verifiedProvider)) {
            if (this.signedCert != null) {
                if (str.length() == 0) {
                    signature2 = Signature.getInstance(this.algId.getName());
                } else {
                    signature2 = Signature.getInstance(this.algId.getName(), str);
                }
                signature2.initVerify(publicKey);
                byte[] encodedInfo = this.info.getEncodedInfo();
                signature2.update(encodedInfo, 0, encodedInfo.length);
                this.verificationResult = signature2.verify(this.signature);
                this.verifiedPublicKey = publicKey;
                this.verifiedProvider = str;
                if (!this.verificationResult) {
                    throw new SignatureException("Signature does not match.");
                }
                return;
            }
            throw new CertificateEncodingException("Uninitialized certificate");
        } else if (!this.verificationResult) {
            throw new SignatureException("Signature does not match.");
        }
    }

    public Object get(String str) {
        X509AttributeName x509AttributeName = new X509AttributeName(str);
        String prefix = x509AttributeName.getPrefix();
        if (prefix.equalsIgnoreCase("x509")) {
            X509AttributeName x509AttributeName2 = new X509AttributeName(x509AttributeName.getSuffix());
            String prefix2 = x509AttributeName2.getPrefix();
            if (prefix2.equalsIgnoreCase("info")) {
                if (this.info == null) {
                    return null;
                }
                if (x509AttributeName2.getSuffix() == null) {
                    return this.info;
                }
                try {
                    return this.info.get(x509AttributeName2.getSuffix());
                } catch (IOException e) {
                    throw new CertificateParsingException(e.toString());
                } catch (CertificateException e2) {
                    throw new CertificateParsingException(e2.toString());
                }
            } else if (prefix2.equalsIgnoreCase("algorithm")) {
                return this.algId;
            } else {
                if (prefix2.equalsIgnoreCase("signature")) {
                    byte[] bArr = this.signature;
                    if (bArr != null) {
                        return bArr.clone();
                    }
                    return null;
                } else if (prefix2.equalsIgnoreCase("signed_cert")) {
                    byte[] bArr2 = this.signedCert;
                    if (bArr2 != null) {
                        return bArr2.clone();
                    }
                    return null;
                } else {
                    throw new CertificateParsingException("Attribute name not recognized or get() not allowed for the same: " + prefix2);
                }
            }
        } else {
            throw new CertificateParsingException("Invalid root of attribute name, expected [x509], received [" + prefix + "]");
        }
    }

    public String toString() {
        if (this.info == null || this.algId == null || this.signature == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append(this.info.toString() + "\n");
        sb.append("  Algorithm: [" + this.algId.toString() + "]\n");
        HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
        new StringBuilder().append("  Signature:\n");
        hexDumpEncoder.encodeBuffer(this.signature);
        throw null;
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (PublicKey) x509CertInfo.get("key.value");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        SerialNumber serialNumberObject = getSerialNumberObject();
        if (serialNumberObject != null) {
            return serialNumberObject.getNumber();
        }
        return null;
    }

    public SerialNumber getSerialNumberObject() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (SerialNumber) x509CertInfo.get("serialNumber.number");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public Principal getSubjectDN() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (Principal) x509CertInfo.get("subject.dname");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getSubjectX500Principal() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (X500Principal) x509CertInfo.get("subject.x500principal");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public Principal getIssuerDN() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (Principal) x509CertInfo.get("issuer.dname");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getIssuerX500Principal() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (X500Principal) x509CertInfo.get("issuer.x500principal");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo != null) {
            return x509CertInfo.getEncodedInfo();
        }
        throw new CertificateEncodingException("Uninitialized certificate");
    }

    public SubjectAlternativeNameExtension getSubjectAlternativeNameExtension() {
        return (SubjectAlternativeNameExtension) getExtension(PKIXExtensions.SubjectAlternativeName_Id);
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return false;
        }
        try {
            CertificateExtensions certificateExtensions = (CertificateExtensions) x509CertInfo.get("extensions");
            if (certificateExtensions == null) {
                return false;
            }
            return certificateExtensions.hasUnsupportedCriticalExtension();
        } catch (Exception unused) {
            return false;
        }
    }

    public Extension getExtension(ObjectIdentifier objectIdentifier) {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            CertificateExtensions certificateExtensions = (CertificateExtensions) x509CertInfo.get("extensions");
            if (certificateExtensions == null) {
                return null;
            }
            Extension extension = certificateExtensions.getExtension(objectIdentifier.toString());
            if (extension != null) {
                return extension;
            }
            for (Extension extension2 : certificateExtensions.getAllExtensions()) {
                if (extension2.getExtensionId().equals(objectIdentifier)) {
                    return extension2;
                }
            }
            return null;
        } catch (IOException | CertificateException unused) {
        }
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        Extension extension;
        Extension extension2;
        try {
            ObjectIdentifier objectIdentifier = new ObjectIdentifier(str);
            String name = OIDMap.getName(objectIdentifier);
            CertificateExtensions certificateExtensions = (CertificateExtensions) this.info.get("extensions");
            if (name != null) {
                try {
                    extension = (Extension) get(name);
                } catch (CertificateException unused) {
                    extension = null;
                }
            } else if (certificateExtensions == null) {
                return null;
            } else {
                Iterator it = certificateExtensions.getAllExtensions().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        extension2 = null;
                        break;
                    }
                    extension2 = (Extension) it.next();
                    if (extension2.getExtensionId().equals(objectIdentifier)) {
                        break;
                    }
                }
                extension = extension2;
            }
            if (extension == null) {
                if (certificateExtensions != null) {
                    extension = (Extension) certificateExtensions.getUnparseableExtensions().get(str);
                }
                if (extension == null) {
                    return null;
                }
            }
            byte[] extensionValue = extension.getExtensionValue();
            if (extensionValue == null) {
                return null;
            }
            DerOutputStream derOutputStream = new DerOutputStream();
            derOutputStream.putOctetString(extensionValue);
            return derOutputStream.toByteArray();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        KeyUsageExtension keyUsageExtension;
        try {
            String name = OIDMap.getName(PKIXExtensions.KeyUsage_Id);
            if (name == null || (keyUsageExtension = (KeyUsageExtension) get(name)) == null) {
                return null;
            }
            boolean[] bits = keyUsageExtension.getBits();
            if (bits.length >= 9) {
                return bits;
            }
            boolean[] zArr = new boolean[9];
            System.arraycopy(bits, 0, zArr, 0, bits.length);
            return zArr;
        } catch (Exception unused) {
            return null;
        }
    }

    private static Collection makeAltNames(GeneralNames generalNames) {
        if (generalNames.isEmpty()) {
            return Collections.emptySet();
        }
        ArrayList arrayList = new ArrayList();
        for (GeneralName generalName : generalNames.names()) {
            GeneralNameInterface name = generalName.getName();
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Integer.valueOf(name.getType()));
            int type = name.getType();
            if (type == 1) {
                arrayList2.add(((RFC822Name) name).getName());
            } else if (type == 2) {
                arrayList2.add(((DNSName) name).getName());
            } else if (type == 4) {
                arrayList2.add(((X500Name) name).getRFC2253Name());
            } else if (type == 6) {
                arrayList2.add(((URIName) name).getName());
            } else if (type == 7) {
                try {
                    arrayList2.add(((IPAddressName) name).getName());
                } catch (IOException e) {
                    throw new RuntimeException("IPAddress cannot be parsed", e);
                }
            } else if (type != 8) {
                DerOutputStream derOutputStream = new DerOutputStream();
                try {
                    name.encode(derOutputStream);
                    arrayList2.add(derOutputStream.toByteArray());
                } catch (IOException e2) {
                    throw new RuntimeException("name cannot be encoded", e2);
                }
            } else {
                arrayList2.add(((OIDName) name).getOID().toString());
            }
            arrayList.add(Collections.unmodifiableList(arrayList2));
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    private static Collection cloneAltNames(Collection collection) {
        Iterator it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((List) it.next()).get(1) instanceof byte[]) {
                z = true;
            }
        }
        if (!z) {
            return collection;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            List list = (List) it2.next();
            Object obj = list.get(1);
            if (obj instanceof byte[]) {
                ArrayList arrayList2 = new ArrayList(list);
                arrayList2.set(1, ((byte[]) obj).clone());
                arrayList.add(Collections.unmodifiableList(arrayList2));
            } else {
                arrayList.add(list);
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    @Override // java.security.cert.X509Certificate
    public synchronized Collection getSubjectAlternativeNames() {
        if (!this.readOnly || this.subjectAlternativeNames == null) {
            SubjectAlternativeNameExtension subjectAlternativeNameExtension = getSubjectAlternativeNameExtension();
            if (subjectAlternativeNameExtension == null) {
                return null;
            }
            try {
                this.subjectAlternativeNames = makeAltNames(subjectAlternativeNameExtension.get("subject_name"));
                return this.subjectAlternativeNames;
            } catch (IOException unused) {
                return Collections.emptySet();
            }
        } else {
            return cloneAltNames(this.subjectAlternativeNames);
        }
    }

    public static Collection getSubjectAlternativeNames(X509Certificate x509Certificate) {
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue("2.5.29.17");
            if (extensionValue == null) {
                return null;
            }
            try {
                return makeAltNames(new SubjectAlternativeNameExtension(Boolean.FALSE, new DerValue(extensionValue).getOctetString()).get("subject_name"));
            } catch (IOException unused) {
                return Collections.emptySet();
            }
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    private void parse(DerValue derValue, byte[] bArr) {
        if (this.readOnly) {
            throw new CertificateParsingException("cannot over-write existing certificate");
        } else if (derValue.data == null || derValue.tag != 48) {
            throw new CertificateParsingException("invalid DER-encoded certificate data");
        } else {
            if (bArr == null) {
                bArr = derValue.toByteArray();
            }
            this.signedCert = bArr;
            DerValue[] derValueArr = {derValue.data.getDerValue(), derValue.data.getDerValue(), derValue.data.getDerValue()};
            if (derValue.data.available() != 0) {
                throw new CertificateParsingException("signed overrun, bytes = " + derValue.data.available());
            } else if (derValueArr[0].tag == 48) {
                this.algId = AlgorithmId.parse(derValueArr[1]);
                this.signature = derValueArr[2].getBitString();
                if (derValueArr[1].data.available() != 0) {
                    throw new CertificateParsingException("algid field overrun");
                } else if (derValueArr[2].data.available() == 0) {
                    this.info = new X509CertInfo(derValueArr[0]);
                    if (this.algId.equals((AlgorithmId) this.info.get("algorithmID.algorithm"))) {
                        this.readOnly = true;
                        return;
                    }
                    throw new CertificateException("Signature algorithm mismatch");
                } else {
                    throw new CertificateParsingException("signed fields overrun");
                }
            } else {
                throw new CertificateParsingException("signed fields invalid");
            }
        }
    }

    private static X500Principal getX500Principal(X509Certificate x509Certificate, boolean z) {
        DerInputStream derInputStream = new DerInputStream(x509Certificate.getEncoded()).getSequence(3)[0].data;
        if (derInputStream.getDerValue().isContextSpecific((byte) 0)) {
            derInputStream.getDerValue();
        }
        derInputStream.getDerValue();
        DerValue derValue = derInputStream.getDerValue();
        if (!z) {
            derInputStream.getDerValue();
            derValue = derInputStream.getDerValue();
        }
        return new X500Principal(derValue.toByteArray());
    }

    public static X500Principal getSubjectX500Principal(X509Certificate x509Certificate) {
        try {
            return getX500Principal(x509Certificate, false);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse subject", e);
        }
    }

    public static X500Principal getIssuerX500Principal(X509Certificate x509Certificate) {
        try {
            return getX500Principal(x509Certificate, true);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse issuer", e);
        }
    }

    public static byte[] getEncodedInternal(Certificate certificate) {
        if (certificate instanceof X509CertImpl) {
            return ((X509CertImpl) certificate).getEncodedInternal();
        }
        return certificate.getEncoded();
    }
}
