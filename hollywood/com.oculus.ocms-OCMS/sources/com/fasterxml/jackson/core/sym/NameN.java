package com.fasterxml.jackson.core.sym;

public final class NameN extends Name {
    final int mQuadLen;
    final int[] mQuads;

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i) {
        return false;
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int i, int i2) {
        return false;
    }

    NameN(String str, int i, int[] iArr, int i2) {
        super(str, i);
        if (i2 >= 3) {
            this.mQuads = iArr;
            this.mQuadLen = i2;
            return;
        }
        throw new IllegalArgumentException("Qlen must >= 3");
    }

    @Override // com.fasterxml.jackson.core.sym.Name
    public boolean equals(int[] iArr, int i) {
        if (i != this.mQuadLen) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != this.mQuads[i2]) {
                return false;
            }
        }
        return true;
    }
}
