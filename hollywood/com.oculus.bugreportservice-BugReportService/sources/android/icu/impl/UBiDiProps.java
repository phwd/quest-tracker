package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.Trie2;
import android.icu.text.UnicodeSet;
import android.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

public final class UBiDiProps {
    public static final UBiDiProps INSTANCE;
    private int[] indexes;
    private byte[] jgArray;
    private byte[] jgArray2;
    private int[] mirrors;
    private Trie2_16 trie;

    private static final int getClassFromProps(int i) {
        return i & 31;
    }

    private static final boolean getFlagFromProps(int i, int i2) {
        return ((i >> i2) & 1) != 0;
    }

    private static final int getMirrorCodePoint(int i) {
        return i & 2097151;
    }

    private UBiDiProps() {
        readData(ICUBinary.getData("ubidi.icu"));
    }

    private void readData(ByteBuffer byteBuffer) {
        ICUBinary.readHeader(byteBuffer, 1114195049, new IsAcceptable());
        int i = byteBuffer.getInt();
        if (i >= 16) {
            this.indexes = new int[i];
            this.indexes[0] = i;
            for (int i2 = 1; i2 < i; i2++) {
                this.indexes[i2] = byteBuffer.getInt();
            }
            this.trie = Trie2_16.createFromSerialized(byteBuffer);
            int i3 = this.indexes[2];
            int serializedLength = this.trie.getSerializedLength();
            if (serializedLength <= i3) {
                ICUBinary.skipBytes(byteBuffer, i3 - serializedLength);
                int i4 = this.indexes[3];
                if (i4 > 0) {
                    this.mirrors = ICUBinary.getInts(byteBuffer, i4, 0);
                }
                int[] iArr = this.indexes;
                this.jgArray = new byte[(iArr[5] - iArr[4])];
                byteBuffer.get(this.jgArray);
                int[] iArr2 = this.indexes;
                this.jgArray2 = new byte[(iArr2[7] - iArr2[6])];
                byteBuffer.get(this.jgArray2);
                return;
            }
            throw new IOException("ubidi.icu: not enough bytes for the trie");
        }
        throw new IOException("indexes[0] too small in ubidi.icu");
    }

    /* access modifiers changed from: private */
    public static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] bArr) {
            return bArr[0] == 2;
        }
    }

    public final void addPropertyStarts(UnicodeSet unicodeSet) {
        Iterator it = this.trie.iterator();
        while (it.hasNext()) {
            Trie2.Range range = (Trie2.Range) it.next();
            if (range.leadSurrogate) {
                break;
            }
            unicodeSet.add(range.startCodePoint);
        }
        int i = this.indexes[3];
        for (int i2 = 0; i2 < i; i2++) {
            int mirrorCodePoint = getMirrorCodePoint(this.mirrors[i2]);
            unicodeSet.add(mirrorCodePoint, mirrorCodePoint + 1);
        }
        int[] iArr = this.indexes;
        int i3 = iArr[4];
        int i4 = iArr[5];
        byte[] bArr = this.jgArray;
        while (true) {
            int i5 = i4 - i3;
            byte b = 0;
            int i6 = i3;
            for (int i7 = 0; i7 < i5; i7++) {
                byte b2 = bArr[i7];
                if (b2 != b) {
                    unicodeSet.add(i6);
                    b = b2;
                }
                i6++;
            }
            if (b != 0) {
                unicodeSet.add(i4);
            }
            int[] iArr2 = this.indexes;
            if (i4 == iArr2[5]) {
                int i8 = iArr2[6];
                int i9 = iArr2[7];
                bArr = this.jgArray2;
                i3 = i8;
                i4 = i9;
            } else {
                return;
            }
        }
    }

    public final int getMaxValue(int i) {
        int i2 = this.indexes[15];
        if (i == 4096) {
            return i2 & 31;
        }
        if (i == 4117) {
            return (i2 & 768) >> 8;
        }
        if (i == 4102) {
            return (i2 & 16711680) >> 16;
        }
        if (i != 4103) {
            return -1;
        }
        return (i2 & 224) >> 5;
    }

    public final int getClass(int i) {
        return getClassFromProps(this.trie.get(i));
    }

    public final boolean isMirrored(int i) {
        return getFlagFromProps(this.trie.get(i), 12);
    }

    public final boolean isBidiControl(int i) {
        return getFlagFromProps(this.trie.get(i), 11);
    }

    public final boolean isJoinControl(int i) {
        return getFlagFromProps(this.trie.get(i), 10);
    }

    public final int getJoiningType(int i) {
        return (this.trie.get(i) & 224) >> 5;
    }

    public final int getJoiningGroup(int i) {
        byte b;
        int[] iArr = this.indexes;
        int i2 = iArr[4];
        int i3 = iArr[5];
        if (i2 > i || i >= i3) {
            int[] iArr2 = this.indexes;
            int i4 = iArr2[6];
            int i5 = iArr2[7];
            if (i4 > i || i >= i5) {
                return 0;
            }
            b = this.jgArray2[i - i4];
        } else {
            b = this.jgArray[i - i2];
        }
        return b & 255;
    }

    public final int getPairedBracketType(int i) {
        return (this.trie.get(i) & 768) >> 8;
    }

    static {
        try {
            INSTANCE = new UBiDiProps();
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }
}
