package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import sun.security.pkcs.PKCS9Attribute;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class NameConstraintsExtension extends Extension implements CertAttrSet<String>, Cloneable {
    public static final String EXCLUDED_SUBTREES = "excluded_subtrees";
    public static final String IDENT = "x509.info.extensions.NameConstraints";
    public static final String NAME = "NameConstraints";
    public static final String PERMITTED_SUBTREES = "permitted_subtrees";
    private static final byte TAG_EXCLUDED = 1;
    private static final byte TAG_PERMITTED = 0;
    private GeneralSubtrees excluded = null;
    private boolean hasMax;
    private boolean hasMin;
    private boolean minMaxValid = false;
    private GeneralSubtrees permitted = null;

    private void calcMinMax() throws IOException {
        this.hasMin = false;
        this.hasMax = false;
        if (this.excluded != null) {
            for (int i = 0; i < this.excluded.size(); i++) {
                GeneralSubtree subtree = this.excluded.get(i);
                if (subtree.getMinimum() != 0) {
                    this.hasMin = true;
                }
                if (subtree.getMaximum() != -1) {
                    this.hasMax = true;
                }
            }
        }
        if (this.permitted != null) {
            for (int i2 = 0; i2 < this.permitted.size(); i2++) {
                GeneralSubtree subtree2 = this.permitted.get(i2);
                if (subtree2.getMinimum() != 0) {
                    this.hasMin = true;
                }
                if (subtree2.getMaximum() != -1) {
                    this.hasMax = true;
                }
            }
        }
        this.minMaxValid = true;
    }

    private void encodeThis() throws IOException {
        this.minMaxValid = false;
        if (this.permitted == null && this.excluded == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream seq = new DerOutputStream();
        DerOutputStream tagged = new DerOutputStream();
        if (this.permitted != null) {
            DerOutputStream tmp = new DerOutputStream();
            this.permitted.encode(tmp);
            tagged.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), tmp);
        }
        if (this.excluded != null) {
            DerOutputStream tmp2 = new DerOutputStream();
            this.excluded.encode(tmp2);
            tagged.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 1), tmp2);
        }
        seq.write((byte) 48, tagged);
        this.extensionValue = seq.toByteArray();
    }

    public NameConstraintsExtension(GeneralSubtrees permitted2, GeneralSubtrees excluded2) throws IOException {
        this.permitted = permitted2;
        this.excluded = excluded2;
        this.extensionId = PKIXExtensions.NameConstraints_Id;
        this.critical = true;
        encodeThis();
    }

    public NameConstraintsExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = PKIXExtensions.NameConstraints_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        DerValue val = new DerValue(this.extensionValue);
        if (val.tag != 48) {
            throw new IOException("Invalid encoding for NameConstraintsExtension.");
        } else if (val.data != null) {
            while (val.data.available() != 0) {
                DerValue opt = val.data.getDerValue();
                if (!opt.isContextSpecific((byte) 0) || !opt.isConstructed()) {
                    if (!opt.isContextSpecific((byte) 1) || !opt.isConstructed()) {
                        throw new IOException("Invalid encoding of NameConstraintsExtension.");
                    } else if (this.excluded == null) {
                        opt.resetTag((byte) 48);
                        this.excluded = new GeneralSubtrees(opt);
                    } else {
                        throw new IOException("Duplicate excluded GeneralSubtrees in NameConstraintsExtension.");
                    }
                } else if (this.permitted == null) {
                    opt.resetTag((byte) 48);
                    this.permitted = new GeneralSubtrees(opt);
                } else {
                    throw new IOException("Duplicate permitted GeneralSubtrees in NameConstraintsExtension.");
                }
            }
            this.minMaxValid = false;
        }
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("NameConstraints: [");
        String str2 = "";
        if (this.permitted == null) {
            str = str2;
        } else {
            str = "\n    Permitted:" + this.permitted.toString();
        }
        sb.append(str);
        if (this.excluded != null) {
            str2 = "\n    Excluded:" + this.excluded.toString();
        }
        sb.append(str2);
        sb.append("   ]\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.NameConstraints_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (name.equalsIgnoreCase(PERMITTED_SUBTREES)) {
            if (obj instanceof GeneralSubtrees) {
                this.permitted = (GeneralSubtrees) obj;
            } else {
                throw new IOException("Attribute value should be of type GeneralSubtrees.");
            }
        } else if (!name.equalsIgnoreCase(EXCLUDED_SUBTREES)) {
            throw new IOException("Attribute name not recognized by CertAttrSet:NameConstraintsExtension.");
        } else if (obj instanceof GeneralSubtrees) {
            this.excluded = (GeneralSubtrees) obj;
        } else {
            throw new IOException("Attribute value should be of type GeneralSubtrees.");
        }
        encodeThis();
    }

    @Override // sun.security.x509.CertAttrSet
    public GeneralSubtrees get(String name) throws IOException {
        if (name.equalsIgnoreCase(PERMITTED_SUBTREES)) {
            return this.permitted;
        }
        if (name.equalsIgnoreCase(EXCLUDED_SUBTREES)) {
            return this.excluded;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:NameConstraintsExtension.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase(PERMITTED_SUBTREES)) {
            this.permitted = null;
        } else if (name.equalsIgnoreCase(EXCLUDED_SUBTREES)) {
            this.excluded = null;
        } else {
            throw new IOException("Attribute name not recognized by CertAttrSet:NameConstraintsExtension.");
        }
        encodeThis();
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement(PERMITTED_SUBTREES);
        elements.addElement(EXCLUDED_SUBTREES);
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }

    public void merge(NameConstraintsExtension newConstraints) throws IOException {
        GeneralSubtrees newExcluded;
        if (newConstraints != null) {
            GeneralSubtrees newExcluded2 = newConstraints.get(EXCLUDED_SUBTREES);
            GeneralSubtrees generalSubtrees = this.excluded;
            GeneralSubtrees generalSubtrees2 = null;
            if (generalSubtrees == null) {
                this.excluded = newExcluded2 != null ? (GeneralSubtrees) newExcluded2.clone() : null;
            } else if (newExcluded2 != null) {
                generalSubtrees.union(newExcluded2);
            }
            GeneralSubtrees newPermitted = newConstraints.get(PERMITTED_SUBTREES);
            GeneralSubtrees generalSubtrees3 = this.permitted;
            if (generalSubtrees3 == null) {
                if (newPermitted != null) {
                    generalSubtrees2 = (GeneralSubtrees) newPermitted.clone();
                }
                this.permitted = generalSubtrees2;
            } else if (!(newPermitted == null || (newExcluded = generalSubtrees3.intersect(newPermitted)) == null)) {
                GeneralSubtrees generalSubtrees4 = this.excluded;
                if (generalSubtrees4 != null) {
                    generalSubtrees4.union(newExcluded);
                } else {
                    this.excluded = (GeneralSubtrees) newExcluded.clone();
                }
            }
            GeneralSubtrees generalSubtrees5 = this.permitted;
            if (generalSubtrees5 != null) {
                generalSubtrees5.reduce(this.excluded);
            }
            encodeThis();
        }
    }

    public boolean verify(X509Certificate cert) throws IOException {
        if (cert != null) {
            if (!this.minMaxValid) {
                calcMinMax();
            }
            if (this.hasMin) {
                throw new IOException("Non-zero minimum BaseDistance in name constraints not supported");
            } else if (!this.hasMax) {
                X500Name subject = X500Name.asX500Name(cert.getSubjectX500Principal());
                if (!(subject.isEmpty() || verify(subject))) {
                    return false;
                }
                GeneralNames altNames = null;
                try {
                    SubjectAlternativeNameExtension altNameExt = X509CertImpl.toImpl(cert).getSubjectAlternativeNameExtension();
                    if (altNameExt != null) {
                        altNames = altNameExt.get(SubjectAlternativeNameExtension.SUBJECT_NAME);
                    }
                    if (altNames == null) {
                        return verifyRFC822SpecialCase(subject);
                    }
                    for (int i = 0; i < altNames.size(); i++) {
                        if (!verify(altNames.get(i).getName())) {
                            return false;
                        }
                    }
                    return true;
                } catch (CertificateException ce) {
                    throw new IOException("Unable to extract extensions from certificate: " + ce.getMessage());
                }
            } else {
                throw new IOException("Maximum BaseDistance in name constraints not supported");
            }
        } else {
            throw new IOException("Certificate is null");
        }
    }

    public boolean verify(GeneralNameInterface name) throws IOException {
        GeneralName gn;
        GeneralNameInterface perName;
        int constrains;
        GeneralName gn2;
        GeneralNameInterface exName;
        int constrains2;
        if (name != null) {
            GeneralSubtrees generalSubtrees = this.excluded;
            if (generalSubtrees != null && generalSubtrees.size() > 0) {
                for (int i = 0; i < this.excluded.size(); i++) {
                    GeneralSubtree gs = this.excluded.get(i);
                    if (!(gs == null || (gn2 = gs.getName()) == null || (exName = gn2.getName()) == null || (constrains2 = exName.constrains(name)) == -1)) {
                        if (constrains2 == 0 || constrains2 == 1) {
                            return false;
                        }
                        if (constrains2 != 2 && constrains2 == 3) {
                        }
                    }
                }
            }
            GeneralSubtrees generalSubtrees2 = this.permitted;
            if (generalSubtrees2 != null && generalSubtrees2.size() > 0) {
                boolean sameType = false;
                for (int i2 = 0; i2 < this.permitted.size(); i2++) {
                    GeneralSubtree gs2 = this.permitted.get(i2);
                    if (!(gs2 == null || (gn = gs2.getName()) == null || (perName = gn.getName()) == null || (constrains = perName.constrains(name)) == -1)) {
                        if (constrains == 0 || constrains == 1) {
                            return true;
                        }
                        if (constrains == 2 || constrains == 3) {
                            sameType = true;
                        }
                    }
                }
                if (sameType) {
                    return false;
                }
            }
            return true;
        }
        throw new IOException("name is null");
    }

    public boolean verifyRFC822SpecialCase(X500Name subject) throws IOException {
        String attrValue;
        for (AVA ava : subject.allAvas()) {
            if (ava.getObjectIdentifier().equals((Object) PKCS9Attribute.EMAIL_ADDRESS_OID) && (attrValue = ava.getValueString()) != null) {
                try {
                    if (!verify(new RFC822Name(attrValue))) {
                        return false;
                    }
                } catch (IOException e) {
                }
            }
        }
        return true;
    }

    public Object clone() {
        try {
            NameConstraintsExtension newNCE = (NameConstraintsExtension) super.clone();
            if (this.permitted != null) {
                newNCE.permitted = (GeneralSubtrees) this.permitted.clone();
            }
            if (this.excluded != null) {
                newNCE.excluded = (GeneralSubtrees) this.excluded.clone();
            }
            return newNCE;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("CloneNotSupportedException while cloning NameConstraintsException. This should never happen.");
        }
    }
}
