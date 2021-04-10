package java.security.spec;

import java.math.BigInteger;
import java.util.Arrays;

public class ECFieldF2m implements ECField {
    private int[] ks;
    private int m;
    private BigInteger rp;

    public ECFieldF2m(int m2) {
        if (m2 > 0) {
            this.m = m2;
            this.ks = null;
            this.rp = null;
            return;
        }
        throw new IllegalArgumentException("m is not positive");
    }

    public ECFieldF2m(int m2, BigInteger rp2) {
        this.m = m2;
        this.rp = rp2;
        if (m2 > 0) {
            int bitCount = this.rp.bitCount();
            if (!this.rp.testBit(0) || !this.rp.testBit(m2) || !(bitCount == 3 || bitCount == 5)) {
                throw new IllegalArgumentException("rp does not represent a valid reduction polynomial");
            }
            BigInteger temp = this.rp.clearBit(0).clearBit(m2);
            this.ks = new int[(bitCount - 2)];
            for (int i = this.ks.length - 1; i >= 0; i--) {
                int index = temp.getLowestSetBit();
                this.ks[i] = index;
                temp = temp.clearBit(index);
            }
            return;
        }
        throw new IllegalArgumentException("m is not positive");
    }

    public ECFieldF2m(int m2, int[] ks2) {
        this.m = m2;
        this.ks = (int[]) ks2.clone();
        if (m2 > 0) {
            int[] iArr = this.ks;
            if (iArr.length == 1 || iArr.length == 3) {
                int i = 0;
                while (true) {
                    int[] iArr2 = this.ks;
                    if (i >= iArr2.length) {
                        this.rp = BigInteger.ONE;
                        this.rp = this.rp.setBit(m2);
                        int j = 0;
                        while (true) {
                            int[] iArr3 = this.ks;
                            if (j < iArr3.length) {
                                this.rp = this.rp.setBit(iArr3[j]);
                                j++;
                            } else {
                                return;
                            }
                        }
                    } else if (iArr2[i] < 1 || iArr2[i] > m2 - 1) {
                    } else if (i == 0 || iArr2[i] < iArr2[i - 1]) {
                        i++;
                    } else {
                        throw new IllegalArgumentException("values in ks are not in descending order");
                    }
                }
                throw new IllegalArgumentException("ks[" + i + "] is out of range");
            }
            throw new IllegalArgumentException("length of ks is neither 1 nor 3");
        }
        throw new IllegalArgumentException("m is not positive");
    }

    @Override // java.security.spec.ECField
    public int getFieldSize() {
        return this.m;
    }

    public int getM() {
        return this.m;
    }

    public BigInteger getReductionPolynomial() {
        return this.rp;
    }

    public int[] getMidTermsOfReductionPolynomial() {
        int[] iArr = this.ks;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECFieldF2m)) {
            return false;
        }
        if (this.m != ((ECFieldF2m) obj).m || !Arrays.equals(this.ks, ((ECFieldF2m) obj).ks)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = this.m << 5;
        BigInteger bigInteger = this.rp;
        return value + (bigInteger == null ? 0 : bigInteger.hashCode());
    }
}
