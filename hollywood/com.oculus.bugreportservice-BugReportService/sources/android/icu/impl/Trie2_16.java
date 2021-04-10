package android.icu.impl;

import java.nio.ByteBuffer;

public final class Trie2_16 extends Trie2 {
    Trie2_16() {
    }

    public static Trie2_16 createFromSerialized(ByteBuffer byteBuffer) {
        return (Trie2_16) Trie2.createFromSerialized(byteBuffer);
    }

    @Override // android.icu.impl.Trie2
    public final int get(int i) {
        if (i >= 0) {
            if (i < 55296 || (i > 56319 && i <= 65535)) {
                char[] cArr = this.index;
                return cArr[(cArr[i >> 5] << 2) + (i & 31)];
            } else if (i <= 65535) {
                char[] cArr2 = this.index;
                return cArr2[(cArr2[((i - 55296) >> 5) + 2048] << 2) + (i & 31)];
            } else if (i < this.highStart) {
                char[] cArr3 = this.index;
                return cArr3[(cArr3[cArr3[(i >> 11) + 2080] + ((i >> 5) & 63)] << 2) + (i & 31)];
            } else if (i <= 1114111) {
                return this.index[this.highValueIndex];
            }
        }
        return this.errorValue;
    }

    @Override // android.icu.impl.Trie2
    public int getFromU16SingleLead(char c) {
        char[] cArr = this.index;
        return cArr[(cArr[c >> 5] << 2) + (c & 31)];
    }

    public int getSerializedLength() {
        return ((this.header.indexLength + this.dataLength) * 2) + 16;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.impl.Trie2
    public int rangeEnd(int i, int i2, int i3) {
        char c;
        loop0:
        while (true) {
            if (i >= i2) {
                break;
            }
            char c2 = 2048;
            if (i < 55296 || (i > 56319 && i <= 65535)) {
                c2 = 0;
                c = this.index[i >> 5];
            } else if (i < 65535) {
                c = this.index[((i - 55296) >> 5) + 2048];
            } else if (i < this.highStart) {
                char[] cArr = this.index;
                c2 = cArr[(i >> 11) + 2080];
                c = cArr[((i >> 5) & 63) + c2];
            } else if (i3 == this.index[this.highValueIndex]) {
                i = i2;
            }
            int i4 = c << 2;
            if (c2 == this.index2NullOffset) {
                if (i3 != this.initialValue) {
                    break;
                }
                i += 2048;
            } else if (i4 != this.dataNullOffset) {
                int i5 = (i & 31) + i4;
                int i6 = i4 + 32;
                for (int i7 = i5; i7 < i6; i7++) {
                    if (this.index[i7] != i3) {
                        i += i7 - i5;
                        break loop0;
                    }
                }
                i += i6 - i5;
            } else if (i3 != this.initialValue) {
                break;
            } else {
                i += 32;
            }
        }
        if (i > i2) {
            i = i2;
        }
        return i - 1;
    }
}
