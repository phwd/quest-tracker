package sun.security.x509;

import java.io.IOException;
import java.util.Arrays;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class OtherName implements GeneralNameInterface {
    private static final byte TAG_VALUE = 0;
    private GeneralNameInterface gni = null;
    private int myhash = -1;
    private String name;
    private byte[] nameValue = null;
    private ObjectIdentifier oid;

    public OtherName(ObjectIdentifier oid2, byte[] value) throws IOException {
        if (oid2 == null || value == null) {
            throw new NullPointerException("parameters may not be null");
        }
        this.oid = oid2;
        this.nameValue = value;
        this.gni = getGNI(oid2, value);
        GeneralNameInterface generalNameInterface = this.gni;
        if (generalNameInterface != null) {
            this.name = generalNameInterface.toString();
            return;
        }
        this.name = "Unrecognized ObjectIdentifier: " + oid2.toString();
    }

    public OtherName(DerValue derValue) throws IOException {
        DerInputStream in = derValue.toDerInputStream();
        this.oid = in.getOID();
        this.nameValue = in.getDerValue().toByteArray();
        this.gni = getGNI(this.oid, this.nameValue);
        GeneralNameInterface generalNameInterface = this.gni;
        if (generalNameInterface != null) {
            this.name = generalNameInterface.toString();
            return;
        }
        this.name = "Unrecognized ObjectIdentifier: " + this.oid.toString();
    }

    public ObjectIdentifier getOID() {
        return this.oid;
    }

    public byte[] getNameValue() {
        return (byte[]) this.nameValue.clone();
    }

    private GeneralNameInterface getGNI(ObjectIdentifier oid2, byte[] nameValue2) throws IOException {
        try {
            Class<?> extClass = OIDMap.getClass(oid2);
            if (extClass == null) {
                return null;
            }
            return (GeneralNameInterface) extClass.getConstructor(Object.class).newInstance(nameValue2);
        } catch (Exception e) {
            throw new IOException("Instantiation error: " + ((Object) e), e);
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 0;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream out) throws IOException {
        GeneralNameInterface generalNameInterface = this.gni;
        if (generalNameInterface != null) {
            generalNameInterface.encode(out);
            return;
        }
        DerOutputStream tmp = new DerOutputStream();
        tmp.putOID(this.oid);
        tmp.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), this.nameValue);
        out.write((byte) 48, tmp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OtherName)) {
            return false;
        }
        OtherName otherOther = (OtherName) other;
        if (!otherOther.oid.equals((Object) this.oid)) {
            return false;
        }
        try {
            GeneralNameInterface otherGNI = getGNI(otherOther.oid, otherOther.nameValue);
            if (otherGNI == null) {
                return Arrays.equals(this.nameValue, otherOther.nameValue);
            }
            try {
                if (otherGNI.constrains(this) == 0) {
                    return true;
                }
                return false;
            } catch (UnsupportedOperationException e) {
                return false;
            }
        } catch (IOException e2) {
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
    public int constrains(GeneralNameInterface inputName) {
        if (inputName == null || inputName.getType() != 0) {
            return -1;
        }
        throw new UnsupportedOperationException("Narrowing, widening, and matching are not supported for OtherName.");
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int subtreeDepth() {
        throw new UnsupportedOperationException("subtreeDepth() not supported for generic OtherName");
    }
}
