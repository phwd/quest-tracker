package android.icu.impl;

public final class USerializedSet {
    private char[] array = new char[8];
    private int arrayOffset;
    private int bmpLength;
    private int length;

    public final boolean getSet(char[] src, int srcStart) {
        int srcStart2;
        this.array = null;
        this.length = 0;
        this.bmpLength = 0;
        this.arrayOffset = 0;
        int srcStart3 = srcStart + 1;
        this.length = src[srcStart];
        int i = this.length;
        if ((32768 & i) != 0) {
            this.length = i & 32767;
            if (src.length >= srcStart3 + 1 + this.length) {
                srcStart2 = srcStart3 + 1;
                this.bmpLength = src[srcStart3];
            } else {
                this.length = 0;
                throw new IndexOutOfBoundsException();
            }
        } else if (src.length >= srcStart3 + i) {
            this.bmpLength = i;
            srcStart2 = srcStart3;
        } else {
            this.length = 0;
            throw new IndexOutOfBoundsException();
        }
        int srcStart4 = this.length;
        this.array = new char[srcStart4];
        System.arraycopy((Object) src, srcStart2, (Object) this.array, 0, srcStart4);
        return true;
    }

    public final void setToOne(int c) {
        if (1114111 >= c) {
            if (c < 65535) {
                this.length = 2;
                this.bmpLength = 2;
                char[] cArr = this.array;
                cArr[0] = (char) c;
                cArr[1] = (char) (c + 1);
            } else if (c == 65535) {
                this.bmpLength = 1;
                this.length = 3;
                char[] cArr2 = this.array;
                cArr2[0] = 65535;
                cArr2[1] = 1;
                cArr2[2] = 0;
            } else if (c < 1114111) {
                this.bmpLength = 0;
                this.length = 4;
                char[] cArr3 = this.array;
                cArr3[0] = (char) (c >> 16);
                cArr3[1] = (char) c;
                int c2 = c + 1;
                cArr3[2] = (char) (c2 >> 16);
                cArr3[3] = (char) c2;
            } else {
                this.bmpLength = 0;
                this.length = 2;
                char[] cArr4 = this.array;
                cArr4[0] = 16;
                cArr4[1] = 65535;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: int[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v3, resolved type: char[] */
    /* JADX DEBUG: Multi-variable search result rejected for r10v5, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r10v7, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r0v8, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r10v11, resolved type: char */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getRange(int rangeIndex, int[] range) {
        if (rangeIndex < 0) {
            return false;
        }
        if (this.array == null) {
            this.array = new char[8];
        }
        if (range == 0 || range.length < 2) {
            throw new IllegalArgumentException();
        }
        int rangeIndex2 = rangeIndex * 2;
        int i = this.bmpLength;
        if (rangeIndex2 < i) {
            char[] cArr = this.array;
            int rangeIndex3 = rangeIndex2 + 1;
            range[0] = cArr[rangeIndex2];
            if (rangeIndex3 < i) {
                range[1] = cArr[rangeIndex3] - 1;
            } else if (rangeIndex3 < this.length) {
                range[1] = ((cArr[rangeIndex3] << 16) | cArr[rangeIndex3 + 1]) - 1;
            } else {
                range[1] = 1114111;
            }
            return true;
        }
        int rangeIndex4 = (rangeIndex2 - i) * 2;
        int suppLength = this.length - i;
        if (rangeIndex4 >= suppLength) {
            return false;
        }
        int offset = this.arrayOffset + i;
        char[] cArr2 = this.array;
        range[0] = (cArr2[offset + rangeIndex4] << 16) | cArr2[offset + rangeIndex4 + 1];
        int rangeIndex5 = rangeIndex4 + 2;
        if (rangeIndex5 < suppLength) {
            range[1] = ((cArr2[offset + rangeIndex5] << 16) | cArr2[(offset + rangeIndex5) + 1]) - 1;
        } else {
            range[1] = 1114111;
        }
        return true;
    }

    public final boolean contains(int c) {
        if (c > 1114111) {
            return false;
        }
        if (c <= 65535) {
            int i = 0;
            while (i < this.bmpLength && ((char) c) >= this.array[i]) {
                i++;
            }
            if ((i & 1) != 0) {
                return true;
            }
            return false;
        }
        char high = (char) (c >> 16);
        char low = (char) c;
        int i2 = this.bmpLength;
        while (i2 < this.length) {
            char[] cArr = this.array;
            if (high <= cArr[i2] && (high != cArr[i2] || low < cArr[i2 + 1])) {
                break;
            }
            i2 += 2;
        }
        if (((this.bmpLength + i2) & 2) != 0) {
            return true;
        }
        return false;
    }

    public final int countRanges() {
        int i = this.bmpLength;
        return ((i + ((this.length - i) / 2)) + 1) / 2;
    }
}
