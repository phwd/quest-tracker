package sun.security.x509;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateSerialNumber implements CertAttrSet<String> {
    public static final String IDENT = "x509.info.serialNumber";
    public static final String NAME = "serialNumber";
    public static final String NUMBER = "number";
    private SerialNumber serial;

    public CertificateSerialNumber(BigInteger num) {
        this.serial = new SerialNumber(num);
    }

    public CertificateSerialNumber(int num) {
        this.serial = new SerialNumber(num);
    }

    public CertificateSerialNumber(DerInputStream in) throws IOException {
        this.serial = new SerialNumber(in);
    }

    public CertificateSerialNumber(InputStream in) throws IOException {
        this.serial = new SerialNumber(in);
    }

    public CertificateSerialNumber(DerValue val) throws IOException {
        this.serial = new SerialNumber(val);
    }

    @Override // sun.security.x509.CertAttrSet
    public String toString() {
        SerialNumber serialNumber = this.serial;
        if (serialNumber == null) {
            return "";
        }
        return serialNumber.toString();
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        this.serial.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!(obj instanceof SerialNumber)) {
            throw new IOException("Attribute must be of type SerialNumber.");
        } else if (name.equalsIgnoreCase("number")) {
            this.serial = (SerialNumber) obj;
        } else {
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public SerialNumber get(String name) throws IOException {
        if (name.equalsIgnoreCase("number")) {
            return this.serial;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("number")) {
            this.serial = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("number");
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "serialNumber";
    }
}
