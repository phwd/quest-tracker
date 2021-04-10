package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.Extension;
import java.util.Date;
import java.util.Enumeration;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class InvalidityDateExtension extends Extension implements CertAttrSet<String> {
    public static final String DATE = "date";
    public static final String NAME = "InvalidityDate";
    private Date date;

    private void encodeThis() throws IOException {
        if (this.date == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream dos = new DerOutputStream();
        dos.putGeneralizedTime(this.date);
        this.extensionValue = dos.toByteArray();
    }

    public InvalidityDateExtension(Date date2) throws IOException {
        this(false, date2);
    }

    public InvalidityDateExtension(boolean critical, Date date2) throws IOException {
        this.extensionId = PKIXExtensions.InvalidityDate_Id;
        this.critical = critical;
        this.date = date2;
        encodeThis();
    }

    public InvalidityDateExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = PKIXExtensions.InvalidityDate_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        this.date = new DerValue(this.extensionValue).getGeneralizedTime();
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!(obj instanceof Date)) {
            throw new IOException("Attribute must be of type Date.");
        } else if (name.equalsIgnoreCase(DATE)) {
            this.date = (Date) obj;
            encodeThis();
        } else {
            throw new IOException("Name not supported by InvalidityDateExtension");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public Date get(String name) throws IOException {
        if (name.equalsIgnoreCase(DATE)) {
            Date date2 = this.date;
            if (date2 == null) {
                return null;
            }
            return new Date(date2.getTime());
        }
        throw new IOException("Name not supported by InvalidityDateExtension");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase(DATE)) {
            this.date = null;
            encodeThis();
            return;
        }
        throw new IOException("Name not supported by InvalidityDateExtension");
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        return super.toString() + "    Invalidity Date: " + String.valueOf(this.date);
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.InvalidityDate_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement(DATE);
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }

    public static InvalidityDateExtension toImpl(Extension ext) throws IOException {
        if (ext instanceof InvalidityDateExtension) {
            return (InvalidityDateExtension) ext;
        }
        return new InvalidityDateExtension(Boolean.valueOf(ext.isCritical()), ext.getValue());
    }
}
