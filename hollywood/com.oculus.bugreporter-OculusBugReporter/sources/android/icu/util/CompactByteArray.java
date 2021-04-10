package android.icu.util;

import android.icu.impl.Utility;

@Deprecated
public final class CompactByteArray implements Cloneable {
    private static final int BLOCKCOUNT = 128;
    private static final int BLOCKMASK = 127;
    private static final int BLOCKSHIFT = 7;
    private static final int INDEXCOUNT = 512;
    private static final int INDEXSHIFT = 9;
    @Deprecated
    public static final int UNICODECOUNT = 65536;
    byte defaultValue;
    private int[] hashes;
    private char[] indices;
    private boolean isCompact;
    private byte[] values;

    @Deprecated
    public CompactByteArray() {
        this((byte) 0);
    }

    @Deprecated
    public CompactByteArray(byte defaultValue2) {
        this.values = new byte[65536];
        this.indices = new char[512];
        this.hashes = new int[512];
        for (int i = 0; i < 65536; i++) {
            this.values[i] = defaultValue2;
        }
        for (int i2 = 0; i2 < 512; i2++) {
            this.indices[i2] = (char) (i2 << 7);
            this.hashes[i2] = 0;
        }
        this.isCompact = false;
        this.defaultValue = defaultValue2;
    }

    @Deprecated
    public CompactByteArray(char[] indexArray, byte[] newValues) {
        if (indexArray.length == 512) {
            for (int i = 0; i < 512; i++) {
                if (indexArray[i] >= newValues.length + 128) {
                    throw new IllegalArgumentException("Index out of bounds.");
                }
            }
            this.indices = indexArray;
            this.values = newValues;
            this.isCompact = true;
            return;
        }
        throw new IllegalArgumentException("Index out of bounds.");
    }

    @Deprecated
    public CompactByteArray(String indexArray, String valueArray) {
        this(Utility.RLEStringToCharArray(indexArray), Utility.RLEStringToByteArray(valueArray));
    }

    @Deprecated
    public byte elementAt(char index) {
        return this.values[(this.indices[index >> 7] & 65535) + (index & 127)];
    }

    @Deprecated
    public void setElementAt(char index, byte value) {
        if (this.isCompact) {
            expand();
        }
        this.values[index] = value;
        touchBlock(index >> 7, value);
    }

    @Deprecated
    public void setElementAt(char start, char end, byte value) {
        if (this.isCompact) {
            expand();
        }
        for (int i = start; i <= end; i++) {
            this.values[i] = value;
            touchBlock(i >> 7, value);
        }
    }

    @Deprecated
    public void compact() {
        compact(false);
    }

    /* JADX INFO: Multiple debug info for r3v2 int: [D('newSize' int), D('i' int)] */
    @Deprecated
    public void compact(boolean exhaustive) {
        if (!this.isCompact) {
            int limitCompacted = 0;
            int iBlockStart = 0;
            char iUntouched = 65535;
            int i = 0;
            while (true) {
                char[] cArr = this.indices;
                if (i < cArr.length) {
                    cArr[i] = 65535;
                    boolean touched = blockTouched(i);
                    if (touched || iUntouched == 65535) {
                        int jBlockStart = 0;
                        int j = 0;
                        while (true) {
                            if (j >= limitCompacted) {
                                break;
                            }
                            int[] iArr = this.hashes;
                            if (iArr[i] == iArr[j]) {
                                byte[] bArr = this.values;
                                if (arrayRegionMatches(bArr, iBlockStart, bArr, jBlockStart, 128)) {
                                    this.indices[i] = (char) jBlockStart;
                                    break;
                                }
                            }
                            j++;
                            jBlockStart += 128;
                        }
                        if (this.indices[i] == 65535) {
                            byte[] bArr2 = this.values;
                            System.arraycopy(bArr2, iBlockStart, bArr2, jBlockStart, 128);
                            this.indices[i] = (char) jBlockStart;
                            int[] iArr2 = this.hashes;
                            iArr2[j] = iArr2[i];
                            limitCompacted++;
                            if (!touched) {
                                iUntouched = (char) jBlockStart;
                            }
                        }
                    } else {
                        this.indices[i] = iUntouched;
                    }
                    i++;
                    iBlockStart += 128;
                } else {
                    int i2 = limitCompacted * 128;
                    byte[] result = new byte[i2];
                    System.arraycopy(this.values, 0, result, 0, i2);
                    this.values = result;
                    this.isCompact = true;
                    this.hashes = null;
                    return;
                }
            }
        }
    }

    static final boolean arrayRegionMatches(byte[] source, int sourceStart, byte[] target, int targetStart, int len) {
        int sourceEnd = sourceStart + len;
        int delta = targetStart - sourceStart;
        for (int i = sourceStart; i < sourceEnd; i++) {
            if (source[i] != target[i + delta]) {
                return false;
            }
        }
        return true;
    }

    private final void touchBlock(int i, int value) {
        int[] iArr = this.hashes;
        iArr[i] = (iArr[i] + (value << 1)) | 1;
    }

    private final boolean blockTouched(int i) {
        return this.hashes[i] != 0;
    }

    @Deprecated
    public char[] getIndexArray() {
        return this.indices;
    }

    @Deprecated
    public byte[] getValueArray() {
        return this.values;
    }

    @Deprecated
    public Object clone() {
        try {
            CompactByteArray other = (CompactByteArray) super.clone();
            other.values = (byte[]) this.values.clone();
            other.indices = (char[]) this.indices.clone();
            if (this.hashes != null) {
                other.hashes = (int[]) this.hashes.clone();
            }
            return other;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CompactByteArray other = (CompactByteArray) obj;
        for (int i = 0; i < 65536; i++) {
            if (elementAt((char) i) != other.elementAt((char) i)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public int hashCode() {
        int result = 0;
        int increment = Math.min(3, this.values.length / 16);
        int i = 0;
        while (true) {
            byte[] bArr = this.values;
            if (i >= bArr.length) {
                return result;
            }
            result = (result * 37) + bArr[i];
            i += increment;
        }
    }

    private void expand() {
        if (this.isCompact) {
            this.hashes = new int[512];
            byte[] tempArray = new byte[65536];
            for (int i = 0; i < 65536; i++) {
                byte value = elementAt((char) i);
                tempArray[i] = value;
                touchBlock(i >> 7, value);
            }
            for (int i2 = 0; i2 < 512; i2++) {
                this.indices[i2] = (char) (i2 << 7);
            }
            this.values = null;
            this.values = tempArray;
            this.isCompact = false;
        }
    }
}
