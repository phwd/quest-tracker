package java.security.cert;

import android.icu.text.PluralRules;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificatePoliciesExtension;
import sun.security.x509.CertificatePolicyId;
import sun.security.x509.CertificatePolicySet;
import sun.security.x509.DNSName;
import sun.security.x509.EDIPartyName;
import sun.security.x509.ExtendedKeyUsageExtension;
import sun.security.x509.GeneralName;
import sun.security.x509.GeneralNameInterface;
import sun.security.x509.GeneralNames;
import sun.security.x509.GeneralSubtree;
import sun.security.x509.GeneralSubtrees;
import sun.security.x509.IPAddressName;
import sun.security.x509.NameConstraintsExtension;
import sun.security.x509.OIDName;
import sun.security.x509.OtherName;
import sun.security.x509.PolicyInformation;
import sun.security.x509.PrivateKeyUsageExtension;
import sun.security.x509.RFC822Name;
import sun.security.x509.SubjectAlternativeNameExtension;
import sun.security.x509.URIName;
import sun.security.x509.X400Address;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509Key;

public class X509CertSelector implements CertSelector {
    private static final ObjectIdentifier ANY_EXTENDED_KEY_USAGE = ObjectIdentifier.newInternal(new int[]{2, 5, 29, 37, 0});
    private static final int CERT_POLICIES_ID = 3;
    private static final int EXTENDED_KEY_USAGE_ID = 4;
    private static final String[] EXTENSION_OIDS = new String[5];
    private static final Boolean FALSE = Boolean.FALSE;
    static final int NAME_ANY = 0;
    private static final int NAME_CONSTRAINTS_ID = 2;
    static final int NAME_DIRECTORY = 4;
    static final int NAME_DNS = 2;
    static final int NAME_EDI = 5;
    static final int NAME_IP = 7;
    static final int NAME_OID = 8;
    static final int NAME_RFC822 = 1;
    static final int NAME_URI = 6;
    static final int NAME_X400 = 3;
    private static final int NUM_OF_EXTENSIONS = 5;
    private static final int PRIVATE_KEY_USAGE_ID = 0;
    private static final int SUBJECT_ALT_NAME_ID = 1;
    private static final Debug debug = Debug.getInstance("certpath");
    private byte[] authorityKeyID;
    private int basicConstraints = -1;
    private Date certificateValid;
    private X500Principal issuer;
    private Set<ObjectIdentifier> keyPurposeOIDSet;
    private Set<String> keyPurposeSet;
    private boolean[] keyUsage;
    private boolean matchAllSubjectAltNames = true;
    private NameConstraintsExtension nc;
    private byte[] ncBytes;
    private Set<GeneralNameInterface> pathToGeneralNames;
    private Set<List<?>> pathToNames;
    private CertificatePolicySet policy;
    private Set<String> policySet;
    private Date privateKeyValid;
    private BigInteger serialNumber;
    private X500Principal subject;
    private Set<GeneralNameInterface> subjectAlternativeGeneralNames;
    private Set<List<?>> subjectAlternativeNames;
    private byte[] subjectKeyID;
    private PublicKey subjectPublicKey;
    private ObjectIdentifier subjectPublicKeyAlgID;
    private byte[] subjectPublicKeyBytes;
    private X509Certificate x509Cert;

    static {
        CertPathHelperImpl.initialize();
        String[] strArr = EXTENSION_OIDS;
        strArr[0] = "2.5.29.16";
        strArr[1] = "2.5.29.17";
        strArr[2] = "2.5.29.30";
        strArr[3] = "2.5.29.32";
        strArr[4] = "2.5.29.37";
    }

    public void setCertificate(X509Certificate cert) {
        this.x509Cert = cert;
    }

    public void setSerialNumber(BigInteger serial) {
        this.serialNumber = serial;
    }

    public void setIssuer(X500Principal issuer2) {
        this.issuer = issuer2;
    }

    public void setIssuer(String issuerDN) throws IOException {
        if (issuerDN == null) {
            this.issuer = null;
        } else {
            this.issuer = new X500Name(issuerDN).asX500Principal();
        }
    }

    public void setIssuer(byte[] issuerDN) throws IOException {
        X500Principal x500Principal;
        if (issuerDN == null) {
            x500Principal = null;
        } else {
            try {
                x500Principal = new X500Principal(issuerDN);
            } catch (IllegalArgumentException e) {
                throw new IOException("Invalid name", e);
            }
        }
        this.issuer = x500Principal;
    }

    public void setSubject(X500Principal subject2) {
        this.subject = subject2;
    }

    public void setSubject(String subjectDN) throws IOException {
        if (subjectDN == null) {
            this.subject = null;
        } else {
            this.subject = new X500Name(subjectDN).asX500Principal();
        }
    }

    public void setSubject(byte[] subjectDN) throws IOException {
        X500Principal x500Principal;
        if (subjectDN == null) {
            x500Principal = null;
        } else {
            try {
                x500Principal = new X500Principal(subjectDN);
            } catch (IllegalArgumentException e) {
                throw new IOException("Invalid name", e);
            }
        }
        this.subject = x500Principal;
    }

