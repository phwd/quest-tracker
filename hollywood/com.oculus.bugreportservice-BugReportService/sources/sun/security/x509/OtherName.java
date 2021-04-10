package sun.security.x509;

import java.io.IOException;
import java.util.Arrays;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class OtherName implements GeneralNameInterface {
    private GeneralNameInterface gni = null;
    private int myhash = -1;
    private String name;
    private byte[] nameValue = null;
    private ObjectIdentifier oid;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 0;
    }

    public OtherName(DerValue derValue) {
        DerInputStream derInputStream = derValue.toDerInputStream();
        this.oid = derInputStream.getOID();
        this.nameValue = derInputStream.getDerValue().toByteArray();
        this.gni = getGNI(this.oid, this.nameValue);
        GeneralNameInterface generalNameInterface = this.gni;
        if (generalNameInterface != null) {
            this.name = generalNameInterface.toString();
            return;
        }
        this.name = "Unrecognized ObjectIdentifier: " + this.oid.toString();
    }

    private GeneralNameInterface getGNI(ObjectIdentifier objectIdentifier, byte[] bArr) {
        try {
            Class cls = OIDMap.getClass(objectIdentifier);
            if (cls == null) {
                return null;
            }
            return (GeneralNameInterface) cls.getConstructor(Object.class).newInstance(bArr);
        } catch (Exception e) {
            throw new IOException("Instantiation error: " + e, e);
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        GeneralNameInterface generalNameInterface = this.gni;
        if (generalNameInterface != null) {
            generalNameInterface.encode(derOutputStream);
            return;
        }
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream2.putOID(this.oid);
        derOutputStream2.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), this.nameValue);
        derOutputStream.write((byte) 48, derOutputStream2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OtherName)) {
            return false;
        }
        OtherName otherName = (OtherName) obj;
        if (!otherName.oid.equals(this.oid)) {
            return false;
        }
        try {
            GeneralNameInterface gni2 = getGNI(otherName.oid, otherName.nameValue);
            if (gni2 != null) {
                return gni2.constrains(this) == 0;
            }
            return Arrays.equals(this.nameValue, otherName.nameValue);
        } catch (IOException unused) {
            return false;
        }
    }

    public int hashCode() {
        if (this.myhash == -1) {
            this.myhash = this.oid.hashCode() + 37;
            int i = 0;
            while (true) {
                byte[] bArr = this.nameValue;
                if (i >= bArr.length) {
                    break;
                }
                this.myhash = (this.myhash * 37) + bArr[i];
                i++;
            }
        }
        return this.myhash;
    }

    public String toString() {
        return "Other-Name: " + this.name;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        if (generalNameInterface == null || generalNameInterface.getType() != 0) {
            return -1;
        }
        throw new UnsupportedOperationException("Narrowing, widening, and matching are not supported for OtherName.");
    }
}
