package sun.security.x509;

import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class OIDName implements GeneralNameInterface {
    private ObjectIdentifier oid;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 8;
    }

    public OIDName(DerValue derValue) {
        this.oid = derValue.getOID();
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.putOID(this.oid);
    }

    public String toString() {
        return "OIDName: " + this.oid.toString();
    }

    public ObjectIdentifier getOID() {
        return this.oid;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OIDName)) {
            return false;
        }
        return this.oid.equals(((OIDName) obj).oid);
    }

    public int hashCode() {
        return this.oid.hashCode();
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        if (generalNameInterface == null || generalNameInterface.getType() != 8) {
            return -1;
        }
        if (equals((OIDName) generalNameInterface)) {
            return 0;
        }
        throw new UnsupportedOperationException("Narrowing and widening are not supported for OIDNames");
    }
}
