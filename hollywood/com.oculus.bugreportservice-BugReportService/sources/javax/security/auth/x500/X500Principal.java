package javax.security.auth.x500;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.Principal;
import sun.security.x509.X500Name;

public final class X500Principal implements Principal, Serializable {
    private static final long serialVersionUID = -500463348111345721L;
    private transient X500Name thisX500Name;

    public X500Principal(byte[] bArr) {
        try {
            this.thisX500Name = new X500Name(bArr);
        } catch (Exception e) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("improperly specified input name");
            illegalArgumentException.initCause(e);
            throw illegalArgumentException;
        }
    }

    @Override // java.security.Principal
    public String getName() {
        return getName("RFC2253");
    }

    public String getName(String str) {
        if (str != null) {
            if (str.equalsIgnoreCase("RFC1779")) {
                return this.thisX500Name.getRFC1779Name();
            }
            if (str.equalsIgnoreCase("RFC2253")) {
                return this.thisX500Name.getRFC2253Name();
            }
            if (str.equalsIgnoreCase("CANONICAL")) {
                return this.thisX500Name.getRFC2253CanonicalName();
            }
        }
        throw new IllegalArgumentException("invalid format specified");
    }

    public String toString() {
        return this.thisX500Name.toString();
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X500Principal)) {
            return false;
        }
        return this.thisX500Name.equals(((X500Principal) obj).thisX500Name);
    }

    public int hashCode() {
        return this.thisX500Name.hashCode();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeObject(this.thisX500Name.getEncodedInternal());
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readObject();
        throw null;
    }
}
