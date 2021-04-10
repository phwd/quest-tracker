package sun.security.x509;

import java.io.IOException;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class GeneralName {
    private GeneralNameInterface name;

    public GeneralName(DerValue derValue) {
        this(derValue, false);
    }

    public GeneralName(DerValue derValue, boolean z) {
        this.name = null;
        short s = (short) ((byte) (derValue.tag & 31));
        switch (s) {
            case 0:
                if (!derValue.isContextSpecific() || !derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of Other-Name");
                }
                derValue.resetTag((byte) 48);
                this.name = new OtherName(derValue);
                return;
            case 1:
                if (!derValue.isContextSpecific() || derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of RFC822 name");
                }
                derValue.resetTag((byte) 22);
                new RFC822Name(derValue);
                throw null;
            case 2:
                if (!derValue.isContextSpecific() || derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of DNS name");
                }
                derValue.resetTag((byte) 22);
                new DNSName(derValue);
                throw null;
            case 3:
            default:
                throw new IOException("Unrecognized GeneralName tag, (" + ((int) s) + ")");
            case 4:
                if (!derValue.isContextSpecific() || !derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of Directory name");
                }
                this.name = new X500Name(derValue.getData());
                return;
            case 5:
                if (!derValue.isContextSpecific() || !derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of EDI name");
                }
                derValue.resetTag((byte) 48);
                this.name = new EDIPartyName(derValue);
                return;
            case 6:
                if (!derValue.isContextSpecific() || derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of URI");
                }
                derValue.resetTag((byte) 22);
                if (z) {
                    URIName.nameConstraint(derValue);
                    throw null;
                } else {
                    new URIName(derValue);
                    throw null;
                }
            case 7:
                if (!derValue.isContextSpecific() || derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of IP address");
                }
                derValue.resetTag((byte) 4);
                this.name = new IPAddressName(derValue);
                return;
            case 8:
                if (!derValue.isContextSpecific() || derValue.isConstructed()) {
                    throw new IOException("Invalid encoding of OID name");
                }
                derValue.resetTag((byte) 6);
                this.name = new OIDName(derValue);
                return;
        }
    }

    public GeneralNameInterface getName() {
        return this.name;
    }

    public String toString() {
        return this.name.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeneralName)) {
            return false;
        }
        try {
            return this.name.constrains(((GeneralName) obj).name) == 0;
        } catch (UnsupportedOperationException unused) {
            return false;
        }
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        this.name.encode(derOutputStream2);
        int type = this.name.getType();
        if (type == 0 || type == 3 || type == 5) {
            derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) type), derOutputStream2);
        } else if (type == 4) {
            derOutputStream.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) type), derOutputStream2);
        } else {
            derOutputStream.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) type), derOutputStream2);
        }
    }
}