    public void setSubjectKeyIdentifier(byte[] subjectKeyID2) {
        if (subjectKeyID2 == null) {
            this.subjectKeyID = null;
        } else {
            this.subjectKeyID = (byte[]) subjectKeyID2.clone();
        }
    }

    public void setAuthorityKeyIdentifier(byte[] authorityKeyID2) {
        if (authorityKeyID2 == null) {
            this.authorityKeyID = null;
        } else {
            this.authorityKeyID = (byte[]) authorityKeyID2.clone();
        }
    }

    public void setCertificateValid(Date certValid) {
        if (certValid == null) {
            this.certificateValid = null;
        } else {
            this.certificateValid = (Date) certValid.clone();
        }
    }

    public void setPrivateKeyValid(Date privateKeyValid2) {
        if (privateKeyValid2 == null) {
            this.privateKeyValid = null;
        } else {
            this.privateKeyValid = (Date) privateKeyValid2.clone();
        }
    }

    public void setSubjectPublicKeyAlgID(String oid) throws IOException {
        if (oid == null) {
            this.subjectPublicKeyAlgID = null;
        } else {
            this.subjectPublicKeyAlgID = new ObjectIdentifier(oid);
        }
    }

    public void setSubjectPublicKey(PublicKey key) {
        if (key == null) {
            this.subjectPublicKey = null;
            this.subjectPublicKeyBytes = null;
            return;
        }
        this.subjectPublicKey = key;
        this.subjectPublicKeyBytes = key.getEncoded();
    }

    public void setSubjectPublicKey(byte[] key) throws IOException {
        if (key == null) {
            this.subjectPublicKey = null;
            this.subjectPublicKeyBytes = null;
            return;
        }
        this.subjectPublicKeyBytes = (byte[]) key.clone();
        this.subjectPublicKey = X509Key.parse(new DerValue(this.subjectPublicKeyBytes));
    }

    public void setKeyUsage(boolean[] keyUsage2) {
        if (keyUsage2 == null) {
            this.keyUsage = null;
        } else {
            this.keyUsage = (boolean[]) keyUsage2.clone();
        }
    }

    public void setExtendedKeyUsage(Set<String> keyPurposeSet2) throws IOException {
        if (keyPurposeSet2 == null || keyPurposeSet2.isEmpty()) {
            this.keyPurposeSet = null;
            this.keyPurposeOIDSet = null;
            return;
        }
        this.keyPurposeSet = Collections.unmodifiableSet(new HashSet(keyPurposeSet2));
        this.keyPurposeOIDSet = new HashSet();
        for (String s : this.keyPurposeSet) {
            this.keyPurposeOIDSet.add(new ObjectIdentifier(s));
        }
    }

    public void setMatchAllSubjectAltNames(boolean matchAllNames) {
        this.matchAllSubjectAltNames = matchAllNames;
    }

    public void setSubjectAlternativeNames(Collection<List<?>> names) throws IOException {
        if (names == null) {
            this.subjectAlternativeNames = null;
            this.subjectAlternativeGeneralNames = null;
        } else if (names.isEmpty()) {
            this.subjectAlternativeNames = null;
            this.subjectAlternativeGeneralNames = null;
        } else {
            Set<List<?>> tempNames = cloneAndCheckNames(names);
            this.subjectAlternativeGeneralNames = parseNames(tempNames);
            this.subjectAlternativeNames = tempNames;
        }
    }

    public void addSubjectAlternativeName(int type, String name) throws IOException {
        addSubjectAlternativeNameInternal(type, name);
    }

    public void addSubjectAlternativeName(int type, byte[] name) throws IOException {
        addSubjectAlternativeNameInternal(type, name.clone());
    }

    private void addSubjectAlternativeNameInternal(int type, Object name) throws IOException {
        GeneralNameInterface tempName = makeGeneralNameInterface(type, name);
        if (this.subjectAlternativeNames == null) {
            this.subjectAlternativeNames = new HashSet();
        }
        if (this.subjectAlternativeGeneralNames == null) {
            this.subjectAlternativeGeneralNames = new HashSet();
        }
        List<Object> list = new ArrayList<>(2);
        list.add(Integer.valueOf(type));
        list.add(name);
        this.subjectAlternativeNames.add(list);
        this.subjectAlternativeGeneralNames.add(tempName);
    }

    private static Set<GeneralNameInterface> parseNames(Collection<List<?>> names) throws IOException {
        Set<GeneralNameInterface> genNames = new HashSet<>();
        for (List<?> nameList : names) {
            if (nameList.size() == 2) {
                Object o = nameList.get(0);
                if (o instanceof Integer) {
                    genNames.add(makeGeneralNameInterface(((Integer) o).intValue(), nameList.get(1)));
                } else {
                    throw new IOException("expected an Integer");
                }
            } else {
                throw new IOException("name list size not 2");
            }
        }
        return genNames;
    }

    static boolean equalNames(Collection<?> object1, Collection<?> object2) {
        if (object1 == null || object2 == null) {
            return object1 == object2;
        }
        return object1.equals(object2);
    }

