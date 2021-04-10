package android.icu.util;

import android.icu.impl.ICUBinary;
import android.icu.impl.Normalizer2Impl;
import android.icu.util.CodePointMap;
import android.support.v4.view.PointerIconCompat;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class CodePointTrie extends CodePointMap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ASCII_LIMIT = 128;
    private static final int BMP_INDEX_LENGTH = 1024;
    static final int CP_PER_INDEX_2_ENTRY = 512;
    private static final int ERROR_VALUE_NEG_DATA_OFFSET = 1;
    static final int FAST_DATA_BLOCK_LENGTH = 64;
    private static final int FAST_DATA_MASK = 63;
    static final int FAST_SHIFT = 6;
    private static final int HIGH_VALUE_NEG_DATA_OFFSET = 2;
    static final int INDEX_2_BLOCK_LENGTH = 32;
    static final int INDEX_2_MASK = 31;
    static final int INDEX_3_BLOCK_LENGTH = 32;
    private static final int INDEX_3_MASK = 31;
    private static final int MAX_UNICODE = 1114111;
    static final int NO_DATA_NULL_OFFSET = 1048575;
    static final int NO_INDEX3_NULL_OFFSET = 32767;
    private static final int OMITTED_BMP_INDEX_1_LENGTH = 4;
    private static final int OPTIONS_DATA_LENGTH_MASK = 61440;
    private static final int OPTIONS_DATA_NULL_OFFSET_MASK = 3840;
    private static final int OPTIONS_RESERVED_MASK = 56;
    private static final int OPTIONS_VALUE_BITS_MASK = 7;
    private static final int SHIFT_1 = 14;
    static final int SHIFT_1_2 = 5;
    private static final int SHIFT_2 = 9;
    static final int SHIFT_2_3 = 5;
    static final int SHIFT_3 = 4;
    static final int SMALL_DATA_BLOCK_LENGTH = 16;
    static final int SMALL_DATA_MASK = 15;
    private static final int SMALL_INDEX_LENGTH = 64;
    static final int SMALL_LIMIT = 4096;
    private static final int SMALL_MAX = 4095;
    private final int[] ascii;
    @Deprecated
    protected final Data data;
    @Deprecated
    protected final int dataLength;
    private final int dataNullOffset;
    @Deprecated
    protected final int highStart;
    private final char[] index;
    private final int index3NullOffset;
    private final int nullValue;

    public enum Type {
        FAST,
        SMALL
    }

    public enum ValueWidth {
        BITS_16,
        BITS_32,
        BITS_8
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int cpIndex(int i);

    public abstract Type getType();

    /* synthetic */ CodePointTrie(char[] x0, Data x1, int x2, int x3, int x4, AnonymousClass1 x5) {
        this(x0, x1, x2, x3, x4);
    }

    private CodePointTrie(char[] index2, Data data2, int highStart2, int index3NullOffset2, int dataNullOffset2) {
        this.ascii = new int[128];
        this.index = index2;
        this.data = data2;
        this.dataLength = data2.getDataLength();
        this.highStart = highStart2;
        this.index3NullOffset = index3NullOffset2;
        this.dataNullOffset = dataNullOffset2;
        for (int c = 0; c < 128; c++) {
            this.ascii[c] = data2.getFromIndex(c);
        }
        int nullValueOffset = dataNullOffset2;
        int i = this.dataLength;
        this.nullValue = data2.getFromIndex(nullValueOffset >= i ? i - 2 : nullValueOffset);
    }

    public static CodePointTrie fromBinary(Type type, ValueWidth valueWidth, ByteBuffer bytes) {
        Throwable th;
        Type actualType;
        ValueWidth actualValueWidth;
        Type type2;
        ValueWidth valueWidth2;
        int actualLength;
        CodePointTrie codePointTrie;
        CodePointTrie codePointTrie2;
        CodePointTrie codePointTrie3;
        ByteOrder outerByteOrder = bytes.order();
        try {
            if (bytes.remaining() >= 16) {
                int signature = bytes.getInt();
                if (signature == 862548564) {
                    bytes.order(outerByteOrder == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                    signature = 1416784179;
                } else if (signature != 1416784179) {
                    throw new ICUUncheckedIOException("Buffer does not contain a serialized CodePointTrie");
                }
                int options = bytes.getChar();
                int indexLength = bytes.getChar();
                int dataLength2 = bytes.getChar();
                int index3NullOffset2 = bytes.getChar();
                int dataNullOffset2 = bytes.getChar();
                int shiftedHighStart = bytes.getChar();
                int typeInt = (options >> 6) & 3;
                if (typeInt == 0) {
                    actualType = Type.FAST;
                } else if (typeInt == 1) {
                    actualType = Type.SMALL;
                } else {
                    throw new ICUUncheckedIOException("CodePointTrie data header has an unsupported type");
                }
                int valueWidthInt = options & 7;
                if (valueWidthInt == 0) {
                    actualValueWidth = ValueWidth.BITS_16;
                } else if (valueWidthInt == 1) {
                    actualValueWidth = ValueWidth.BITS_32;
                } else if (valueWidthInt == 2) {
                    actualValueWidth = ValueWidth.BITS_8;
                } else {
                    throw new ICUUncheckedIOException("CodePointTrie data header has an unsupported value width");
                }
                if ((options & 56) == 0) {
                    if (type == null) {
                        type2 = actualType;
                    } else {
                        type2 = type;
                    }
                    if (valueWidth == null) {
                        valueWidth2 = actualValueWidth;
                    } else {
                        valueWidth2 = valueWidth;
                    }
                    if (type2 == actualType) {
                        if (valueWidth2 == actualValueWidth) {
                            int dataLength3 = dataLength2 | ((options & OPTIONS_DATA_LENGTH_MASK) << 4);
                            int dataNullOffset3 = dataNullOffset2 | ((options & OPTIONS_DATA_NULL_OFFSET_MASK) << 8);
                            int highStart2 = shiftedHighStart << 9;
                            int actualLength2 = indexLength * 2;
                            try {
                                if (valueWidth2 == ValueWidth.BITS_16) {
                                    actualLength = actualLength2 + (dataLength3 * 2);
                                } else if (valueWidth2 == ValueWidth.BITS_32) {
                                    actualLength = actualLength2 + (dataLength3 * 4);
                                } else {
                                    actualLength = actualLength2 + dataLength3;
                                }
                                if (bytes.remaining() >= actualLength) {
                                    char[] index2 = ICUBinary.getChars(bytes, indexLength, 0);
                                    int i = AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth[valueWidth2.ordinal()];
                                    if (i == 1) {
                                        char[] data16 = ICUBinary.getChars(bytes, dataLength3, 0);
                                        if (type2 == Type.FAST) {
                                            codePointTrie = new Fast16(index2, data16, highStart2, index3NullOffset2, dataNullOffset3);
                                        } else {
                                            codePointTrie = new Small16(index2, data16, highStart2, index3NullOffset2, dataNullOffset3);
                                        }
                                        bytes.order(outerByteOrder);
                                        return codePointTrie;
                                    } else if (i == 2) {
                                        int[] data32 = ICUBinary.getInts(bytes, dataLength3, 0);
                                        if (type2 == Type.FAST) {
                                            codePointTrie2 = new Fast32(index2, data32, highStart2, index3NullOffset2, dataNullOffset3);
                                        } else {
                                            codePointTrie2 = new Small32(index2, data32, highStart2, index3NullOffset2, dataNullOffset3);
                                        }
                                        bytes.order(outerByteOrder);
                                        return codePointTrie2;
                                    } else if (i == 3) {
                                        byte[] data8 = ICUBinary.getBytes(bytes, dataLength3, 0);
                                        try {
                                            if (type2 == Type.FAST) {
                                                codePointTrie3 = new Fast8(index2, data8, highStart2, index3NullOffset2, dataNullOffset3);
                                            } else {
                                                codePointTrie3 = new Small8(index2, data8, highStart2, index3NullOffset2, dataNullOffset3);
                                            }
                                            bytes.order(outerByteOrder);
                                            return codePointTrie3;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            bytes.order(outerByteOrder);
                                            throw th;
                                        }
                                    } else {
                                        throw new AssertionError((Object) "should be unreachable");
                                    }
                                } else {
                                    throw new ICUUncheckedIOException("Buffer too short for the CodePointTrie data");
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                bytes.order(outerByteOrder);
                                throw th;
                            }
                        }
                    }
                    throw new ICUUncheckedIOException("CodePointTrie data header has a different type or value width than required");
                }
                throw new ICUUncheckedIOException("CodePointTrie data header has unsupported options");
            }
            throw new ICUUncheckedIOException("Buffer too short for a CodePointTrie header");
        } catch (Throwable th4) {
            th = th4;
            bytes.order(outerByteOrder);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.CodePointTrie$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$CodePointTrie$ValueWidth = new int[ValueWidth.values().length];

        static {
            try {
                $SwitchMap$android$icu$util$CodePointTrie$ValueWidth[ValueWidth.BITS_16.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$util$CodePointTrie$ValueWidth[ValueWidth.BITS_32.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$util$CodePointTrie$ValueWidth[ValueWidth.BITS_8.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public final ValueWidth getValueWidth() {
        return this.data.getValueWidth();
    }

    @Override // android.icu.util.CodePointMap
    public int get(int c) {
        return this.data.getFromIndex(cpIndex(c));
    }

    public final int asciiGet(int c) {
        return this.ascii[c];
    }

    private static final int maybeFilterValue(int value, int trieNullValue, int nullValue2, CodePointMap.ValueFilter filter) {
        if (value == trieNullValue) {
            return nullValue2;
        }
        if (filter != null) {
            return filter.apply(value);
        }
        return value;
    }

    /* JADX INFO: Multiple debug info for r13v4 int: [D('i1' int), D('i3Block' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0136, code lost:
        r26.set(r24, r9 - 1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x013c, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0174 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01a1 A[LOOP:1: B:44:0x00b1->B:95:0x01a1, LOOP_END] */
    @Override // android.icu.util.CodePointMap
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean getRange(int r24, android.icu.util.CodePointMap.ValueFilter r25, android.icu.util.CodePointMap.Range r26) {
        /*
        // Method dump skipped, instructions count: 429
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.CodePointTrie.getRange(int, android.icu.util.CodePointMap$ValueFilter, android.icu.util.CodePointMap$Range):boolean");
    }

    public final int toBinary(OutputStream os) {
        try {
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(1416784179);
            dos.writeChar(((this.dataLength & 983040) >> 4) | ((983040 & this.dataNullOffset) >> 8) | (getType().ordinal() << 6) | getValueWidth().ordinal());
            dos.writeChar(this.index.length);
            dos.writeChar(this.dataLength);
            dos.writeChar(this.index3NullOffset);
            dos.writeChar(this.dataNullOffset);
            dos.writeChar(this.highStart >> 9);
            for (char i : this.index) {
                dos.writeChar(i);
            }
            return 16 + (this.index.length * 2) + this.data.write(dos);
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    /* access modifiers changed from: private */
    public static abstract class Data {
        /* access modifiers changed from: package-private */
        public abstract int getDataLength();

        /* access modifiers changed from: package-private */
        public abstract int getFromIndex(int i);

        /* access modifiers changed from: package-private */
        public abstract ValueWidth getValueWidth();

        /* access modifiers changed from: package-private */
        public abstract int write(DataOutputStream dataOutputStream) throws IOException;

        private Data() {
        }

        /* synthetic */ Data(AnonymousClass1 x0) {
            this();
        }
    }

    private static final class Data16 extends Data {
        char[] array;

        Data16(char[] a) {
            super(null);
            this.array = a;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public ValueWidth getValueWidth() {
            return ValueWidth.BITS_16;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getDataLength() {
            return this.array.length;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getFromIndex(int index) {
            return this.array[index];
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int write(DataOutputStream dos) throws IOException {
            for (char v : this.array) {
                dos.writeChar(v);
            }
            return this.array.length * 2;
        }
    }

    private static final class Data32 extends Data {
        int[] array;

        Data32(int[] a) {
            super(null);
            this.array = a;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public ValueWidth getValueWidth() {
            return ValueWidth.BITS_32;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getDataLength() {
            return this.array.length;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getFromIndex(int index) {
            return this.array[index];
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int write(DataOutputStream dos) throws IOException {
            for (int v : this.array) {
                dos.writeInt(v);
            }
            return this.array.length * 4;
        }
    }

    private static final class Data8 extends Data {
        byte[] array;

        Data8(byte[] a) {
            super(null);
            this.array = a;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public ValueWidth getValueWidth() {
            return ValueWidth.BITS_8;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getDataLength() {
            return this.array.length;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getFromIndex(int index) {
            return this.array[index] & 255;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int write(DataOutputStream dos) throws IOException {
            for (byte v : this.array) {
                dos.writeByte(v);
            }
            return this.array.length;
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final int fastIndex(int c) {
        return this.index[c >> 6] + (c & 63);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final int smallIndex(Type type, int c) {
        if (c >= this.highStart) {
            return this.dataLength - 2;
        }
        return internalSmallIndex(type, c);
    }

    private final int internalSmallIndex(Type type, int c) {
        int i1;
        int dataBlock;
        int i12 = c >> 14;
        if (type == Type.FAST) {
            i1 = i12 + PointerIconCompat.TYPE_GRAB;
        } else {
            i1 = i12 + 64;
        }
        char[] cArr = this.index;
        char c2 = cArr[cArr[i1] + ((c >> 9) & 31)];
        int i3 = (c >> 4) & 31;
        if ((32768 & c2) == 0) {
            dataBlock = cArr[c2 + i3];
        } else {
            int i3Block = (c2 & 32767) + (i3 & -8) + (i3 >> 3);
            int i32 = i3 & 7;
            dataBlock = cArr[i3Block + 1 + i32] | ((cArr[i3Block] << ((i32 * 2) + 2)) & 196608);
        }
        return (c & 15) + dataBlock;
    }

    public static abstract class Fast extends CodePointTrie {
        public abstract int bmpGet(int i);

        public abstract int suppGet(int i);

        /* synthetic */ Fast(char[] x0, Data x1, int x2, int x3, int x4, AnonymousClass1 x5) {
            this(x0, x1, x2, x3, x4);
        }

        private Fast(char[] index, Data data, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, data, highStart, index3NullOffset, dataNullOffset, null);
        }

        public static Fast fromBinary(ValueWidth valueWidth, ByteBuffer bytes) {
            return (Fast) CodePointTrie.fromBinary(Type.FAST, valueWidth, bytes);
        }

        @Override // android.icu.util.CodePointTrie
        public final Type getType() {
            return Type.FAST;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.CodePointTrie
        @Deprecated
        public final int cpIndex(int c) {
            if (c >= 0) {
                if (c <= 65535) {
                    return fastIndex(c);
                }
                if (c <= 1114111) {
                    return smallIndex(Type.FAST, c);
                }
            }
            return this.dataLength - 1;
        }

        @Override // android.icu.util.CodePointMap
        public final CodePointMap.StringIterator stringIterator(CharSequence s, int sIndex) {
            return new FastStringIterator(this, s, sIndex, null);
        }

        private final class FastStringIterator extends CodePointMap.StringIterator {
            /* synthetic */ FastStringIterator(Fast x0, CharSequence x1, int x2, AnonymousClass1 x3) {
                this(x1, x2);
            }

            private FastStringIterator(CharSequence s, int sIndex) {
                super(s, sIndex);
            }

            @Override // android.icu.util.CodePointMap.StringIterator
            public boolean next() {
                int dataIndex;
                if (this.sIndex >= this.s.length()) {
                    return false;
                }
                CharSequence charSequence = this.s;
                int i = this.sIndex;
                this.sIndex = i + 1;
                char lead = charSequence.charAt(i);
                this.c = lead;
                if (!Character.isSurrogate(lead)) {
                    dataIndex = Fast.this.fastIndex(this.c);
                } else {
                    if (Normalizer2Impl.UTF16Plus.isSurrogateLead(lead) && this.sIndex < this.s.length()) {
                        char trail = this.s.charAt(this.sIndex);
                        if (Character.isLowSurrogate(trail)) {
                            this.sIndex++;
                            this.c = Character.toCodePoint(lead, trail);
                            dataIndex = Fast.this.smallIndex(Type.FAST, this.c);
                        }
                    }
                    dataIndex = Fast.this.dataLength - 1;
                }
                this.value = Fast.this.data.getFromIndex(dataIndex);
                return true;
            }

            @Override // android.icu.util.CodePointMap.StringIterator
            public boolean previous() {
                int dataIndex;
                if (this.sIndex <= 0) {
                    return false;
                }
                CharSequence charSequence = this.s;
                int i = this.sIndex - 1;
                this.sIndex = i;
                char trail = charSequence.charAt(i);
                this.c = trail;
                if (!Character.isSurrogate(trail)) {
                    dataIndex = Fast.this.fastIndex(this.c);
                } else {
                    if (!Normalizer2Impl.UTF16Plus.isSurrogateLead(trail) && this.sIndex > 0) {
                        char lead = this.s.charAt(this.sIndex - 1);
                        if (Character.isHighSurrogate(lead)) {
                            this.sIndex--;
                            this.c = Character.toCodePoint(lead, trail);
                            dataIndex = Fast.this.smallIndex(Type.FAST, this.c);
                        }
                    }
                    dataIndex = Fast.this.dataLength - 1;
                }
                this.value = Fast.this.data.getFromIndex(dataIndex);
                return true;
            }
        }
    }

    public static abstract class Small extends CodePointTrie {
        /* synthetic */ Small(char[] x0, Data x1, int x2, int x3, int x4, AnonymousClass1 x5) {
            this(x0, x1, x2, x3, x4);
        }

        private Small(char[] index, Data data, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, data, highStart, index3NullOffset, dataNullOffset, null);
        }

        public static Small fromBinary(ValueWidth valueWidth, ByteBuffer bytes) {
            return (Small) CodePointTrie.fromBinary(Type.SMALL, valueWidth, bytes);
        }

        @Override // android.icu.util.CodePointTrie
        public final Type getType() {
            return Type.SMALL;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.CodePointTrie
        @Deprecated
        public final int cpIndex(int c) {
            if (c >= 0) {
                if (c <= 4095) {
                    return fastIndex(c);
                }
                if (c <= 1114111) {
                    return smallIndex(Type.SMALL, c);
                }
            }
            return this.dataLength - 1;
        }

        @Override // android.icu.util.CodePointMap
        public final CodePointMap.StringIterator stringIterator(CharSequence s, int sIndex) {
            return new SmallStringIterator(this, s, sIndex, null);
        }

        private final class SmallStringIterator extends CodePointMap.StringIterator {
            /* synthetic */ SmallStringIterator(Small x0, CharSequence x1, int x2, AnonymousClass1 x3) {
                this(x1, x2);
            }

            private SmallStringIterator(CharSequence s, int sIndex) {
                super(s, sIndex);
            }

            @Override // android.icu.util.CodePointMap.StringIterator
            public boolean next() {
                int dataIndex;
                if (this.sIndex >= this.s.length()) {
                    return false;
                }
                CharSequence charSequence = this.s;
                int i = this.sIndex;
                this.sIndex = i + 1;
                char lead = charSequence.charAt(i);
                this.c = lead;
                if (!Character.isSurrogate(lead)) {
                    dataIndex = Small.this.cpIndex(this.c);
                } else {
                    if (Normalizer2Impl.UTF16Plus.isSurrogateLead(lead) && this.sIndex < this.s.length()) {
                        char trail = this.s.charAt(this.sIndex);
                        if (Character.isLowSurrogate(trail)) {
                            this.sIndex++;
                            this.c = Character.toCodePoint(lead, trail);
                            dataIndex = Small.this.smallIndex(Type.SMALL, this.c);
                        }
                    }
                    dataIndex = Small.this.dataLength - 1;
                }
                this.value = Small.this.data.getFromIndex(dataIndex);
                return true;
            }

            @Override // android.icu.util.CodePointMap.StringIterator
            public boolean previous() {
                int dataIndex;
                if (this.sIndex <= 0) {
                    return false;
                }
                CharSequence charSequence = this.s;
                int i = this.sIndex - 1;
                this.sIndex = i;
                char trail = charSequence.charAt(i);
                this.c = trail;
                if (!Character.isSurrogate(trail)) {
                    dataIndex = Small.this.cpIndex(this.c);
                } else {
                    if (!Normalizer2Impl.UTF16Plus.isSurrogateLead(trail) && this.sIndex > 0) {
                        char lead = this.s.charAt(this.sIndex - 1);
                        if (Character.isHighSurrogate(lead)) {
                            this.sIndex--;
                            this.c = Character.toCodePoint(lead, trail);
                            dataIndex = Small.this.smallIndex(Type.SMALL, this.c);
                        }
                    }
                    dataIndex = Small.this.dataLength - 1;
                }
                this.value = Small.this.data.getFromIndex(dataIndex);
                return true;
            }
        }
    }

    public static final class Fast16 extends Fast {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final char[] dataArray;

        Fast16(char[] index, char[] data16, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, new Data16(data16), highStart, index3NullOffset, dataNullOffset, null);
            this.dataArray = data16;
        }

        public static Fast16 fromBinary(ByteBuffer bytes) {
            return (Fast16) CodePointTrie.fromBinary(Type.FAST, ValueWidth.BITS_16, bytes);
        }

        @Override // android.icu.util.CodePointTrie, android.icu.util.CodePointMap
        public final int get(int c) {
            return this.dataArray[cpIndex(c)];
        }

        @Override // android.icu.util.CodePointTrie.Fast
        public final int bmpGet(int c) {
            return this.dataArray[fastIndex(c)];
        }

        @Override // android.icu.util.CodePointTrie.Fast
        public final int suppGet(int c) {
            return this.dataArray[smallIndex(Type.FAST, c)];
        }
    }

    public static final class Fast32 extends Fast {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final int[] dataArray;

        Fast32(char[] index, int[] data32, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, new Data32(data32), highStart, index3NullOffset, dataNullOffset, null);
            this.dataArray = data32;
        }

        public static Fast32 fromBinary(ByteBuffer bytes) {
            return (Fast32) CodePointTrie.fromBinary(Type.FAST, ValueWidth.BITS_32, bytes);
        }

        @Override // android.icu.util.CodePointTrie, android.icu.util.CodePointMap
        public final int get(int c) {
            return this.dataArray[cpIndex(c)];
        }

        @Override // android.icu.util.CodePointTrie.Fast
        public final int bmpGet(int c) {
            return this.dataArray[fastIndex(c)];
        }

        @Override // android.icu.util.CodePointTrie.Fast
        public final int suppGet(int c) {
            return this.dataArray[smallIndex(Type.FAST, c)];
        }
    }

    public static final class Fast8 extends Fast {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final byte[] dataArray;

        Fast8(char[] index, byte[] data8, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, new Data8(data8), highStart, index3NullOffset, dataNullOffset, null);
            this.dataArray = data8;
        }

        public static Fast8 fromBinary(ByteBuffer bytes) {
            return (Fast8) CodePointTrie.fromBinary(Type.FAST, ValueWidth.BITS_8, bytes);
        }

        @Override // android.icu.util.CodePointTrie, android.icu.util.CodePointMap
        public final int get(int c) {
            return this.dataArray[cpIndex(c)] & 255;
        }

        @Override // android.icu.util.CodePointTrie.Fast
        public final int bmpGet(int c) {
            return this.dataArray[fastIndex(c)] & 255;
        }

        @Override // android.icu.util.CodePointTrie.Fast
        public final int suppGet(int c) {
            return this.dataArray[smallIndex(Type.FAST, c)] & 255;
        }
    }

    public static final class Small16 extends Small {
        Small16(char[] index, char[] data16, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, new Data16(data16), highStart, index3NullOffset, dataNullOffset, null);
        }

        public static Small16 fromBinary(ByteBuffer bytes) {
            return (Small16) CodePointTrie.fromBinary(Type.SMALL, ValueWidth.BITS_16, bytes);
        }
    }

    public static final class Small32 extends Small {
        Small32(char[] index, int[] data32, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, new Data32(data32), highStart, index3NullOffset, dataNullOffset, null);
        }

        public static Small32 fromBinary(ByteBuffer bytes) {
            return (Small32) CodePointTrie.fromBinary(Type.SMALL, ValueWidth.BITS_32, bytes);
        }
    }

    public static final class Small8 extends Small {
        Small8(char[] index, byte[] data8, int highStart, int index3NullOffset, int dataNullOffset) {
            super(index, new Data8(data8), highStart, index3NullOffset, dataNullOffset, null);
        }

        public static Small8 fromBinary(ByteBuffer bytes) {
            return (Small8) CodePointTrie.fromBinary(Type.SMALL, ValueWidth.BITS_8, bytes);
        }
    }
}
