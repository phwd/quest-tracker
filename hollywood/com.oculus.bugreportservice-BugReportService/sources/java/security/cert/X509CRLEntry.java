package java.security.cert;

public abstract class X509CRLEntry implements X509Extension {
    public abstract byte[] getEncoded();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X509CRLEntry)) {
            return false;
        }
        try {
            byte[] encoded = getEncoded();
            byte[] encoded2 = ((X509CRLEntry) obj).getEncoded();
            if (encoded.length != encoded2.length) {
                return false;
            }
            for (int i = 0; i < encoded.length; i++) {
                if (encoded[i] != encoded2[i]) {
                    return false;
                }
            }
            return true;
        } catch (CRLException unused) {
            return false;
        }
    }

    public int hashCode() {
        int i = 0;
        try {
            byte[] encoded = getEncoded();
            for (int i2 = 1; i2 < encoded.length; i2++) {
                i += encoded[i2] * i2;
            }
        } catch (CRLException unused) {
        }
        return i;
    }
}
