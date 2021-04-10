package sun.security.x509;

import android.icu.text.PluralRules;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import sun.security.util.Debug;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class CRLNumberExtension extends Extension implements CertAttrSet<String> {
    private static final String LABEL = "CRL Number";
    public static final String NAME = "CRLNumber";
    public static final String NUMBER = "value";
    private BigInteger crlNumber;
    private String extensionLabel;
    private String extensionName;

    private void encodeThis() throws IOException {
        if (this.crlNumber == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream os = new DerOutputStream();
        os.putInteger(this.crlNumber);
        this.extensionValue = os.toByteArray();
    }

    public CRLNumberExtension(int crlNum) throws IOException {
        this(PKIXExtensions.CRLNumber_Id, false, BigInteger.valueOf((long) crlNum), NAME, LABEL);
    }

    public CRLNumberExtension(BigInteger crlNum) throws IOException {
        this(PKIXExtensions.CRLNumber_Id, false, crlNum, NAME, LABEL);
    }

    protected CRLNumberExtension(ObjectIdentifier extensionId, boolean isCritical, BigInteger crlNum, String extensionName2, String extensionLabel2) throws IOException {
        this.crlNumber = null;
        this.extensionId = extensionId;
        this.critical = isCritical;
        this.crlNumber = crlNum;
        this.extensionName = extensionName2;
        this.extensionLabel = extensionLabel2;
        encodeThis();
    }

    public CRLNumberExtension(Boolean critical, Object value) throws IOException {
        this(PKIXExtensions.CRLNumber_Id, critical, value, NAME, LABEL);
    }

    protected CRLNumberExtension(ObjectIdentifier extensionId, Boolean critical, Object value, String extensionName2, String extensionLabel2) throws IOException {
        this.crlNumber = null;
        this.extensionId = extensionId;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        this.crlNumber = new DerValue(this.extensionValue).getBigInteger();
        this.extensionName = extensionName2;
        this.extensionLabel = extensionLabel2;
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!name.equalsIgnoreCase("value")) {
            throw new IOException("Attribute name not recognized by CertAttrSet:" + this.extensionName + ".");
        } else if (obj instanceof BigInteger) {
            this.crlNumber = (BigInteger) obj;
            encodeThis();
        } else {
            throw new IOException("Attribute must be of type BigInteger.");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public BigInteger get(String name) throws IOException {
        if (name.equalsIgnoreCase("value")) {
            return this.crlNumber;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:" + this.extensionName + '.');
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("value")) {
            this.crlNumber = null;
            encodeThis();
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:" + this.extensionName + ".");
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.extensionLabel);
        sb.append(PluralRules.KEYWORD_RULE_SEPARATOR);
        BigInteger bigInteger = this.crlNumber;
        sb.append(bigInteger == null ? "" : Debug.toHexString(bigInteger));
        sb.append("\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        new DerOutputStream();
        encode(out, PKIXExtensions.CRLNumber_Id, true);
    }

    /* access modifiers changed from: protected */
    public void encode(OutputStream out, ObjectIdentifier extensionId, boolean isCritical) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = extensionId;
            this.critical = isCritical;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("value");
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return this.extensionName;
    }
}
