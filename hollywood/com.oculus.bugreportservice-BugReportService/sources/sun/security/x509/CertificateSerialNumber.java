package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateSerialNumber implements CertAttrSet {
    private SerialNumber serial;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "serialNumber";
    }

    public CertificateSerialNumber(DerValue derValue) {
        this.serial = new SerialNumber(derValue);
    }

    public String toString() {
        SerialNumber serialNumber = this.serial;
        if (serialNumber == null) {
            return "";
        }
        return serialNumber.toString();
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        this.serial.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    public SerialNumber get(String str) {
        if (str.equalsIgnoreCase("number")) {
            return this.serial;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
    }
}
