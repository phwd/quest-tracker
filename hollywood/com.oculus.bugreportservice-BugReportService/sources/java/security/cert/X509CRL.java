package java.security.cert;

import java.util.Arrays;
import sun.security.x509.X509CRLImpl;

public abstract class X509CRL extends CRL implements X509Extension {
    public abstract byte[] getEncoded();

    protected X509CRL() {
        super("X.509");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X509CRL)) {
            return false;
        }
        try {
            return Arrays.equals(X509CRLImpl.getEncodedInternal(this), X509CRLImpl.getEncodedInternal((X509CRL) obj));
        } catch (CRLException unused) {
            return false;
        }
    }

    public int hashCode() {
        int i = 0;
        try {
            byte[] encodedInternal = X509CRLImpl.getEncodedInternal(this);
            for (int i2 = 1; i2 < encodedInternal.length; i2++) {
                i += encodedInternal[i2] * i2;
            }
        } catch (CRLException unused) {
        }
        return i;
    }
}
