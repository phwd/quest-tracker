package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;
import sun.security.provider.X509Factory;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class X509CertImpl extends X509Certificate implements DerEncoder {
    public static final String ALG_ID = "algorithm";
    private static final String AUTH_INFO_ACCESS_OID = "1.3.6.1.5.5.7.1.1";
    private static final String BASIC_CONSTRAINT_OID = "2.5.29.19";
    private static final String DOT = ".";
    private static final String EXTENDED_KEY_USAGE_OID = "2.5.29.37";
    public static final String INFO = "info";
    private static final String ISSUER_ALT_NAME_OID = "2.5.29.18";
    public static final String ISSUER_DN = "x509.info.issuer.dname";
    private static final String KEY_USAGE_OID = "2.5.29.15";
    public static final String NAME = "x509";
    private static final int NUM_STANDARD_KEY_USAGE = 9;
    public static final String PUBLIC_KEY = "x509.info.key.value";
    public static final String SERIAL_ID = "x509.info.serialNumber.number";
    public static final String SIG = "x509.signature";
    public static final String SIGNATURE = "signature";
    public static final String SIGNED_CERT = "signed_cert";
    public static final String SIG_ALG = "x509.algorithm";
    private static final String SUBJECT_ALT_NAME_OID = "2.5.29.17";
    public static final String SUBJECT_DN = "x509.info.subject.dname";
    public static final String VERSION = "x509.info.version.number";
    private static final long serialVersionUID = -3457612960190864406L;
    protected AlgorithmId algId = null;
    private Set<AccessDescription> authInfoAccess;
    private List<String> extKeyUsage;
    private ConcurrentHashMap<String, String> fingerprints = new ConcurrentHashMap<>(2);
    protected X509CertInfo info = null;
    private Collection<List<?>> issuerAlternativeNames;
    private boolean readOnly = false;
    protected byte[] signature = null;
    private byte[] signedCert = null;
    private Collection<List<?>> subjectAlternativeNames;
    private boolean verificationResult;
    private String verifiedProvider;
    private PublicKey verifiedPublicKey;

    public X509CertImpl() {
    }

    public X509CertImpl(byte[] certData) throws CertificateException {
        try {
            parse(new DerValue(certData));
        } catch (IOException e) {
            this.signedCert = null;
            throw new CertificateException("Unable to initialize, " + ((Object) e), e);
        }
    }

    public X509CertImpl(X509CertInfo certInfo) {
        this.info = certInfo;
    }

    public X509CertImpl(DerValue derVal) throws CertificateException {
        try {
            parse(derVal);
        } catch (IOException e) {
            this.signedCert = null;
            throw new CertificateException("Unable to initialize, " + ((Object) e), e);
        }
    }

    public X509CertImpl(DerValue derVal, byte[] encoded) throws CertificateException {
        try {
            parse(derVal, encoded);
        } catch (IOException e) {
            this.signedCert = null;
            throw new CertificateException("Unable to initialize, " + ((Object) e), e);
        }
    }

    public void encode(OutputStream out) throws CertificateEncodingException {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            try {
                out.write((byte[]) bArr.clone());
            } catch (IOException e) {
                throw new CertificateEncodingException(e.toString());
            }
        } else {
            throw new CertificateEncodingException("Null certificate to encode");
        }
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream out) throws IOException {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            out.write((byte[]) bArr.clone());
            return;
        }
        throw new IOException("Null certificate to encode");
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        return (byte[]) getEncodedInternal().clone();
    }

    public byte[] getEncodedInternal() throws CertificateEncodingException {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            return bArr;
        }
        throw new CertificateEncodingException("Null certificate to encode");
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        verify(key, "");
    }

    @Override // java.security.cert.Certificate
    public synchronized void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature sigVerf;
        if (sigProvider == null) {
            sigProvider = "";
        }
        if (this.verifiedPublicKey == null || !this.verifiedPublicKey.equals(key) || !sigProvider.equals(this.verifiedProvider)) {
            if (this.signedCert != null) {
                if (sigProvider.length() == 0) {
                    sigVerf = Signature.getInstance(this.algId.getName());
                } else {
                    sigVerf = Signature.getInstance(this.algId.getName(), sigProvider);
                }
                sigVerf.initVerify(key);
                byte[] rawCert = this.info.getEncodedInfo();
                sigVerf.update(rawCert, 0, rawCert.length);
                this.verificationResult = sigVerf.verify(this.signature);
                this.verifiedPublicKey = key;
                this.verifiedProvider = sigProvider;
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

    @Override // java.security.cert.X509Certificate, java.security.cert.Certificate
    public synchronized void verify(PublicKey key, Provider sigProvider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sigVerf;
        if (this.signedCert != null) {
            if (sigProvider == null) {
                sigVerf = Signature.getInstance(this.algId.getName());
            } else {
                sigVerf = Signature.getInstance(this.algId.getName(), sigProvider);
            }
            sigVerf.initVerify(key);
            byte[] rawCert = this.info.getEncodedInfo();
            sigVerf.update(rawCert, 0, rawCert.length);
            this.verificationResult = sigVerf.verify(this.signature);
            this.verifiedPublicKey = key;
            if (!this.verificationResult) {
                throw new SignatureException("Signature does not match.");
            }
        } else {
            throw new CertificateEncodingException("Uninitialized certificate");
        }
    }

    public static void verify(X509Certificate cert, PublicKey key, Provider sigProvider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        cert.verify(key, sigProvider);
    }

    public void sign(PrivateKey key, String algorithm) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        sign(key, algorithm, null);
    }

    public void sign(PrivateKey key, String algorithm, String provider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature sigEngine;
        try {
            if (!this.readOnly) {
                if (provider != null) {
                    if (provider.length() != 0) {
                        sigEngine = Signature.getInstance(algorithm, provider);
                        sigEngine.initSign(key);
                        this.algId = AlgorithmId.get(sigEngine.getAlgorithm());
                        DerOutputStream out = new DerOutputStream();
                        DerOutputStream tmp = new DerOutputStream();
                        this.info.encode(tmp);
                        byte[] rawCert = tmp.toByteArray();
                        this.algId.encode(tmp);
                        sigEngine.update(rawCert, 0, rawCert.length);
                        this.signature = sigEngine.sign();
                        tmp.putBitString(this.signature);
                        out.write((byte) 48, tmp);
                        this.signedCert = out.toByteArray();
                        this.readOnly = true;
                        return;
                    }
                }
                sigEngine = Signature.getInstance(algorithm);
                sigEngine.initSign(key);
                this.algId = AlgorithmId.get(sigEngine.getAlgorithm());
                DerOutputStream out2 = new DerOutputStream();
                DerOutputStream tmp2 = new DerOutputStream();
                this.info.encode(tmp2);
                byte[] rawCert2 = tmp2.toByteArray();
                this.algId.encode(tmp2);
                sigEngine.update(rawCert2, 0, rawCert2.length);
                this.signature = sigEngine.sign();
                tmp2.putBitString(this.signature);
                out2.write((byte) 48, tmp2);
                this.signedCert = out2.toByteArray();
                this.readOnly = true;
                return;
            }
            throw new CertificateEncodingException("cannot over-write existing certificate");
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(new Date());
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        try {
            CertificateValidity interval = (CertificateValidity) this.info.get("validity");
            if (interval != null) {
                interval.valid(date);
                return;
            }
            throw new CertificateNotYetValidException("Null validity period");
        } catch (Exception e) {
            throw new CertificateNotYetValidException("Incorrect validity period");
        }
    }

    public Object get(String name) throws CertificateParsingException {
        X509AttributeName attr = new X509AttributeName(name);
        String id = attr.getPrefix();
        if (id.equalsIgnoreCase(NAME)) {
            X509AttributeName attr2 = new X509AttributeName(attr.getSuffix());
            String id2 = attr2.getPrefix();
            if (id2.equalsIgnoreCase("info")) {
                if (this.info == null) {
                    return null;
                }
                if (attr2.getSuffix() == null) {
                    return this.info;
                }
                try {
                    return this.info.get(attr2.getSuffix());
                } catch (IOException e) {
                    throw new CertificateParsingException(e.toString());
                } catch (CertificateException e2) {
                    throw new CertificateParsingException(e2.toString());
                }
            } else if (id2.equalsIgnoreCase("algorithm")) {
                return this.algId;
            } else {
                if (id2.equalsIgnoreCase(SIGNATURE)) {
                    byte[] bArr = this.signature;
                    if (bArr != null) {
                        return bArr.clone();
                    }
                    return null;
                } else if (id2.equalsIgnoreCase(SIGNED_CERT)) {
                    byte[] bArr2 = this.signedCert;
                    if (bArr2 != null) {
                        return bArr2.clone();
                    }
                    return null;
                } else {
                    throw new CertificateParsingException("Attribute name not recognized or get() not allowed for the same: " + id2);
                }
            }
        } else {
            throw new CertificateParsingException("Invalid root of attribute name, expected [x509], received [" + id + "]");
        }
    }

    public void set(String name, Object obj) throws CertificateException, IOException {
        if (!this.readOnly) {
            X509AttributeName attr = new X509AttributeName(name);
            String id = attr.getPrefix();
            if (id.equalsIgnoreCase(NAME)) {
                X509AttributeName attr2 = new X509AttributeName(attr.getSuffix());
                String id2 = attr2.getPrefix();
                if (!id2.equalsIgnoreCase("info")) {
                    throw new CertificateException("Attribute name not recognized or set() not allowed for the same: " + id2);
                } else if (attr2.getSuffix() != null) {
                    this.info.set(attr2.getSuffix(), obj);
                    this.signedCert = null;
                } else if (obj instanceof X509CertInfo) {
                    this.info = (X509CertInfo) obj;
                    this.signedCert = null;
                } else {
                    throw new CertificateException("Attribute value should be of type X509CertInfo.");
                }
            } else {
                throw new CertificateException("Invalid root of attribute name, expected [x509], received " + id);
            }
        } else {
            throw new CertificateException("cannot over-write existing certificate");
        }
    }

    public void delete(String name) throws CertificateException, IOException {
        if (!this.readOnly) {
            X509AttributeName attr = new X509AttributeName(name);
            String id = attr.getPrefix();
            if (id.equalsIgnoreCase(NAME)) {
                X509AttributeName attr2 = new X509AttributeName(attr.getSuffix());
                String id2 = attr2.getPrefix();
                if (id2.equalsIgnoreCase("info")) {
                    if (attr2.getSuffix() != null) {
                        this.info = null;
                    } else {
                        this.info.delete(attr2.getSuffix());
                    }
                } else if (id2.equalsIgnoreCase("algorithm")) {
                    this.algId = null;
                } else if (id2.equalsIgnoreCase(SIGNATURE)) {
                    this.signature = null;
                } else if (id2.equalsIgnoreCase(SIGNED_CERT)) {
                    this.signedCert = null;
                } else {
                    throw new CertificateException("Attribute name not recognized or delete() not allowed for the same: " + id2);
                }
            } else {
                throw new CertificateException("Invalid root of attribute name, expected [x509], received " + id);
            }
        } else {
            throw new CertificateException("cannot over-write existing certificate");
        }
    }

    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement(X509CertInfo.IDENT);
        elements.addElement(SIG_ALG);
        elements.addElement(SIG);
        elements.addElement("x509.signed_cert");
        return elements.elements();
    }

    public String getName() {
        return NAME;
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        if (this.info == null || this.algId == null || this.signature == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append(this.info.toString() + "\n");
        sb.append("  Algorithm: [" + this.algId.toString() + "]\n");
        HexDumpEncoder encoder = new HexDumpEncoder();
        sb.append("  Signature:\n" + encoder.encodeBuffer(this.signature));
        sb.append("\n]");
        return sb.toString();
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (PublicKey) x509CertInfo.get("key.value");
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public int getVersion() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return -1;
        }
        try {
            return ((Integer) x509CertInfo.get("version.number")).intValue() + 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        SerialNumber ser = getSerialNumberObject();
        if (ser != null) {
            return ser.getNumber();
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotBefore() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (Date) x509CertInfo.get("validity.notBefore");
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotAfter() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            return (Date) x509CertInfo.get("validity.notAfter");
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo != null) {
            return x509CertInfo.getEncodedInfo();
        }
        throw new CertificateEncodingException("Uninitialized certificate");
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSignature() {
        byte[] bArr = this.signature;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgName() {
        AlgorithmId algorithmId = this.algId;
        if (algorithmId == null) {
            return null;
        }
        return algorithmId.getName();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgOID() {
        AlgorithmId algorithmId = this.algId;
        if (algorithmId == null) {
            return null;
        }
        return algorithmId.getOID().toString();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        AlgorithmId algorithmId = this.algId;
        if (algorithmId == null) {
            return null;
        }
        try {
            return algorithmId.getEncodedParams();
        } catch (IOException e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getIssuerUniqueID() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            UniqueIdentity id = (UniqueIdentity) x509CertInfo.get(X509CertInfo.ISSUER_ID);
            if (id == null) {
                return null;
            }
            return id.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getSubjectUniqueID() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            UniqueIdentity id = (UniqueIdentity) x509CertInfo.get(X509CertInfo.SUBJECT_ID);
            if (id == null) {
                return null;
            }
            return id.getId();
        } catch (Exception e) {
            return null;
        }
    }

    public KeyIdentifier getAuthKeyId() {
        AuthorityKeyIdentifierExtension aki = getAuthorityKeyIdentifierExtension();
        if (aki == null) {
            return null;
        }
        try {
            return (KeyIdentifier) aki.get("key_id");
        } catch (IOException e) {
            return null;
        }
    }

    public KeyIdentifier getSubjectKeyId() {
        SubjectKeyIdentifierExtension ski = getSubjectKeyIdentifierExtension();
        if (ski == null) {
            return null;
        }
        try {
            return ski.get("key_id");
        } catch (IOException e) {
            return null;
        }
    }

    public AuthorityKeyIdentifierExtension getAuthorityKeyIdentifierExtension() {
        return (AuthorityKeyIdentifierExtension) getExtension(PKIXExtensions.AuthorityKey_Id);
    }

    public BasicConstraintsExtension getBasicConstraintsExtension() {
        return (BasicConstraintsExtension) getExtension(PKIXExtensions.BasicConstraints_Id);
    }

    public CertificatePoliciesExtension getCertificatePoliciesExtension() {
        return (CertificatePoliciesExtension) getExtension(PKIXExtensions.CertificatePolicies_Id);
    }

    public ExtendedKeyUsageExtension getExtendedKeyUsageExtension() {
        return (ExtendedKeyUsageExtension) getExtension(PKIXExtensions.ExtendedKeyUsage_Id);
    }

    public IssuerAlternativeNameExtension getIssuerAlternativeNameExtension() {
        return (IssuerAlternativeNameExtension) getExtension(PKIXExtensions.IssuerAlternativeName_Id);
    }

    public NameConstraintsExtension getNameConstraintsExtension() {
        return (NameConstraintsExtension) getExtension(PKIXExtensions.NameConstraints_Id);
    }

    public PolicyConstraintsExtension getPolicyConstraintsExtension() {
        return (PolicyConstraintsExtension) getExtension(PKIXExtensions.PolicyConstraints_Id);
    }

    public PolicyMappingsExtension getPolicyMappingsExtension() {
        return (PolicyMappingsExtension) getExtension(PKIXExtensions.PolicyMappings_Id);
    }

    public PrivateKeyUsageExtension getPrivateKeyUsageExtension() {
        return (PrivateKeyUsageExtension) getExtension(PKIXExtensions.PrivateKeyUsage_Id);
    }

    public SubjectAlternativeNameExtension getSubjectAlternativeNameExtension() {
        return (SubjectAlternativeNameExtension) getExtension(PKIXExtensions.SubjectAlternativeName_Id);
    }

    public SubjectKeyIdentifierExtension getSubjectKeyIdentifierExtension() {
        return (SubjectKeyIdentifierExtension) getExtension(PKIXExtensions.SubjectKey_Id);
    }

    public CRLDistributionPointsExtension getCRLDistributionPointsExtension() {
        return (CRLDistributionPointsExtension) getExtension(PKIXExtensions.CRLDistributionPoints_Id);
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return false;
        }
        try {
            CertificateExtensions exts = (CertificateExtensions) x509CertInfo.get("extensions");
            if (exts == null) {
                return false;
            }
            return exts.hasUnsupportedCriticalExtension();
        } catch (Exception e) {
            return false;
        }
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getCriticalExtensionOIDs() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            CertificateExtensions exts = (CertificateExtensions) x509CertInfo.get("extensions");
            if (exts == null) {
                return null;
            }
            Set<String> extSet = new TreeSet<>();
            for (Extension ex : exts.getAllExtensions()) {
                if (ex.isCritical()) {
                    extSet.add(ex.getExtensionId().toString());
                }
            }
            return extSet;
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getNonCriticalExtensionOIDs() {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            CertificateExtensions exts = (CertificateExtensions) x509CertInfo.get("extensions");
            if (exts == null) {
                return null;
            }
            Set<String> extSet = new TreeSet<>();
            for (Extension ex : exts.getAllExtensions()) {
                if (!ex.isCritical()) {
                    extSet.add(ex.getExtensionId().toString());
                }
            }
            extSet.addAll(exts.getUnparseableExtensions().keySet());
            return extSet;
        } catch (Exception e) {
            return null;
        }
    }

    public Extension getExtension(ObjectIdentifier oid) {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            CertificateExtensions extensions = (CertificateExtensions) x509CertInfo.get("extensions");
            if (extensions == null) {
                return null;
            }
            try {
                Extension ex = extensions.getExtension(oid.toString());
                if (ex != null) {
                    return ex;
                }
                for (Extension ex2 : extensions.getAllExtensions()) {
                    if (ex2.getExtensionId().equals((Object) oid)) {
                        return ex2;
                    }
                }
                return null;
            } catch (IOException e) {
                return null;
            }
        } catch (CertificateException e2) {
            return null;
        }
    }

    public Extension getUnparseableExtension(ObjectIdentifier oid) {
        X509CertInfo x509CertInfo = this.info;
        if (x509CertInfo == null) {
            return null;
        }
        try {
            CertificateExtensions extensions = (CertificateExtensions) x509CertInfo.get("extensions");
            if (extensions == null) {
                return null;
            }
            try {
                return extensions.getUnparseableExtensions().get(oid.toString());
            } catch (IOException e) {
                return null;
            }
        } catch (CertificateException e2) {
            return null;
        }
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String oid) {
        try {
            ObjectIdentifier findOID = new ObjectIdentifier(oid);
            String extAlias = OIDMap.getName(findOID);
            Extension certExt = null;
            CertificateExtensions exts = (CertificateExtensions) this.info.get("extensions");
            if (extAlias == null) {
                if (exts != null) {
                    Iterator<Extension> it = exts.getAllExtensions().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Extension ex = it.next();
                        if (ex.getExtensionId().equals((Object) findOID)) {
                            certExt = ex;
                            break;
                        }
                    }
                } else {
                    return null;
                }
            } else {
                try {
                    certExt = (Extension) get(extAlias);
                } catch (CertificateException e) {
                }
            }
            if (certExt == null) {
                if (exts != null) {
                    certExt = exts.getUnparseableExtensions().get(oid);
                }
                if (certExt == null) {
                    return null;
                }
            }
            byte[] extData = certExt.getExtensionValue();
            if (extData == null) {
                return null;
            }
            DerOutputStream out = new DerOutputStream();
            out.putOctetString(extData);
            return out.toByteArray();
        } catch (Exception e2) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        KeyUsageExtension certExt;
        try {
            String extAlias = OIDMap.getName(PKIXExtensions.KeyUsage_Id);
            if (extAlias == null || (certExt = (KeyUsageExtension) get(extAlias)) == null) {
                return null;
            }
            boolean[] ret = certExt.getBits();
            if (ret.length >= 9) {
                return ret;
            }
            boolean[] usageBits = new boolean[9];
            System.arraycopy((Object) ret, 0, (Object) usageBits, 0, ret.length);
            return usageBits;
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public synchronized List<String> getExtendedKeyUsage() throws CertificateParsingException {
        if (!this.readOnly || this.extKeyUsage == null) {
            ExtendedKeyUsageExtension ext = getExtendedKeyUsageExtension();
            if (ext == null) {
                return null;
            }
            this.extKeyUsage = Collections.unmodifiableList(ext.getExtendedKeyUsage());
            return this.extKeyUsage;
        }
        return this.extKeyUsage;
    }

    public static List<String> getExtendedKeyUsage(X509Certificate cert) throws CertificateParsingException {
        try {
            byte[] ext = cert.getExtensionValue(EXTENDED_KEY_USAGE_OID);
            if (ext == null) {
                return null;
            }
            return Collections.unmodifiableList(new ExtendedKeyUsageExtension(Boolean.FALSE, new DerValue(ext).getOctetString()).getExtendedKeyUsage());
        } catch (IOException ioe) {
            throw new CertificateParsingException(ioe);
        }
    }

    @Override // java.security.cert.X509Certificate
    public int getBasicConstraints() {
        BasicConstraintsExtension certExt;
        try {
            String extAlias = OIDMap.getName(PKIXExtensions.BasicConstraints_Id);
            if (extAlias == null || (certExt = (BasicConstraintsExtension) get(extAlias)) == null || !((Boolean) certExt.get(BasicConstraintsExtension.IS_CA)).booleanValue()) {
                return -1;
            }
            return ((Integer) certExt.get(BasicConstraintsExtension.PATH_LEN)).intValue();
        } catch (Exception e) {
            return -1;
        }
    }

    private static Collection<List<?>> makeAltNames(GeneralNames names) {
        if (names.isEmpty()) {
            return Collections.emptySet();
        }
        List<List<?>> newNames = new ArrayList<>();
        for (GeneralName gname : names.names()) {
            GeneralNameInterface name = gname.getName();
            List<Object> nameEntry = new ArrayList<>(2);
            nameEntry.add(Integer.valueOf(name.getType()));
            int type = name.getType();
            if (type == 1) {
                nameEntry.add(((RFC822Name) name).getName());
            } else if (type == 2) {
                nameEntry.add(((DNSName) name).getName());
            } else if (type == 4) {
                nameEntry.add(((X500Name) name).getRFC2253Name());
            } else if (type == 6) {
                nameEntry.add(((URIName) name).getName());
            } else if (type == 7) {
                try {
                    nameEntry.add(((IPAddressName) name).getName());
                } catch (IOException ioe) {
                    throw new RuntimeException("IPAddress cannot be parsed", ioe);
                }
            } else if (type != 8) {
                DerOutputStream derOut = new DerOutputStream();
                try {
                    name.encode(derOut);
                    nameEntry.add(derOut.toByteArray());
                } catch (IOException ioe2) {
                    throw new RuntimeException("name cannot be encoded", ioe2);
                }
            } else {
                nameEntry.add(((OIDName) name).getOID().toString());
            }
            newNames.add(Collections.unmodifiableList(nameEntry));
        }
        return Collections.unmodifiableCollection(newNames);
    }

    private static Collection<List<?>> cloneAltNames(Collection<List<?>> altNames) {
        boolean mustClone = false;
        for (List<?> nameEntry : altNames) {
            if (nameEntry.get(1) instanceof byte[]) {
                mustClone = true;
            }
        }
        if (!mustClone) {
            return altNames;
        }
        List<List<?>> namesCopy = new ArrayList<>();
        for (List<?> nameEntry2 : altNames) {
            Object nameObject = nameEntry2.get(1);
            if (nameObject instanceof byte[]) {
                List<Object> nameEntryCopy = new ArrayList<>(nameEntry2);
                nameEntryCopy.set(1, ((byte[]) nameObject).clone());
                namesCopy.add(Collections.unmodifiableList(nameEntryCopy));
            } else {
                namesCopy.add(nameEntry2);
            }
        }
        return Collections.unmodifiableCollection(namesCopy);
    }

    @Override // java.security.cert.X509Certificate
    public synchronized Collection<List<?>> getSubjectAlternativeNames() throws CertificateParsingException {
        if (!this.readOnly || this.subjectAlternativeNames == null) {
            SubjectAlternativeNameExtension subjectAltNameExt = getSubjectAlternativeNameExtension();
            if (subjectAltNameExt == null) {
                return null;
            }
            try {
                this.subjectAlternativeNames = makeAltNames(subjectAltNameExt.get(SubjectAlternativeNameExtension.SUBJECT_NAME));
                return this.subjectAlternativeNames;
            } catch (IOException e) {
                return Collections.emptySet();
            }
        } else {
            return cloneAltNames(this.subjectAlternativeNames);
        }
    }

    public static Collection<List<?>> getSubjectAlternativeNames(X509Certificate cert) throws CertificateParsingException {
        try {
            byte[] ext = cert.getExtensionValue(SUBJECT_ALT_NAME_OID);
            if (ext == null) {
                return null;
            }
            try {
                return makeAltNames(new SubjectAlternativeNameExtension(Boolean.FALSE, new DerValue(ext).getOctetString()).get(SubjectAlternativeNameExtension.SUBJECT_NAME));
            } catch (IOException e) {
                return Collections.emptySet();
            }
        } catch (IOException ioe) {
            throw new CertificateParsingException(ioe);
        }
    }

    @Override // java.security.cert.X509Certificate
    public synchronized Collection<List<?>> getIssuerAlternativeNames() throws CertificateParsingException {
        if (!this.readOnly || this.issuerAlternativeNames == null) {
            IssuerAlternativeNameExtension issuerAltNameExt = getIssuerAlternativeNameExtension();
            if (issuerAltNameExt == null) {
                return null;
            }
            try {
                this.issuerAlternativeNames = makeAltNames(issuerAltNameExt.get(IssuerAlternativeNameExtension.ISSUER_NAME));
                return this.issuerAlternativeNames;
            } catch (IOException e) {
                return Collections.emptySet();
            }
        } else {
            return cloneAltNames(this.issuerAlternativeNames);
        }
    }

    public static Collection<List<?>> getIssuerAlternativeNames(X509Certificate cert) throws CertificateParsingException {
        try {
            byte[] ext = cert.getExtensionValue(ISSUER_ALT_NAME_OID);
            if (ext == null) {
                return null;
            }
            try {
                return makeAltNames(new IssuerAlternativeNameExtension(Boolean.FALSE, new DerValue(ext).getOctetString()).get(IssuerAlternativeNameExtension.ISSUER_NAME));
            } catch (IOException e) {
                return Collections.emptySet();
            }
        } catch (IOException ioe) {
            throw new CertificateParsingException(ioe);
        }
    }

    public AuthorityInfoAccessExtension getAuthorityInfoAccessExtension() {
        return (AuthorityInfoAccessExtension) getExtension(PKIXExtensions.AuthInfoAccess_Id);
    }

    private void parse(DerValue val) throws CertificateException, IOException {
        parse(val, null);
    }

    private void parse(DerValue val, byte[] originalEncodedForm) throws CertificateException, IOException {
        if (this.readOnly) {
            throw new CertificateParsingException("cannot over-write existing certificate");
        } else if (val.data == null || val.tag != 48) {
            throw new CertificateParsingException("invalid DER-encoded certificate data");
        } else {
            this.signedCert = originalEncodedForm != null ? originalEncodedForm : val.toByteArray();
            DerValue[] seq = {val.data.getDerValue(), val.data.getDerValue(), val.data.getDerValue()};
            if (val.data.available() != 0) {
                throw new CertificateParsingException("signed overrun, bytes = " + val.data.available());
            } else if (seq[0].tag == 48) {
                this.algId = AlgorithmId.parse(seq[1]);
                this.signature = seq[2].getBitString();
                if (seq[1].data.available() != 0) {
                    throw new CertificateParsingException("algid field overrun");
                } else if (seq[2].data.available() == 0) {
                    this.info = new X509CertInfo(seq[0]);
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

    private static X500Principal getX500Principal(X509Certificate cert, boolean getIssuer) throws Exception {
        DerInputStream tbsIn = new DerInputStream(cert.getEncoded()).getSequence(3)[0].data;
        if (tbsIn.getDerValue().isContextSpecific((byte) 0)) {
            tbsIn.getDerValue();
        }
        tbsIn.getDerValue();
        DerValue tmp = tbsIn.getDerValue();
        if (!getIssuer) {
            tbsIn.getDerValue();
            tmp = tbsIn.getDerValue();
        }
        return new X500Principal(tmp.toByteArray());
    }

    public static X500Principal getSubjectX500Principal(X509Certificate cert) {
        try {
            return getX500Principal(cert, false);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse subject", e);
        }
    }

    public static X500Principal getIssuerX500Principal(X509Certificate cert) {
        try {
            return getX500Principal(cert, true);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse issuer", e);
        }
    }

    public static byte[] getEncodedInternal(Certificate cert) throws CertificateEncodingException {
        if (cert instanceof X509CertImpl) {
            return ((X509CertImpl) cert).getEncodedInternal();
        }
        return cert.getEncoded();
    }

    public static X509CertImpl toImpl(X509Certificate cert) throws CertificateException {
        if (cert instanceof X509CertImpl) {
            return (X509CertImpl) cert;
        }
        return X509Factory.intern(cert);
    }

    public static boolean isSelfIssued(X509Certificate cert) {
        return cert.getSubjectX500Principal().equals(cert.getIssuerX500Principal());
    }

    public static boolean isSelfSigned(X509Certificate cert, String sigProvider) {
        if (!isSelfIssued(cert)) {
            return false;
        }
        if (sigProvider == null) {
            try {
                cert.verify(cert.getPublicKey());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            cert.verify(cert.getPublicKey(), sigProvider);
            return true;
        }
    }

    public static String getFingerprint(String algorithm, X509Certificate cert) {
        try {
            byte[] digest = MessageDigest.getInstance(algorithm).digest(cert.getEncoded());
            StringBuffer buf = new StringBuffer();
            for (byte b : digest) {
                byte2hex(b, buf);
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException | CertificateEncodingException e) {
            return "";
        }
    }

    private static void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        buf.append(hexChars[(b & 240) >> 4]);
        buf.append(hexChars[b & 15]);
    }
}
