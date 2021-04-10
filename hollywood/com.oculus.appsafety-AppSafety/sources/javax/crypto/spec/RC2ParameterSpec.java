package javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

public class RC2ParameterSpec implements AlgorithmParameterSpec {
    private int effectiveKeyBits;
    private byte[] iv;

    public RC2ParameterSpec(int effectiveKeyBits2) {
        this.iv = null;
        this.effectiveKeyBits = effectiveKeyBits2;
    }

    public RC2ParameterSpec(int effectiveKeyBits2, byte[] iv2) {
        this(effectiveKeyBits2, iv2, 0);
    }

    public RC2ParameterSpec(int effectiveKeyBits2, byte[] iv2, int offset) {
        this.iv = null;
        this.effectiveKeyBits = effectiveKeyBits2;
        if (iv2 == null) {
            throw new IllegalArgumentException("IV missing");
        } else if (iv2.length - offset >= 8) {
            this.iv = new byte[8];
            System.arraycopy(iv2, offset, this.iv, 0, 8);
        } else {
            throw new IllegalArgumentException("IV too short");
        }
    }

    public int getEffectiveKeyBits() {
        return this.effectiveKeyBits;
    }

    public byte[] getIV() {
        byte[] bArr = this.iv;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RC2ParameterSpec)) {
            return false;
        }
        RC2ParameterSpec other = (RC2ParameterSpec) obj;
        if (this.effectiveKeyBits != other.effectiveKeyBits || !Arrays.equals(this.iv, other.iv)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int retval = 0;
        if (this.iv != null) {
            int i = 1;
            while (true) {
                byte[] bArr = this.iv;
                if (i >= bArr.length) {
                    break;
                }
                retval += bArr[i] * i;
                i++;
            }
        }
        return this.effectiveKeyBits + retval;
    }
}
