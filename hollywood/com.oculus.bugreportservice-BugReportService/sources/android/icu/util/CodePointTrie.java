package android.icu.util;

import android.icu.util.CodePointMap;
import java.nio.ByteBuffer;

public abstract class CodePointTrie extends CodePointMap {
    private final int[] ascii;
    protected final Data data;
    protected final int dataLength;
    private final int dataNullOffset;
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
    public abstract int cpIndex(int i);

    public abstract Type getType();

    /* synthetic */ CodePointTrie(char[] cArr, Data data2, int i, int i2, int i3, AnonymousClass1 r6) {
        this(cArr, data2, i, i2, i3);
    }

    private CodePointTrie(char[] cArr, Data data2, int i, int i2, int i3) {
        this.ascii = new int[128];
        this.index = cArr;
        this.data = data2;
        this.dataLength = data2.getDataLength();
        this.highStart = i;
        this.index3NullOffset = i2;
        this.dataNullOffset = i3;
        for (int i4 = 0; i4 < 128; i4++) {
            this.ascii[i4] = data2.getFromIndex(i4);
        }
        int i5 = this.dataLength;
        this.nullValue = data2.getFromIndex(i3 >= i5 ? i5 - 2 : i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b9 A[Catch:{ all -> 0x0144 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0124  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.icu.util.CodePointTrie fromBinary(android.icu.util.CodePointTrie.Type r16, android.icu.util.CodePointTrie.ValueWidth r17, java.nio.ByteBuffer r18) {
        /*
        // Method dump skipped, instructions count: 329
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.CodePointTrie.fromBinary(android.icu.util.CodePointTrie$Type, android.icu.util.CodePointTrie$ValueWidth, java.nio.ByteBuffer):android.icu.util.CodePointTrie");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.CodePointTrie$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$CodePointTrie$ValueWidth = new int[ValueWidth.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                android.icu.util.CodePointTrie$ValueWidth[] r0 = android.icu.util.CodePointTrie.ValueWidth.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.util.CodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth = r0
                int[] r0 = android.icu.util.CodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.util.CodePointTrie$ValueWidth r1 = android.icu.util.CodePointTrie.ValueWidth.BITS_16     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.util.CodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.util.CodePointTrie$ValueWidth r1 = android.icu.util.CodePointTrie.ValueWidth.BITS_32     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.util.CodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.util.CodePointTrie$ValueWidth r1 = android.icu.util.CodePointTrie.ValueWidth.BITS_8     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.CodePointTrie.AnonymousClass1.<clinit>():void");
        }
    }

    public int get(int i) {
        return this.data.getFromIndex(cpIndex(i));
    }

    private static final int maybeFilterValue(int i, int i2, int i3, CodePointMap.ValueFilter valueFilter) {
        if (i == i2) {
            return i3;
        }
        return valueFilter != null ? valueFilter.apply(i) : i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0121, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x015f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0158 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0186 A[LOOP:1: B:44:0x00a6->B:95:0x0186, LOOP_END] */
    @Override // android.icu.util.CodePointMap
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean getRange(int r24, android.icu.util.CodePointMap.ValueFilter r25, android.icu.util.CodePointMap.Range r26) {
        /*
        // Method dump skipped, instructions count: 397
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.CodePointTrie.getRange(int, android.icu.util.CodePointMap$ValueFilter, android.icu.util.CodePointMap$Range):boolean");
    }

    /* access modifiers changed from: private */
    public static abstract class Data {
        /* access modifiers changed from: package-private */
        public abstract int getDataLength();

        /* access modifiers changed from: package-private */
        public abstract int getFromIndex(int i);

        private Data() {
        }

        /* synthetic */ Data(AnonymousClass1 r1) {
            this();
        }
    }

    private static final class Data16 extends Data {
        char[] array;

        Data16(char[] cArr) {
            super(null);
            this.array = cArr;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getDataLength() {
            return this.array.length;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getFromIndex(int i) {
            return this.array[i];
        }
    }

    private static final class Data32 extends Data {
        int[] array;

        Data32(int[] iArr) {
            super(null);
            this.array = iArr;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getDataLength() {
            return this.array.length;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getFromIndex(int i) {
            return this.array[i];
        }
    }

    private static final class Data8 extends Data {
        byte[] array;

        Data8(byte[] bArr) {
            super(null);
            this.array = bArr;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getDataLength() {
            return this.array.length;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.util.CodePointTrie.Data
        public int getFromIndex(int i) {
            return this.array[i] & 255;
        }
    }

    /* access modifiers changed from: protected */
    public final int fastIndex(int i) {
        return this.index[i >> 6] + (i & 63);
    }

    /* access modifiers changed from: protected */
    public final int smallIndex(Type type, int i) {
        if (i >= this.highStart) {
            return this.dataLength - 2;
        }
        return internalSmallIndex(type, i);
    }

    private final int internalSmallIndex(Type type, int i) {
        char c;
        int i2 = i >> 14;
        int i3 = type == Type.FAST ? i2 + 1020 : i2 + 64;
        char[] cArr = this.index;
        char c2 = cArr[cArr[i3] + ((i >> 9) & 31)];
        int i4 = (i >> 4) & 31;
        if ((32768 & c2) == 0) {
            c = cArr[c2 + i4];
        } else {
            int i5 = (c2 & 32767) + (i4 & -8) + (i4 >> 3);
            int i6 = i4 & 7;
            c = cArr[i5 + 1 + i6] | ((cArr[i5] << ((i6 * 2) + 2)) & 196608);
        }
        return c + (i & 15);
    }

    public static abstract class Fast extends CodePointTrie {
        /* synthetic */ Fast(char[] cArr, Data data, int i, int i2, int i3, AnonymousClass1 r6) {
            this(cArr, data, i, i2, i3);
        }

        private Fast(char[] cArr, Data data, int i, int i2, int i3) {
            super(cArr, data, i, i2, i3, null);
        }

        @Override // android.icu.util.CodePointTrie
        public final Type getType() {
            return Type.FAST;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.CodePointTrie
        public final int cpIndex(int i) {
            if (i >= 0) {
                if (i <= 65535) {
                    return fastIndex(i);
                }
                if (i <= 1114111) {
                    return smallIndex(Type.FAST, i);
                }
            }
            return this.dataLength - 1;
        }
    }

    public static abstract class Small extends CodePointTrie {
        /* synthetic */ Small(char[] cArr, Data data, int i, int i2, int i3, AnonymousClass1 r6) {
            this(cArr, data, i, i2, i3);
        }

        private Small(char[] cArr, Data data, int i, int i2, int i3) {
            super(cArr, data, i, i2, i3, null);
        }

        @Override // android.icu.util.CodePointTrie
        public final Type getType() {
            return Type.SMALL;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.CodePointTrie
        public final int cpIndex(int i) {
            if (i >= 0) {
                if (i <= 4095) {
                    return fastIndex(i);
                }
                if (i <= 1114111) {
                    return smallIndex(Type.SMALL, i);
                }
            }
            return this.dataLength - 1;
        }
    }

    public static final class Fast16 extends Fast {
        private final char[] dataArray;

        Fast16(char[] cArr, char[] cArr2, int i, int i2, int i3) {
            super(cArr, new Data16(cArr2), i, i2, i3, null);
            this.dataArray = cArr2;
        }

        public static Fast16 fromBinary(ByteBuffer byteBuffer) {
            return (Fast16) CodePointTrie.fromBinary(Type.FAST, ValueWidth.BITS_16, byteBuffer);
        }

        @Override // android.icu.util.CodePointTrie
        public final int get(int i) {
            return this.dataArray[cpIndex(i)];
        }

        public final int bmpGet(int i) {
            return this.dataArray[fastIndex(i)];
        }

        public final int suppGet(int i) {
            return this.dataArray[smallIndex(Type.FAST, i)];
        }
    }

    public static final class Fast32 extends Fast {
        private final int[] dataArray;

        Fast32(char[] cArr, int[] iArr, int i, int i2, int i3) {
            super(cArr, new Data32(iArr), i, i2, i3, null);
            this.dataArray = iArr;
        }

        @Override // android.icu.util.CodePointTrie
        public final int get(int i) {
            return this.dataArray[cpIndex(i)];
        }
    }

    public static final class Fast8 extends Fast {
        private final byte[] dataArray;

        Fast8(char[] cArr, byte[] bArr, int i, int i2, int i3) {
            super(cArr, new Data8(bArr), i, i2, i3, null);
            this.dataArray = bArr;
        }

        @Override // android.icu.util.CodePointTrie
        public final int get(int i) {
            return this.dataArray[cpIndex(i)] & 255;
        }
    }

    public static final class Small16 extends Small {
        Small16(char[] cArr, char[] cArr2, int i, int i2, int i3) {
            super(cArr, new Data16(cArr2), i, i2, i3, null);
        }
    }

    public static final class Small32 extends Small {
        Small32(char[] cArr, int[] iArr, int i, int i2, int i3) {
            super(cArr, new Data32(iArr), i, i2, i3, null);
        }
    }

    public static final class Small8 extends Small {
        Small8(char[] cArr, byte[] bArr, int i, int i2, int i3) {
            super(cArr, new Data8(bArr), i, i2, i3, null);
        }
    }
}
