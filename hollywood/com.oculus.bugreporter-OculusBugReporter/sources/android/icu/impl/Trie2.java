package android.icu.impl;

import android.icu.text.UTF16;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Trie2 implements Iterable<Range> {
    static final int UNEWTRIE2_INDEX_1_LENGTH = 544;
    static final int UNEWTRIE2_INDEX_GAP_LENGTH = 576;
    static final int UNEWTRIE2_INDEX_GAP_OFFSET = 2080;
    static final int UNEWTRIE2_MAX_DATA_LENGTH = 1115264;
    static final int UNEWTRIE2_MAX_INDEX_2_LENGTH = 35488;
    static final int UTRIE2_BAD_UTF8_DATA_OFFSET = 128;
    static final int UTRIE2_CP_PER_INDEX_1_ENTRY = 2048;
    static final int UTRIE2_DATA_BLOCK_LENGTH = 32;
    static final int UTRIE2_DATA_GRANULARITY = 4;
    static final int UTRIE2_DATA_MASK = 31;
    static final int UTRIE2_DATA_START_OFFSET = 192;
    static final int UTRIE2_INDEX_1_OFFSET = 2112;
    static final int UTRIE2_INDEX_2_BLOCK_LENGTH = 64;
    static final int UTRIE2_INDEX_2_BMP_LENGTH = 2080;
    static final int UTRIE2_INDEX_2_MASK = 63;
    static final int UTRIE2_INDEX_2_OFFSET = 0;
    static final int UTRIE2_INDEX_SHIFT = 2;
    static final int UTRIE2_LSCP_INDEX_2_LENGTH = 32;
    static final int UTRIE2_LSCP_INDEX_2_OFFSET = 2048;
    static final int UTRIE2_MAX_INDEX_1_LENGTH = 512;
    static final int UTRIE2_OMITTED_BMP_INDEX_1_LENGTH = 32;
    static final int UTRIE2_OPTIONS_VALUE_BITS_MASK = 15;
    static final int UTRIE2_SHIFT_1 = 11;
    static final int UTRIE2_SHIFT_1_2 = 6;
    static final int UTRIE2_SHIFT_2 = 5;
    static final int UTRIE2_UTF8_2B_INDEX_2_LENGTH = 32;
    static final int UTRIE2_UTF8_2B_INDEX_2_OFFSET = 2080;
    private static ValueMapper defaultValueMapper = new ValueMapper() {
        /* class android.icu.impl.Trie2.AnonymousClass1 */

        @Override // android.icu.impl.Trie2.ValueMapper
        public int map(int in) {
            return in;
        }
    };
    int data16;
    int[] data32;
    int dataLength;
    int dataNullOffset;
    int errorValue;
    int fHash;
    UTrie2Header header;
    int highStart;
    int highValueIndex;
    char[] index;
    int index2NullOffset;
    int indexLength;
    int initialValue;

    public static class CharSequenceValues {
        public int codePoint;
        public int index;
        public int value;
    }

    public interface ValueMapper {
        int map(int i);
    }

    /* access modifiers changed from: package-private */
    public enum ValueWidth {
        BITS_16,
        BITS_32
    }

    public abstract int get(int i);

    public abstract int getFromU16SingleLead(char c);

    public static Trie2 createFromSerialized(ByteBuffer bytes) throws IOException {
        Trie2 This;
        ValueWidth width;
        ByteOrder outerByteOrder = bytes.order();
        try {
            UTrie2Header header2 = new UTrie2Header();
            header2.signature = bytes.getInt();
            int i = header2.signature;
            if (i == 845771348) {
                bytes.order(outerByteOrder == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                header2.signature = 1416784178;
            } else if (i != 1416784178) {
                throw new IllegalArgumentException("Buffer does not contain a serialized UTrie2");
            }
            header2.options = bytes.getChar();
            header2.indexLength = bytes.getChar();
            header2.shiftedDataLength = bytes.getChar();
            header2.index2NullOffset = bytes.getChar();
            header2.dataNullOffset = bytes.getChar();
            header2.shiftedHighStart = bytes.getChar();
            if ((header2.options & 15) <= 1) {
                if ((header2.options & 15) == 0) {
                    width = ValueWidth.BITS_16;
                    This = new Trie2_16();
                } else {
                    width = ValueWidth.BITS_32;
                    This = new Trie2_32();
                }
                This.header = header2;
                This.indexLength = header2.indexLength;
                This.dataLength = header2.shiftedDataLength << 2;
                This.index2NullOffset = header2.index2NullOffset;
                This.dataNullOffset = header2.dataNullOffset;
                This.highStart = header2.shiftedHighStart << 11;
                This.highValueIndex = This.dataLength - 4;
                if (width == ValueWidth.BITS_16) {
                    This.highValueIndex += This.indexLength;
                }
                int indexArraySize = This.indexLength;
                if (width == ValueWidth.BITS_16) {
                    indexArraySize += This.dataLength;
                }
                This.index = ICUBinary.getChars(bytes, indexArraySize, 0);
                if (width == ValueWidth.BITS_16) {
                    This.data16 = This.indexLength;
                } else {
                    This.data32 = ICUBinary.getInts(bytes, This.dataLength, 0);
                }
                int i2 = AnonymousClass2.$SwitchMap$android$icu$impl$Trie2$ValueWidth[width.ordinal()];
                if (i2 == 1) {
                    This.data32 = null;
                    This.initialValue = This.index[This.dataNullOffset];
                    This.errorValue = This.index[This.data16 + 128];
                } else if (i2 == 2) {
                    This.data16 = 0;
                    This.initialValue = This.data32[This.dataNullOffset];
                    This.errorValue = This.data32[128];
                } else {
                    throw new IllegalArgumentException("UTrie2 serialized format error.");
                }
                return This;
            }
            throw new IllegalArgumentException("UTrie2 serialized format error.");
        } finally {
            bytes.order(outerByteOrder);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.Trie2$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$Trie2$ValueWidth = new int[ValueWidth.values().length];

        static {
            try {
                $SwitchMap$android$icu$impl$Trie2$ValueWidth[ValueWidth.BITS_16.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$impl$Trie2$ValueWidth[ValueWidth.BITS_32.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static int getVersion(InputStream is, boolean littleEndianOk) throws IOException {
        if (is.markSupported()) {
            is.mark(4);
            byte[] sig = new byte[4];
            int read = is.read(sig);
            is.reset();
            if (read != sig.length) {
                return 0;
            }
            if (sig[0] == 84 && sig[1] == 114 && sig[2] == 105 && sig[3] == 101) {
                return 1;
            }
            if (sig[0] == 84 && sig[1] == 114 && sig[2] == 105 && sig[3] == 50) {
                return 2;
            }
            if (littleEndianOk) {
                if (sig[0] == 101 && sig[1] == 105 && sig[2] == 114 && sig[3] == 84) {
                    return 1;
                }
                if (sig[0] == 50 && sig[1] == 105 && sig[2] == 114 && sig[3] == 84) {
                    return 2;
                }
                return 0;
            }
            return 0;
        }
        throw new IllegalArgumentException("Input stream must support mark().");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof android.icu.impl.Trie2
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = r8
            android.icu.impl.Trie2 r0 = (android.icu.impl.Trie2) r0
            java.util.Iterator r2 = r0.iterator()
            java.util.Iterator r3 = r7.iterator()
        L_0x0011:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0032
            java.lang.Object r4 = r3.next()
            android.icu.impl.Trie2$Range r4 = (android.icu.impl.Trie2.Range) r4
            boolean r5 = r2.hasNext()
            if (r5 != 0) goto L_0x0024
            return r1
        L_0x0024:
            java.lang.Object r5 = r2.next()
            android.icu.impl.Trie2$Range r5 = (android.icu.impl.Trie2.Range) r5
            boolean r6 = r4.equals(r5)
            if (r6 != 0) goto L_0x0031
            return r1
        L_0x0031:
            goto L_0x0011
        L_0x0032:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0039
            return r1
        L_0x0039:
            int r3 = r7.errorValue
            int r4 = r0.errorValue
            if (r3 != r4) goto L_0x0048
            int r3 = r7.initialValue
            int r4 = r0.initialValue
            if (r3 == r4) goto L_0x0046
            goto L_0x0048
        L_0x0046:
            r1 = 1
            return r1
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.Trie2.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this.fHash == 0) {
            int hash = initHash();
            Iterator<Range> it = iterator();
            while (it.hasNext()) {
                hash = hashInt(hash, it.next().hashCode());
            }
            if (hash == 0) {
                hash = 1;
            }
            this.fHash = hash;
        }
        return this.fHash;
    }

    public static class Range {
        public int endCodePoint;
        public boolean leadSurrogate;
        public int startCodePoint;
        public int value;

        public boolean equals(Object other) {
            if (other == null || !other.getClass().equals(getClass())) {
                return false;
            }
            Range tother = (Range) other;
            if (this.startCodePoint == tother.startCodePoint && this.endCodePoint == tother.endCodePoint && this.value == tother.value && this.leadSurrogate == tother.leadSurrogate) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Trie2.hashByte(Trie2.hashInt(Trie2.hashUChar32(Trie2.hashUChar32(Trie2.initHash(), this.startCodePoint), this.endCodePoint), this.value), this.leadSurrogate ? 1 : 0);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Range> iterator() {
        return iterator(defaultValueMapper);
    }

    public Iterator<Range> iterator(ValueMapper mapper) {
        return new Trie2Iterator(mapper);
    }

    public Iterator<Range> iteratorForLeadSurrogate(char lead, ValueMapper mapper) {
        return new Trie2Iterator(lead, mapper);
    }

    public Iterator<Range> iteratorForLeadSurrogate(char lead) {
        return new Trie2Iterator(lead, defaultValueMapper);
    }

    /* access modifiers changed from: protected */
    public int serializeHeader(DataOutputStream dos) throws IOException {
        dos.writeInt(this.header.signature);
        dos.writeShort(this.header.options);
        dos.writeShort(this.header.indexLength);
        dos.writeShort(this.header.shiftedDataLength);
        dos.writeShort(this.header.index2NullOffset);
        dos.writeShort(this.header.dataNullOffset);
        dos.writeShort(this.header.shiftedHighStart);
        int bytesWritten = 0 + 16;
        for (int i = 0; i < this.header.indexLength; i++) {
            dos.writeChar(this.index[i]);
        }
        return bytesWritten + this.header.indexLength;
    }

    public CharSequenceIterator charSequenceIterator(CharSequence text, int index2) {
        return new CharSequenceIterator(text, index2);
    }

    public class CharSequenceIterator implements Iterator<CharSequenceValues> {
        private CharSequenceValues fResults = new CharSequenceValues();
        private int index;
        private CharSequence text;
        private int textLength;

        CharSequenceIterator(CharSequence t, int index2) {
            this.text = t;
            this.textLength = this.text.length();
            set(index2);
        }

        public void set(int i) {
            if (i < 0 || i > this.textLength) {
                throw new IndexOutOfBoundsException();
            }
            this.index = i;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.index < this.textLength;
        }

        public final boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.Iterator
        public CharSequenceValues next() {
            int c = Character.codePointAt(this.text, this.index);
            int val = Trie2.this.get(c);
            CharSequenceValues charSequenceValues = this.fResults;
            int i = this.index;
            charSequenceValues.index = i;
            charSequenceValues.codePoint = c;
            charSequenceValues.value = val;
            this.index = i + 1;
            if (c >= 65536) {
                this.index++;
            }
            return this.fResults;
        }

        public CharSequenceValues previous() {
            int c = Character.codePointBefore(this.text, this.index);
            int val = Trie2.this.get(c);
            this.index--;
            if (c >= 65536) {
                this.index--;
            }
            CharSequenceValues charSequenceValues = this.fResults;
            charSequenceValues.index = this.index;
            charSequenceValues.codePoint = c;
            charSequenceValues.value = val;
            return charSequenceValues;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Trie2.CharSequenceIterator does not support remove().");
        }
    }

    /* access modifiers changed from: package-private */
    public static class UTrie2Header {
        int dataNullOffset;
        int index2NullOffset;
        int indexLength;
        int options;
        int shiftedDataLength;
        int shiftedHighStart;
        int signature;

        UTrie2Header() {
        }
    }

    /* access modifiers changed from: package-private */
    public class Trie2Iterator implements Iterator<Range> {
        private boolean doLeadSurrogates = true;
        private boolean doingCodePoints = true;
        private int limitCP;
        private ValueMapper mapper;
        private int nextStart;
        private Range returnValue = new Range();

        Trie2Iterator(ValueMapper vm) {
            this.mapper = vm;
            this.nextStart = 0;
            this.limitCP = 1114112;
            this.doLeadSurrogates = true;
        }

        Trie2Iterator(char leadSurrogate, ValueMapper vm) {
            if (leadSurrogate < 55296 || leadSurrogate > 56319) {
                throw new IllegalArgumentException("Bad lead surrogate value.");
            }
            this.mapper = vm;
            this.nextStart = (leadSurrogate - 55232) << 10;
            this.limitCP = this.nextStart + 1024;
            this.doLeadSurrogates = false;
        }

        @Override // java.util.Iterator
        public Range next() {
            int mappedVal;
            int endOfRange;
            if (hasNext()) {
                if (this.nextStart >= this.limitCP) {
                    this.doingCodePoints = false;
                    this.nextStart = 55296;
                }
                if (this.doingCodePoints) {
                    int val = Trie2.this.get(this.nextStart);
                    mappedVal = this.mapper.map(val);
                    endOfRange = Trie2.this.rangeEnd(this.nextStart, this.limitCP, val);
                    while (endOfRange < this.limitCP - 1) {
                        int val2 = Trie2.this.get(endOfRange + 1);
                        if (this.mapper.map(val2) != mappedVal) {
                            break;
                        }
                        endOfRange = Trie2.this.rangeEnd(endOfRange + 1, this.limitCP, val2);
                    }
                } else {
                    mappedVal = this.mapper.map(Trie2.this.getFromU16SingleLead((char) this.nextStart));
                    endOfRange = rangeEndLS((char) this.nextStart);
                    while (endOfRange < 56319) {
                        if (this.mapper.map(Trie2.this.getFromU16SingleLead((char) (endOfRange + 1))) != mappedVal) {
                            break;
                        }
                        endOfRange = rangeEndLS((char) (endOfRange + 1));
                    }
                }
                Range range = this.returnValue;
                range.startCodePoint = this.nextStart;
                range.endCodePoint = endOfRange;
                range.value = mappedVal;
                range.leadSurrogate = !this.doingCodePoints;
                this.nextStart = endOfRange + 1;
                return range;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return (this.doingCodePoints && (this.doLeadSurrogates || this.nextStart < this.limitCP)) || this.nextStart < 56320;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private int rangeEndLS(char startingLS) {
            if (startingLS >= 56319) {
                return UTF16.LEAD_SURROGATE_MAX_VALUE;
            }
            int val = Trie2.this.getFromU16SingleLead(startingLS);
            int c = startingLS + 1;
            while (c <= 56319 && Trie2.this.getFromU16SingleLead((char) c) == val) {
                c++;
            }
            return c - 1;
        }
    }

    /* access modifiers changed from: package-private */
    public int rangeEnd(int start, int limitp, int val) {
        int limit = Math.min(this.highStart, limitp);
        int c = start + 1;
        while (c < limit && get(c) == val) {
            c++;
        }
        if (c >= this.highStart) {
            c = limitp;
        }
        return c - 1;
    }

    /* access modifiers changed from: private */
    public static int initHash() {
        return -2128831035;
    }

    /* access modifiers changed from: private */
    public static int hashByte(int h, int b) {
        return (h * 16777619) ^ b;
    }

    /* access modifiers changed from: private */
    public static int hashUChar32(int h, int c) {
        return hashByte(hashByte(hashByte(h, c & 255), (c >> 8) & 255), c >> 16);
    }

    /* access modifiers changed from: private */
    public static int hashInt(int h, int i) {
        return hashByte(hashByte(hashByte(hashByte(h, i & 255), (i >> 8) & 255), (i >> 16) & 255), (i >> 24) & 255);
    }
}