    static GeneralNameInterface makeGeneralNameInterface(int type, Object name) throws IOException {
        GeneralNameInterface result;
        GeneralNameInterface result2;
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("X509CertSelector.makeGeneralNameInterface(" + type + ")...");
        }
        if (name instanceof String) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("X509CertSelector.makeGeneralNameInterface() name is String: " + name);
            }
            if (type == 1) {
                result2 = new RFC822Name((String) name);
            } else if (type == 2) {
                result2 = new DNSName((String) name);
            } else if (type == 4) {
                result2 = new X500Name((String) name);
            } else if (type == 6) {
                result2 = new URIName((String) name);
            } else if (type == 7) {
                result2 = new IPAddressName((String) name);
            } else if (type == 8) {
                result2 = new OIDName((String) name);
            } else {
                throw new IOException("unable to parse String names of type " + type);
            }
            Debug debug4 = debug;
            if (debug4 == null) {
                return result2;
            }
            debug4.println("X509CertSelector.makeGeneralNameInterface() result: " + result2.toString());
            return result2;
        } else if (name instanceof byte[]) {
            DerValue val = new DerValue((byte[]) name);
            Debug debug5 = debug;
            if (debug5 != null) {
                debug5.println("X509CertSelector.makeGeneralNameInterface() is byte[]");
            }
            switch (type) {
                case 0:
                    result = new OtherName(val);
                    break;
                case 1:
                    result = new RFC822Name(val);
                    break;
                case 2:
                    result = new DNSName(val);
                    break;
                case 3:
                    result = new X400Address(val);
                    break;
                case 4:
                    result = new X500Name(val);
                    break;
                case 5:
                    result = new EDIPartyName(val);
                    break;
                case 6:
                    result = new URIName(val);
                    break;
                case 7:
                    result = new IPAddressName(val);
                    break;
                case 8:
                    result = new OIDName(val);
                    break;
                default:
                    throw new IOException("unable to parse byte array names of type " + type);
            }
            Debug debug6 = debug;
            if (debug6 != null) {
                debug6.println("X509CertSelector.makeGeneralNameInterface() result: " + result.toString());
            }
            return result;
        } else {
            Debug debug7 = debug;
            if (debug7 != null) {
                debug7.println("X509CertSelector.makeGeneralName() input name not String or byte array");
            }
            throw new IOException("name not String or byte array");
        }
    }

    public void setNameConstraints(byte[] bytes) throws IOException {
        if (bytes == null) {
            this.ncBytes = null;
            this.nc = null;
            return;
        }
        this.ncBytes = (byte[]) bytes.clone();
        this.nc = new NameConstraintsExtension(FALSE, bytes);
    }

    public void setBasicConstraints(int minMaxPathLen) {
        if (minMaxPathLen >= -2) {
            this.basicConstraints = minMaxPathLen;
            return;
        }
        throw new IllegalArgumentException("basic constraints less than -2");
    }

    public void setPolicy(Set<String> certPolicySet) throws IOException {
        if (certPolicySet == null) {
            this.policySet = null;
            this.policy = null;
            return;
        }
        Set<String> tempSet = Collections.unmodifiableSet(new HashSet(certPolicySet));
        Vector<CertificatePolicyId> polIdVector = new Vector<>();
        for (Object o : tempSet) {
            if (o instanceof String) {
                polIdVector.add(new CertificatePolicyId(new ObjectIdentifier((String) o)));
            } else {
                throw new IOException("non String in certPolicySet");
            }
        }
        this.policySet = tempSet;
        this.policy = new CertificatePolicySet(polIdVector);
    }

    public void setPathToNames(Collection<List<?>> names) throws IOException {
        if (names == null || names.isEmpty()) {
            this.pathToNames = null;
            this.pathToGeneralNames = null;
            return;
        }
        Set<List<?>> tempNames = cloneAndCheckNames(names);
        this.pathToGeneralNames = parseNames(tempNames);
        this.pathToNames = tempNames;
    }

    /* access modifiers changed from: package-private */
    public void setPathToNamesInternal(Set<GeneralNameInterface> names) {
        this.pathToNames = Collections.emptySet();
        this.pathToGeneralNames = names;
    }

    public void addPathToName(int type, String name) throws IOException {
        addPathToNameInternal(type, name);
    }

    public void addPathToName(int type, byte[] name) throws IOException {
        addPathToNameInternal(type, name.clone());
    }

    private void addPathToNameInternal(int type, Object name) throws IOException {
        GeneralNameInterface tempName = makeGeneralNameInterface(type, name);
        if (this.pathToGeneralNames == null) {
            this.pathToNames = new HashSet();
            this.pathToGeneralNames = new HashSet();
        }
        List<Object> list = new ArrayList<>(2);
        list.add(Integer.valueOf(type));
        list.add(name);
        this.pathToNames.add(list);
        this.pathToGeneralNames.add(tempName);
    }

    public X509Certificate getCertificate() {
        return this.x509Cert;
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    public X500Principal getIssuer() {
        return this.issuer;
    }

    public String getIssuerAsString() {
        X500Principal x500Principal = this.issuer;
        if (x500Principal == null) {
            return null;
        }
        return x500Principal.getName();
    }

    public byte[] getIssuerAsBytes() throws IOException {
        X500Principal x500Principal = this.issuer;
        if (x500Principal == null) {
            return null;
        }
        return x500Principal.getEncoded();
    }

    public X500Principal getSubject() {
        return this.subject;
    }

    public String getSubjectAsString() {
        X500Principal x500Principal = this.subject;
        if (x500Principal == null) {
            return null;
        }
        return x500Principal.getName();
    }

    public byte[] getSubjectAsBytes() throws IOException {
        X500Principal x500Principal = this.subject;
        if (x500Principal == null) {
            return null;
        }
        return x500Principal.getEncoded();
    }

    public byte[] getSubjectKeyIdentifier() {
        byte[] bArr = this.subjectKeyID;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public byte[] getAuthorityKeyIdentifier() {
        byte[] bArr = this.authorityKeyID;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public Date getCertificateValid() {
        Date date = this.certificateValid;
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public Date getPrivateKeyValid() {
        Date date = this.privateKeyValid;
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public String getSubjectPublicKeyAlgID() {
        ObjectIdentifier objectIdentifier = this.subjectPublicKeyAlgID;
        if (objectIdentifier == null) {
            return null;
        }
        return objectIdentifier.toString();
    }

    public PublicKey getSubjectPublicKey() {
        return this.subjectPublicKey;
    }

    public boolean[] getKeyUsage() {
        boolean[] zArr = this.keyUsage;
        if (zArr == null) {
            return null;
        }
        return (boolean[]) zArr.clone();
    }

    public Set<String> getExtendedKeyUsage() {
        return this.keyPurposeSet;
    }

    public boolean getMatchAllSubjectAltNames() {
        return this.matchAllSubjectAltNames;
    }

    public Collection<List<?>> getSubjectAlternativeNames() {
        Set<List<?>> set = this.subjectAlternativeNames;
        if (set == null) {
            return null;
        }
        return cloneNames(set);
    }

    private static Set<List<?>> cloneNames(Collection<List<?>> names) {
        try {
            return cloneAndCheckNames(names);
        } catch (IOException e) {
            throw new RuntimeException("cloneNames encountered IOException: " + e.getMessage());
        }
    }

    private static Set<List<?>> cloneAndCheckNames(Collection<List<?>> names) throws IOException {
        Set<List<?>> namesCopy = new HashSet<>();
        for (List<?> o : names) {
            namesCopy.add(new ArrayList<>(o));
        }
        for (List<?> list : namesCopy) {
            if (list.size() == 2) {
                Object o2 = list.get(0);
                if (o2 instanceof Integer) {
                    int nameType = ((Integer) o2).intValue();
                    if (nameType < 0 || nameType > 8) {
                        throw new IOException("name type not 0-8");
                    }
                    Object nameObject = list.get(1);
                    if (!(nameObject instanceof byte[]) && !(nameObject instanceof String)) {
                        Debug debug2 = debug;
                        if (debug2 != null) {
                            debug2.println("X509CertSelector.cloneAndCheckNames() name not byte array");
                        }
                        throw new IOException("name not byte array or String");
                    } else if (nameObject instanceof byte[]) {
                        list.set(1, ((byte[]) nameObject).clone());
                    }
                } else {
                    throw new IOException("expected an Integer");
                }
            } else {
                throw new IOException("name list size not 2");
            }
        }
        return namesCopy;
    }

    public byte[] getNameConstraints() {
        byte[] bArr = this.ncBytes;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public int getBasicConstraints() {
        return this.basicConstraints;
    }

    public Set<String> getPolicy() {
        return this.policySet;
    }

    public Collection<List<?>> getPathToNames() {
        Set<List<?>> set = this.pathToNames;
        if (set == null) {
            return null;
        }
        return cloneNames(set);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("X509CertSelector: [\n");
        if (this.x509Cert != null) {
            sb.append("  Certificate: " + this.x509Cert.toString() + "\n");
        }
        if (this.serialNumber != null) {
            sb.append("  Serial Number: " + this.serialNumber.toString() + "\n");
        }
        if (this.issuer != null) {
            sb.append("  Issuer: " + getIssuerAsString() + "\n");
        }
        if (this.subject != null) {
            sb.append("  Subject: " + getSubjectAsString() + "\n");
        }
        sb.append("  matchAllSubjectAltNames flag: " + String.valueOf(this.matchAllSubjectAltNames) + "\n");
        if (this.subjectAlternativeNames != null) {
            sb.append("  SubjectAlternativeNames:\n");
            for (List<?> list : this.subjectAlternativeNames) {
                sb.append("    type " + list.get(0) + ", name " + list.get(1) + "\n");
            }
        }
        if (this.subjectKeyID != null) {
            HexDumpEncoder enc = new HexDumpEncoder();
            sb.append("  Subject Key Identifier: " + enc.encodeBuffer(this.subjectKeyID) + "\n");
        }
        if (this.authorityKeyID != null) {
            HexDumpEncoder enc2 = new HexDumpEncoder();
            sb.append("  Authority Key Identifier: " + enc2.encodeBuffer(this.authorityKeyID) + "\n");
        }
        if (this.certificateValid != null) {
            sb.append("  Certificate Valid: " + this.certificateValid.toString() + "\n");
        }
        if (this.privateKeyValid != null) {
            sb.append("  Private Key Valid: " + this.privateKeyValid.toString() + "\n");
        }
        if (this.subjectPublicKeyAlgID != null) {
            sb.append("  Subject Public Key AlgID: " + this.subjectPublicKeyAlgID.toString() + "\n");
        }
        if (this.subjectPublicKey != null) {
            sb.append("  Subject Public Key: " + this.subjectPublicKey.toString() + "\n");
        }
        if (this.keyUsage != null) {
            sb.append("  Key Usage: " + keyUsageToString(this.keyUsage) + "\n");
        }
        if (this.keyPurposeSet != null) {
            sb.append("  Extended Key Usage: " + this.keyPurposeSet.toString() + "\n");
        }
        if (this.policy != null) {
            sb.append("  Policy: " + this.policy.toString() + "\n");
        }
        if (this.pathToGeneralNames != null) {
            sb.append("  Path to names:\n");
            Iterator<GeneralNameInterface> i = this.pathToGeneralNames.iterator();
            while (i.hasNext()) {
                sb.append("    " + ((Object) i.next()) + "\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String keyUsageToString(boolean[] k) {
        String s = "KeyUsage [\n";
        try {
            if (k[0]) {
                s = s + "  DigitalSignature\n";
            }
            if (k[1]) {
                s = s + "  Non_repudiation\n";
            }
            if (k[2]) {
                s = s + "  Key_Encipherment\n";
            }
            if (k[3]) {
                s = s + "  Data_Encipherment\n";
            }
            if (k[4]) {
                s = s + "  Key_Agreement\n";
            }
            if (k[5]) {
                s = s + "  Key_CertSign\n";
            }
            if (k[6]) {
                s = s + "  Crl_Sign\n";
            }
            if (k[7]) {
                s = s + "  Encipher_Only\n";
            }
            if (k[8]) {
                s = s + "  Decipher_Only\n";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return s + "]\n";
    }

    private static Extension getExtensionObject(X509Certificate cert, int extId) throws IOException {
        if (cert instanceof X509CertImpl) {
            X509CertImpl impl = (X509CertImpl) cert;
            if (extId == 0) {
                return impl.getPrivateKeyUsageExtension();
            }
            if (extId == 1) {
                return impl.getSubjectAlternativeNameExtension();
            }
            if (extId == 2) {
                return impl.getNameConstraintsExtension();
            }
            if (extId == 3) {
                return impl.getCertificatePoliciesExtension();
            }
            if (extId != 4) {
                return null;
            }
            return impl.getExtendedKeyUsageExtension();
        }
        byte[] rawExtVal = cert.getExtensionValue(EXTENSION_OIDS[extId]);
        if (rawExtVal == null) {
            return null;
        }
        byte[] encoded = new DerInputStream(rawExtVal).getOctetString();
        if (extId == 0) {
            try {
                return new PrivateKeyUsageExtension(FALSE, encoded);
            } catch (CertificateException ex) {
                throw new IOException(ex.getMessage());
            }
        } else if (extId == 1) {
            return new SubjectAlternativeNameExtension(FALSE, encoded);
        } else {
            if (extId == 2) {
                return new NameConstraintsExtension(FALSE, encoded);
            }
            if (extId == 3) {
                return new CertificatePoliciesExtension(FALSE, encoded);
            }
            if (extId != 4) {
                return null;
            }
            return new ExtendedKeyUsageExtension(FALSE, encoded);
        }
    }

    @Override // java.security.cert.CertSelector
    public boolean match(Certificate cert) {
        Debug debug2;
        boolean result = false;
        if (!(cert instanceof X509Certificate)) {
            return false;
        }
        X509Certificate xcert = (X509Certificate) cert;
        Debug debug3 = debug;
        if (debug3 != null) {
            debug3.println("X509CertSelector.match(SN: " + xcert.getSerialNumber().toString(16) + "\n  Issuer: " + ((Object) xcert.getIssuerDN()) + "\n  Subject: " + ((Object) xcert.getSubjectDN()) + ")");
        }
        X509Certificate x509Certificate = this.x509Cert;
        if (x509Certificate == null || x509Certificate.equals(xcert)) {
            BigInteger bigInteger = this.serialNumber;
            if (bigInteger == null || bigInteger.equals(xcert.getSerialNumber())) {
                X500Principal x500Principal = this.issuer;
                if (x500Principal == null || x500Principal.equals(xcert.getIssuerX500Principal())) {
                    X500Principal x500Principal2 = this.subject;
                    if (x500Principal2 == null || x500Principal2.equals(xcert.getSubjectX500Principal())) {
                        Date date = this.certificateValid;
                        if (date != null) {
                            try {
                                xcert.checkValidity(date);
                            } catch (CertificateException e) {
                                Debug debug4 = debug;
                                if (debug4 != null) {
                                    debug4.println("X509CertSelector.match: certificate not within validity period");
                                }
                                return false;
                            }
                        }
                        if (this.subjectPublicKeyBytes != null) {
                            if (!Arrays.equals(this.subjectPublicKeyBytes, xcert.getPublicKey().getEncoded())) {
                                Debug debug5 = debug;
                                if (debug5 != null) {
                                    debug5.println("X509CertSelector.match: subject public keys don't match");
                                }
                                return false;
                            }
                        }
                        if (matchBasicConstraints(xcert) && matchKeyUsage(xcert) && matchExtendedKeyUsage(xcert) && matchSubjectKeyID(xcert) && matchAuthorityKeyID(xcert) && matchPrivateKeyValid(xcert) && matchSubjectPublicKeyAlgID(xcert) && matchPolicy(xcert) && matchSubjectAlternativeNames(xcert) && matchPathToNames(xcert) && matchNameConstraints(xcert)) {
                            result = true;
                        }
                        if (result && (debug2 = debug) != null) {
                            debug2.println("X509CertSelector.match returning: true");
                        }
                        return result;
                    }
                    Debug debug6 = debug;
                    if (debug6 != null) {
                        debug6.println("X509CertSelector.match: subject DNs don't match");
                    }
                    return false;
                }
                Debug debug7 = debug;
                if (debug7 != null) {
                    debug7.println("X509CertSelector.match: issuer DNs don't match");
                }
                return false;
            }
            Debug debug8 = debug;
            if (debug8 != null) {
                debug8.println("X509CertSelector.match: serial numbers don't match");
            }
            return false;
        }
        Debug debug9 = debug;
        if (debug9 != null) {
            debug9.println("X509CertSelector.match: certs don't match");
        }
        return false;
    }

    private boolean matchSubjectKeyID(X509Certificate xcert) {
        if (this.subjectKeyID == null) {
            return true;
        }
        try {
            byte[] extVal = xcert.getExtensionValue("2.5.29.14");
            if (extVal == null) {
                if (debug != null) {
                    debug.println("X509CertSelector.match: no subject key ID extension");
                }
                return false;
            }
            byte[] certSubjectKeyID = new DerInputStream(extVal).getOctetString();
            if (certSubjectKeyID != null) {
                if (Arrays.equals(this.subjectKeyID, certSubjectKeyID)) {
                    return true;
                }
            }
            if (debug != null) {
                debug.println("X509CertSelector.match: subject key IDs don't match");
            }
            return false;
        } catch (IOException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("X509CertSelector.match: exception in subject key ID check");
            }
            return false;
        }
    }

    private boolean matchAuthorityKeyID(X509Certificate xcert) {
        if (this.authorityKeyID == null) {
            return true;
        }
        try {
            byte[] extVal = xcert.getExtensionValue("2.5.29.35");
            if (extVal == null) {
                if (debug != null) {
                    debug.println("X509CertSelector.match: no authority key ID extension");
                }
                return false;
            }
            byte[] certAuthKeyID = new DerInputStream(extVal).getOctetString();
            if (certAuthKeyID != null) {
                if (Arrays.equals(this.authorityKeyID, certAuthKeyID)) {
                    return true;
                }
            }
            if (debug != null) {
                debug.println("X509CertSelector.match: authority key IDs don't match");
            }
            return false;
        } catch (IOException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("X509CertSelector.match: exception in authority key ID check");
            }
            return false;
        }
    }

    private boolean matchPrivateKeyValid(X509Certificate xcert) {
        if (this.privateKeyValid == null) {
            return true;
        }
        PrivateKeyUsageExtension ext = null;
        try {
            PrivateKeyUsageExtension ext2 = (PrivateKeyUsageExtension) getExtensionObject(xcert, 0);
            if (ext2 != null) {
                ext2.valid(this.privateKeyValid);
            }
            return true;
        } catch (CertificateExpiredException e1) {
            if (debug != null) {
                String time = "n/a";
                try {
                    time = ext.get(PrivateKeyUsageExtension.NOT_AFTER).toString();
                } catch (CertificateException e) {
                }
                Debug debug2 = debug;
                debug2.println("X509CertSelector.match: private key usage not within validity date; ext.NOT_After: " + time + "; X509CertSelector: " + toString());
                e1.printStackTrace();
            }
            return false;
        } catch (CertificateNotYetValidException e2) {
            if (debug != null) {
                String time2 = "n/a";
                try {
                    time2 = ext.get(PrivateKeyUsageExtension.NOT_BEFORE).toString();
                } catch (CertificateException e3) {
                }
                Debug debug3 = debug;
                debug3.println("X509CertSelector.match: private key usage not within validity date; ext.NOT_BEFORE: " + time2 + "; X509CertSelector: " + toString());
                e2.printStackTrace();
            }
            return false;
        } catch (IOException e4) {
            Debug debug4 = debug;
            if (debug4 != null) {
                debug4.println("X509CertSelector.match: IOException in private key usage check; X509CertSelector: " + toString());
                e4.printStackTrace();
            }
            return false;
        }
    }

    private boolean matchSubjectPublicKeyAlgID(X509Certificate xcert) {
        if (this.subjectPublicKeyAlgID == null) {
            return true;
        }
        try {
            DerValue val = new DerValue(xcert.getPublicKey().getEncoded());
            if (val.tag == 48) {
                AlgorithmId algID = AlgorithmId.parse(val.data.getDerValue());
                if (debug != null) {
                    Debug debug2 = debug;
                    debug2.println("X509CertSelector.match: subjectPublicKeyAlgID = " + ((Object) this.subjectPublicKeyAlgID) + ", xcert subjectPublicKeyAlgID = " + ((Object) algID.getOID()));
                }
                if (this.subjectPublicKeyAlgID.equals((Object) algID.getOID())) {
                    return true;
                }
                if (debug != null) {
                    debug.println("X509CertSelector.match: subject public key alg IDs don't match");
                }
                return false;
            }
            throw new IOException("invalid key format");
        } catch (IOException e) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("X509CertSelector.match: IOException in subject public key algorithm OID check");
            }
            return false;
        }
    }

    private boolean matchKeyUsage(X509Certificate xcert) {
        boolean[] certKeyUsage;
        if (this.keyUsage != null && (certKeyUsage = xcert.getKeyUsage()) != null) {
            int keyBit = 0;
            while (true) {
                boolean[] zArr = this.keyUsage;
                if (keyBit >= zArr.length) {
                    break;
                } else if (!zArr[keyBit] || (keyBit < certKeyUsage.length && certKeyUsage[keyBit])) {
                    keyBit++;
                }
            }
            Debug debug2 = debug;
            if (debug2 == null) {
                return false;
            }
            debug2.println("X509CertSelector.match: key usage bits don't match");
            return false;
        }
        return true;
    }

    private boolean matchExtendedKeyUsage(X509Certificate xcert) {
        Set<String> set = this.keyPurposeSet;
        if (set == null || set.isEmpty()) {
            return true;
        }
        try {
            ExtendedKeyUsageExtension ext = (ExtendedKeyUsageExtension) getExtensionObject(xcert, 4);
            if (ext != null) {
                Vector<ObjectIdentifier> certKeyPurposeVector = ext.get(ExtendedKeyUsageExtension.USAGES);
                if (!certKeyPurposeVector.contains(ANY_EXTENDED_KEY_USAGE) && !certKeyPurposeVector.containsAll(this.keyPurposeOIDSet)) {
                    if (debug != null) {
                        debug.println("X509CertSelector.match: cert failed extendedKeyUsage criterion");
                    }
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("X509CertSelector.match: IOException in extended key usage check");
            }
            return false;
        }
    }

    private boolean matchSubjectAlternativeNames(X509Certificate xcert) {
        Set<List<?>> set = this.subjectAlternativeNames;
        if (set == null || set.isEmpty()) {
            return true;
        }
        try {
            SubjectAlternativeNameExtension sanExt = (SubjectAlternativeNameExtension) getExtensionObject(xcert, 1);
            if (sanExt == null) {
                if (debug != null) {
                    debug.println("X509CertSelector.match: no subject alternative name extension");
                }
                return false;
            }
            GeneralNames certNames = sanExt.get(SubjectAlternativeNameExtension.SUBJECT_NAME);
            Iterator<GeneralNameInterface> i = this.subjectAlternativeGeneralNames.iterator();
            while (i.hasNext()) {
                GeneralNameInterface matchName = i.next();
                boolean found = false;
                Iterator<GeneralName> t = certNames.iterator();
                while (t.hasNext() && !found) {
                    found = t.next().getName().equals(matchName);
                }
                if (found || (!this.matchAllSubjectAltNames && i.hasNext())) {
                    if (found && !this.matchAllSubjectAltNames) {
                        break;
                    }
                } else {
                    if (debug != null) {
                        Debug debug2 = debug;
                        debug2.println("X509CertSelector.match: subject alternative name " + ((Object) matchName) + " not found");
                    }
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("X509CertSelector.match: IOException in subject alternative name check");
            }
            return false;
        }
    }

    private boolean matchNameConstraints(X509Certificate xcert) {
        NameConstraintsExtension nameConstraintsExtension = this.nc;
        if (nameConstraintsExtension == null) {
            return true;
        }
        try {
            if (nameConstraintsExtension.verify(xcert)) {
                return true;
            }
            if (debug != null) {
                debug.println("X509CertSelector.match: name constraints not satisfied");
            }
            return false;
        } catch (IOException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("X509CertSelector.match: IOException in name constraints check");
            }
            return false;
        }
    }

    private boolean matchPolicy(X509Certificate xcert) {
        if (this.policy == null) {
            return true;
        }
        try {
            CertificatePoliciesExtension ext = (CertificatePoliciesExtension) getExtensionObject(xcert, 3);
            if (ext == null) {
                if (debug != null) {
                    debug.println("X509CertSelector.match: no certificate policy extension");
                }
                return false;
            }
            List<PolicyInformation> policies = ext.get(CertificatePoliciesExtension.POLICIES);
            List<CertificatePolicyId> policyIDs = new ArrayList<>(policies.size());
            for (PolicyInformation info : policies) {
                policyIDs.add(info.getPolicyIdentifier());
            }
            if (this.policy != null) {
                boolean foundOne = false;
                if (!this.policy.getCertPolicyIds().isEmpty()) {
                    Iterator<CertificatePolicyId> it = this.policy.getCertPolicyIds().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (policyIDs.contains(it.next())) {
                                foundOne = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (!foundOne) {
                        if (debug != null) {
                            debug.println("X509CertSelector.match: cert failed policyAny criterion");
                        }
                        return false;
                    }
                } else if (policyIDs.isEmpty()) {
                    if (debug != null) {
                        debug.println("X509CertSelector.match: cert failed policyAny criterion");
                    }
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("X509CertSelector.match: IOException in certificate policy ID check");
            }
            return false;
        }
    }

    private boolean matchPathToNames(X509Certificate xcert) {
        if (this.pathToGeneralNames == null) {
            return true;
        }
        try {
            NameConstraintsExtension ext = (NameConstraintsExtension) getExtensionObject(xcert, 2);
            if (ext == null) {
                return true;
            }
            if (debug != null && Debug.isOn("certpath")) {
                debug.println("X509CertSelector.match pathToNames:\n");
                Iterator<GeneralNameInterface> i = this.pathToGeneralNames.iterator();
                while (i.hasNext()) {
                    Debug debug2 = debug;
                    debug2.println("    " + ((Object) i.next()) + "\n");
                }
            }
            GeneralSubtrees permitted = ext.get(NameConstraintsExtension.PERMITTED_SUBTREES);
            GeneralSubtrees excluded = ext.get(NameConstraintsExtension.EXCLUDED_SUBTREES);
            if (excluded != null && !matchExcluded(excluded)) {
                return false;
            }
            if (permitted == null || matchPermitted(permitted)) {
                return true;
            }
            return false;
        } catch (IOException e) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("X509CertSelector.match: IOException in name constraints check");
            }
            return false;
        }
    }

    private boolean matchExcluded(GeneralSubtrees excluded) {
        GeneralNameInterface pathToName;
        int constrains;
        Iterator<GeneralSubtree> t = excluded.iterator();
        loop0:
        while (t.hasNext()) {
            GeneralNameInterface excludedName = t.next().getName().getName();
            Iterator<GeneralNameInterface> i = this.pathToGeneralNames.iterator();
            while (true) {
                if (i.hasNext()) {
                    pathToName = i.next();
                    if (!(excludedName.getType() == pathToName.getType() && ((constrains = pathToName.constrains(excludedName)) == 0 || constrains == 2))) {
                    }
                }
            }
            Debug debug2 = debug;
            if (debug2 == null) {
                return false;
            }
            debug2.println("X509CertSelector.match: name constraints inhibit path to specified name");
            Debug debug3 = debug;
            debug3.println("X509CertSelector.match: excluded name: " + ((Object) pathToName));
            return false;
        }
        return true;
    }

    private boolean matchPermitted(GeneralSubtrees permitted) {
        for (GeneralNameInterface pathToName : this.pathToGeneralNames) {
            Iterator<GeneralSubtree> t = permitted.iterator();
            boolean permittedNameFound = false;
            boolean nameTypeFound = false;
            String names = "";
            while (t.hasNext() && !permittedNameFound) {
                GeneralNameInterface permittedName = t.next().getName().getName();
                if (permittedName.getType() == pathToName.getType()) {
                    nameTypeFound = true;
                    names = names + "  " + ((Object) permittedName);
                    int constrains = pathToName.constrains(permittedName);
                    if (constrains == 0 || constrains == 2) {
                        permittedNameFound = true;
                    }
                }
            }
            if (!permittedNameFound && nameTypeFound) {
                Debug debug2 = debug;
                if (debug2 == null) {
                    return false;
                }
                debug2.println("X509CertSelector.match: name constraints inhibit path to specified name; permitted names of type " + pathToName.getType() + PluralRules.KEYWORD_RULE_SEPARATOR + names);
                return false;
            }
        }
        return true;
    }

    private boolean matchBasicConstraints(X509Certificate xcert) {
        if (this.basicConstraints == -1) {
            return true;
        }
        int maxPathLen = xcert.getBasicConstraints();
        int i = this.basicConstraints;
        if (i == -2) {
            if (maxPathLen != -1) {
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("X509CertSelector.match: not an EE cert");
                }
                return false;
            }
        } else if (maxPathLen < i) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("X509CertSelector.match: cert's maxPathLen is less than the min maxPathLen set by basicConstraints. (" + maxPathLen + " < " + this.basicConstraints + ")");
            }
            return false;
        }
        return true;
    }

    private static <T> Set<T> cloneSet(Set<T> set) {
        if (set instanceof HashSet) {
            return (Set) ((HashSet) set).clone();
        }
        return new HashSet(set);
    }

    @Override // java.security.cert.CertSelector
    public Object clone() {
        try {
            X509CertSelector copy = (X509CertSelector) super.clone();
            if (this.subjectAlternativeNames != null) {
                copy.subjectAlternativeNames = cloneSet(this.subjectAlternativeNames);
                copy.subjectAlternativeGeneralNames = cloneSet(this.subjectAlternativeGeneralNames);
            }
            if (this.pathToGeneralNames != null) {
                copy.pathToNames = cloneSet(this.pathToNames);
                copy.pathToGeneralNames = cloneSet(this.pathToGeneralNames);
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString(), e);
        }
    }
}
