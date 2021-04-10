package javax.crypto.spec;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Locale;
import javax.crypto.SecretKey;

public class SecretKeySpec implements KeySpec, SecretKey {
    private static final long serialVersionUID = 6577238317307289933L;
    private String algorithm;
    private byte[] key;

    public SecretKeySpec(byte[] key2, String algorithm2) {
        if (key2 == null || algorithm2 == null) {
            throw new IllegalArgumentException("Missing argument");
        } else if (key2.length != 0) {
            this.key = (byte[]) key2.clone();
            this.algorithm = algorithm2;
        } else {
            throw new IllegalArgumentException("Empty key");
        }
    }

    public SecretKeySpec(byte[] key2, int offset, int len, String algorithm2) {
        if (key2 == null || algorithm2 == null) {
            throw new IllegalArgumentException("Missing argument");
        } else if (key2.length == 0) {
            throw new IllegalArgumentException("Empty key");
        } else if (key2.length - offset < len) {
            throw new IllegalArgumentException("Invalid offset/length combination");
        } else if (len >= 0) {
            this.key = new byte[len];
            System.arraycopy(key2, offset, this.key, 0, len);
            this.algorithm = algorithm2;
        } else {
            throw new ArrayIndexOutOfBoundsException("len is negative");
        }
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public String getFormat() {
        return "RAW";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return (byte[]) this.key.clone();
    }

    public int hashCode() {
        int retval = 0;
        int i = 1;
        while (true) {
            byte[] bArr = this.key;
            if (i >= bArr.length) {
                break;
            }
            retval += bArr[i] * i;
            i++;
        }
        if (this.algorithm.equalsIgnoreCase("TripleDES")) {
            return "desede".hashCode() ^ retval;
        }
        return this.algorithm.toLowerCase(Locale.ENGLISH).hashCode() ^ retval;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecretKey)) {
            return false;
        }
        String thatAlg = ((SecretKey) obj).getAlgorithm();
        if (!thatAlg.equalsIgnoreCase(this.algorithm) && ((!thatAlg.equalsIgnoreCase("DESede") || !this.algorithm.equalsIgnoreCase("TripleDES")) && (!thatAlg.equalsIgnoreCase("TripleDES") || !this.algorithm.equalsIgnoreCase("DESede")))) {
            return false;
        }
        return MessageDigest.isEqual(this.key, ((SecretKey) obj).getEncoded());
    }
}
