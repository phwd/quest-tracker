package com.android.org.bouncycastle.math.field;

import com.android.org.bouncycastle.util.Arrays;

/* access modifiers changed from: package-private */
public class GF2Polynomial implements Polynomial {
    protected final int[] exponents;

    GF2Polynomial(int[] exponents2) {
        this.exponents = Arrays.clone(exponents2);
    }

    @Override // com.android.org.bouncycastle.math.field.Polynomial
    public int getDegree() {
        int[] iArr = this.exponents;
        return iArr[iArr.length - 1];
    }

    @Override // com.android.org.bouncycastle.math.field.Polynomial
    public int[] getExponentsPresent() {
        return Arrays.clone(this.exponents);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GF2Polynomial)) {
            return false;
        }
        return Arrays.areEqual(this.exponents, ((GF2Polynomial) obj).exponents);
    }

    public int hashCode() {
        return Arrays.hashCode(this.exponents);
    }
}
