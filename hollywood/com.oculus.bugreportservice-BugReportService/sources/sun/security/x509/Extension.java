package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class Extension implements java.security.cert.Extension {
    protected boolean critical = false;
    protected ObjectIdentifier extensionId = null;
    protected byte[] extensionValue = null;

    public Extension() {
    }

    public Extension(DerValue derValue) {
        DerInputStream derInputStream = derValue.toDerInputStream();
        this.extensionId = derInputStream.getOID();
        DerValue derValue2 = derInputStream.getDerValue();
        if (derValue2.tag == 1) {
            this.critical = derValue2.getBoolean();
            this.extensionValue = derInputStream.getDerValue().getOctetString();
            return;
        }
        this.critical = false;
        this.extensionValue = derValue2.getOctetString();
    }

    public Extension(Extension extension) {
        this.extensionId = extension.extensionId;
        this.critical = extension.critical;
        this.extensionValue = extension.extensionValue;
    }

    public void encode(OutputStream outputStream) {
        if (outputStream != null) {
            DerOutputStream derOutputStream = new DerOutputStream();
            DerOutputStream derOutputStream2 = new DerOutputStream();
            derOutputStream.putOID(this.extensionId);
            boolean z = this.critical;
            if (z) {
                derOutputStream.putBoolean(z);
            }
            derOutputStream.putOctetString(this.extensionValue);
            derOutputStream2.write((byte) 48, derOutputStream);
            outputStream.write(derOutputStream2.toByteArray());
            return;
        }
        throw new NullPointerException();
    }

    public void encode(DerOutputStream derOutputStream) {
        if (this.extensionId == null) {
            throw new IOException("Null OID to encode for the extension!");
        } else if (this.extensionValue != null) {
            DerOutputStream derOutputStream2 = new DerOutputStream();
            derOutputStream2.putOID(this.extensionId);
            boolean z = this.critical;
            if (z) {
                derOutputStream2.putBoolean(z);
            }
            derOutputStream2.putOctetString(this.extensionValue);
            derOutputStream.write((byte) 48, derOutputStream2);
        } else {
            throw new IOException("No value to encode for the extension!");
        }
    }

    public boolean isCritical() {
        return this.critical;
    }

    public ObjectIdentifier getExtensionId() {
        return this.extensionId;
    }

    public byte[] getExtensionValue() {
        return this.extensionValue;
    }

    public String toString() {
        String str = "ObjectId: " + this.extensionId.toString();
        if (this.critical) {
            return str + " Criticality=true\n";
        }
        return str + " Criticality=false\n";
    }

    public int hashCode() {
        byte[] bArr = this.extensionValue;
        int i = 0;
        if (bArr != null) {
            int length = bArr.length;
            while (length > 0) {
                int i2 = length - 1;
                i += length * bArr[i2];
                length = i2;
            }
        }
        return (((i * 31) + this.extensionId.hashCode()) * 31) + (this.critical ? 1231 : 1237);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Extension)) {
            return false;
        }
        Extension extension = (Extension) obj;
        if (this.critical == extension.critical && this.extensionId.equals(extension.extensionId)) {
            return Arrays.equals(this.extensionValue, extension.extensionValue);
        }
        return false;
    }
}
