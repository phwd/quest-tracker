package android.icu.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Trie2 implements Iterable {
    private static ValueMapper defaultValueMapper = new ValueMapper() {
        /* class android.icu.impl.Trie2.AnonymousClass1 */

        @Override // android.icu.impl.Trie2.ValueMapper
        public int map(int i) {
            return i;
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

    public interface ValueMapper {
        int map(int i);
    }

    /* access modifiers changed from: package-private */
    public enum ValueWidth {
        BITS_16,
        BITS_32
    }

    /* access modifiers changed from: private */
    public static int hashByte(int i, int i2) {
        return (i * 16777619) ^ i2;
    }

    /* access modifiers changed from: private */
    public static int initHash() {
        return -2128831035;
    }

    public abstract int get(int i);

    public abstract int getFromU16SingleLead(char c);

    public static Trie2 createFromSerialized(ByteBuffer byteBuffer) {
        Trie2 trie2;
        ValueWidth valueWidth;
        ByteOrder order = byteBuffer.order();
        try {
            UTrie2Header uTrie2Header = new UTrie2Header();
            uTrie2Header.signature = byteBuffer.getInt();
            int i = uTrie2Header.signature;
            if (i == 845771348) {
                byteBuffer.order(order == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                uTrie2Header.signature = 1416784178;
            } else if (i != 1416784178) {
                throw new IllegalArgumentException("Buffer does not contain a serialized UTrie2");
            }
            uTrie2Header.options = byteBuffer.getChar();
            uTrie2Header.indexLength = byteBuffer.getChar();
            uTrie2Header.shiftedDataLength = byteBuffer.getChar();
            uTrie2Header.index2NullOffset = byteBuffer.getChar();
            uTrie2Header.dataNullOffset = byteBuffer.getChar();
            uTrie2Header.shiftedHighStart = byteBuffer.getChar();
            if ((uTrie2Header.options & 15) <= 1) {
                if ((uTrie2Header.options & 15) == 0) {
                    valueWidth = ValueWidth.BITS_16;
                    trie2 = new Trie2_16();
                } else {
                    valueWidth = ValueWidth.BITS_32;
                    trie2 = new Trie2_32();
                }
                trie2.header = uTrie2Header;
                trie2.indexLength = uTrie2Header.indexLength;
                trie2.dataLength = uTrie2Header.shiftedDataLength << 2;
                trie2.index2NullOffset = uTrie2Header.index2NullOffset;
                trie2.dataNullOffset = uTrie2Header.dataNullOffset;
                trie2.highStart = uTrie2Header.shiftedHighStart << 11;
                trie2.highValueIndex = trie2.dataLength - 4;
                if (valueWidth == ValueWidth.BITS_16) {
                    trie2.highValueIndex += trie2.indexLength;
                }
                int i2 = trie2.indexLength;
                if (valueWidth == ValueWidth.BITS_16) {
                    i2 += trie2.dataLength;
                }
                trie2.index = ICUBinary.getChars(byteBuffer, i2, 0);
                if (valueWidth == ValueWidth.BITS_16) {
                    trie2.data16 = trie2.indexLength;
                } else {
                    trie2.data32 = ICUBinary.getInts(byteBuffer, trie2.dataLength, 0);
                }
                int i3 = AnonymousClass2.$SwitchMap$android$icu$impl$Trie2$ValueWidth[valueWidth.ordinal()];
                if (i3 == 1) {
                    trie2.data32 = null;
                    trie2.initialValue = trie2.index[trie2.dataNullOffset];
                    trie2.errorValue = trie2.index[trie2.data16 + 128];
                } else if (i3 == 2) {
                    trie2.data16 = 0;
                    trie2.initialValue = trie2.data32[trie2.dataNullOffset];
                    trie2.errorValue = trie2.data32[128];
                } else {
                    throw new IllegalArgumentException("UTrie2 serialized format error.");
                }
                return trie2;
            }
            throw new IllegalArgumentException("UTrie2 serialized format error.");
        } finally {
            byteBuffer.order(order);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.Trie2$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$Trie2$ValueWidth = new int[ValueWidth.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                android.icu.impl.Trie2$ValueWidth[] r0 = android.icu.impl.Trie2.ValueWidth.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.Trie2.AnonymousClass2.$SwitchMap$android$icu$impl$Trie2$ValueWidth = r0
                int[] r0 = android.icu.impl.Trie2.AnonymousClass2.$SwitchMap$android$icu$impl$Trie2$ValueWidth     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.impl.Trie2$ValueWidth r1 = android.icu.impl.Trie2.ValueWidth.BITS_16     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.impl.Trie2.AnonymousClass2.$SwitchMap$android$icu$impl$Trie2$ValueWidth     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.impl.Trie2$ValueWidth r1 = android.icu.impl.Trie2.ValueWidth.BITS_32     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.Trie2.AnonymousClass2.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof android.icu.impl.Trie2
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.icu.impl.Trie2 r6 = (android.icu.impl.Trie2) r6
            java.util.Iterator r0 = r6.iterator()
            java.util.Iterator r2 = r5.iterator()
        L_0x0010:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0030
            java.lang.Object r3 = r2.next()
            android.icu.impl.Trie2$Range r3 = (android.icu.impl.Trie2.Range) r3
            boolean r4 = r0.hasNext()
            if (r4 != 0) goto L_0x0023
            return r1
        L_0x0023:
            java.lang.Object r4 = r0.next()
            android.icu.impl.Trie2$Range r4 = (android.icu.impl.Trie2.Range) r4
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0010
            return r1
        L_0x0030:
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x0037
            return r1
        L_0x0037:
            int r0 = r5.errorValue
            int r2 = r6.errorValue
            if (r0 != r2) goto L_0x0046
            int r5 = r5.initialValue
            int r6 = r6.initialValue
            if (r5 == r6) goto L_0x0044
            goto L_0x0046
        L_0x0044:
            r5 = 1
            return r5
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.Trie2.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this.fHash == 0) {
            int initHash = initHash();
            Iterator it = iterator();
            while (it.hasNext()) {
                initHash = hashInt(initHash, ((Range) it.next()).hashCode());
            }
            if (initHash == 0) {
                initHash = 1;
            }
            this.fHash = initHash;
        }
        return this.fHash;
    }

    public static class Range {
        public int endCodePoint;
        public boolean leadSurrogate;
        public int startCodePoint;
        public int value;

        public boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(Range.class)) {
                return false;
            }
            Range range = (Range) obj;
            if (this.startCodePoint == range.startCodePoint && this.endCodePoint == range.endCodePoint && this.value == range.value && this.leadSurrogate == range.leadSurrogate) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Trie2.hashByte(Trie2.hashInt(Trie2.hashUChar32(Trie2.hashUChar32(Trie2.initHash(), this.startCodePoint), this.endCodePoint), this.value), this.leadSurrogate ? 1 : 0);
        }
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return iterator(defaultValueMapper);
    }

    public Iterator iterator(ValueMapper valueMapper) {
        return new Trie2Iterator(valueMapper);
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
    public class Trie2Iterator implements Iterator {
        private boolean doLeadSurrogates = true;
        private boolean doingCodePoints = true;
        private int limitCP;
        private ValueMapper mapper;
        private int nextStart;
        private Range returnValue = new Range();

        Trie2Iterator(ValueMapper valueMapper) {
            this.mapper = valueMapper;
            this.nextStart = 0;
            this.limitCP = 1114112;
            this.doLeadSurrogates = true;
        }

        @Override // java.util.Iterator
        public Range next() {
            int i;
            int i2;
            if (hasNext()) {
                if (this.nextStart >= this.limitCP) {
                    this.doingCodePoints = false;
                    this.nextStart = 55296;
                }
                if (this.doingCodePoints) {
                    i2 = Trie2.this.get(this.nextStart);
                    this.mapper.map(i2);
                    i = Trie2.this.rangeEnd(this.nextStart, this.limitCP, i2);
                    while (i < this.limitCP - 1) {
                        int i3 = i + 1;
                        int i4 = Trie2.this.get(i3);
                        this.mapper.map(i4);
                        if (i4 != i2) {
                            break;
                        }
                        i = Trie2.this.rangeEnd(i3, this.limitCP, i4);
                    }
                } else {
                    i2 = Trie2.this.getFromU16SingleLead((char) this.nextStart);
                    this.mapper.map(i2);
                    i = rangeEndLS((char) this.nextStart);
                    while (i < 56319) {
                        char c = (char) (i + 1);
                        int fromU16SingleLead = Trie2.this.getFromU16SingleLead(c);
                        this.mapper.map(fromU16SingleLead);
                        if (fromU16SingleLead != i2) {
                            break;
                        }
                        i = rangeEndLS(c);
                    }
                }
                Range range = this.returnValue;
                range.startCodePoint = this.nextStart;
                range.endCodePoint = i;
                range.value = i2;
                range.leadSurrogate = !this.doingCodePoints;
                this.nextStart = i + 1;
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

        private int rangeEndLS(char c) {
            int i;
            int fromU16SingleLead;
            if (c >= 56319) {
                return 56319;
            }
            int fromU16SingleLead2 = Trie2.this.getFromU16SingleLead(c);
            char c2 = c;
            do {
                i = (c2 == 1 ? 1 : 0) + 1;
                if (i > 56319) {
                    break;
                }
                fromU16SingleLead = Trie2.this.getFromU16SingleLead((char) i);
                c2 = i;
            } while (fromU16SingleLead == fromU16SingleLead2);
            return i - 1;
        }
    }

    /* access modifiers changed from: package-private */
    public int rangeEnd(int i, int i2, int i3) {
        int min = Math.min(this.highStart, i2);
        do {
            i++;
            if (i >= min) {
                break;
            }
        } while (get(i) == i3);
        if (i >= this.highStart) {
            i = i2;
        }
        return i - 1;
    }

    /* access modifiers changed from: private */
    public static int hashUChar32(int i, int i2) {
        return hashByte(hashByte(hashByte(i, i2 & 255), (i2 >> 8) & 255), i2 >> 16);
    }

    /* access modifiers changed from: private */
    public static int hashInt(int i, int i2) {
        return hashByte(hashByte(hashByte(hashByte(i, i2 & 255), (i2 >> 8) & 255), (i2 >> 16) & 255), (i2 >> 24) & 255);
    }
}
