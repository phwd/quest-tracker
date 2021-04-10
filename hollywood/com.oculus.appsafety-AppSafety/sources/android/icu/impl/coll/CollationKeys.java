package android.icu.impl.coll;

public final class CollationKeys {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CASE_LOWER_FIRST_COMMON_HIGH = 13;
    private static final int CASE_LOWER_FIRST_COMMON_LOW = 1;
    private static final int CASE_LOWER_FIRST_COMMON_MAX_COUNT = 7;
    private static final int CASE_LOWER_FIRST_COMMON_MIDDLE = 7;
    private static final int CASE_UPPER_FIRST_COMMON_HIGH = 15;
    private static final int CASE_UPPER_FIRST_COMMON_LOW = 3;
    private static final int CASE_UPPER_FIRST_COMMON_MAX_COUNT = 13;
    private static final int QUAT_COMMON_HIGH = 252;
    private static final int QUAT_COMMON_LOW = 28;
    private static final int QUAT_COMMON_MAX_COUNT = 113;
    private static final int QUAT_COMMON_MIDDLE = 140;
    private static final int QUAT_SHIFTED_LIMIT_BYTE = 27;
    static final int SEC_COMMON_HIGH = 69;
    private static final int SEC_COMMON_LOW = 5;
    private static final int SEC_COMMON_MAX_COUNT = 33;
    private static final int SEC_COMMON_MIDDLE = 37;
    public static final LevelCallback SIMPLE_LEVEL_FALLBACK = new LevelCallback();
    private static final int TER_LOWER_FIRST_COMMON_HIGH = 69;
    private static final int TER_LOWER_FIRST_COMMON_LOW = 5;
    private static final int TER_LOWER_FIRST_COMMON_MAX_COUNT = 33;
    private static final int TER_LOWER_FIRST_COMMON_MIDDLE = 37;
    private static final int TER_ONLY_COMMON_HIGH = 197;
    private static final int TER_ONLY_COMMON_LOW = 5;
    private static final int TER_ONLY_COMMON_MAX_COUNT = 97;
    private static final int TER_ONLY_COMMON_MIDDLE = 101;
    private static final int TER_UPPER_FIRST_COMMON_HIGH = 197;
    private static final int TER_UPPER_FIRST_COMMON_LOW = 133;
    private static final int TER_UPPER_FIRST_COMMON_MAX_COUNT = 33;
    private static final int TER_UPPER_FIRST_COMMON_MIDDLE = 165;
    private static final int[] levelMasks = {2, 6, 22, 54, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 54};

    public static abstract class SortKeyByteSink {
        private int appended_ = 0;
        protected byte[] buffer_;

        /* access modifiers changed from: protected */
        public abstract void AppendBeyondCapacity(byte[] bArr, int i, int i2, int i3);

        /* access modifiers changed from: protected */
        public abstract boolean Resize(int i, int i2);

        public SortKeyByteSink(byte[] dest) {
            this.buffer_ = dest;
        }

        public void setBufferAndAppended(byte[] dest, int app) {
            this.buffer_ = dest;
            this.appended_ = app;
        }

        public void Append(byte[] bytes, int n) {
            if (n > 0 && bytes != null) {
                int length = this.appended_;
                this.appended_ += n;
                byte[] bArr = this.buffer_;
                if (n <= bArr.length - length) {
                    System.arraycopy(bytes, 0, bArr, length, n);
                } else {
                    AppendBeyondCapacity(bytes, 0, n, length);
                }
            }
        }

        public void Append(int b) {
            int i = this.appended_;
            if (i < this.buffer_.length || Resize(1, i)) {
                this.buffer_[this.appended_] = (byte) b;
            }
            this.appended_++;
        }

        public int NumberOfBytesAppended() {
            return this.appended_;
        }

        public int GetRemainingCapacity() {
            return this.buffer_.length - this.appended_;
        }

        public boolean Overflowed() {
            return this.appended_ > this.buffer_.length;
        }
    }

    public static class LevelCallback {
        /* access modifiers changed from: package-private */
        public boolean needToWrite(int level) {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static final class SortKeyLevel {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int INITIAL_CAPACITY = 40;
        byte[] buffer = new byte[40];
        int len = 0;

        SortKeyLevel() {
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.len == 0;
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.len;
        }

        /* access modifiers changed from: package-private */
        public byte getAt(int index) {
            return this.buffer[index];
        }

        /* access modifiers changed from: package-private */
        public byte[] data() {
            return this.buffer;
        }

        /* access modifiers changed from: package-private */
        public void appendByte(int b) {
            if (this.len < this.buffer.length || ensureCapacity(1)) {
                byte[] bArr = this.buffer;
                int i = this.len;
                this.len = i + 1;
                bArr[i] = (byte) b;
            }
        }

        /* access modifiers changed from: package-private */
        public void appendWeight16(int w) {
            byte b0 = (byte) (w >>> 8);
            byte b1 = (byte) w;
            int appendLength = b1 == 0 ? 1 : 2;
            if (this.len + appendLength <= this.buffer.length || ensureCapacity(appendLength)) {
                byte[] bArr = this.buffer;
                int i = this.len;
                this.len = i + 1;
                bArr[i] = b0;
                if (b1 != 0) {
                    int i2 = this.len;
                    this.len = i2 + 1;
                    bArr[i2] = b1;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void appendWeight32(long w) {
            int appendLength = 4;
            byte[] bytes = {(byte) ((int) (w >>> 24)), (byte) ((int) (w >>> 16)), (byte) ((int) (w >>> 8)), (byte) ((int) w)};
            if (bytes[1] == 0) {
                appendLength = 1;
            } else if (bytes[2] == 0) {
                appendLength = 2;
            } else if (bytes[3] == 0) {
                appendLength = 3;
            }
            if (this.len + appendLength <= this.buffer.length || ensureCapacity(appendLength)) {
                byte[] bArr = this.buffer;
                int i = this.len;
                this.len = i + 1;
                bArr[i] = bytes[0];
                if (bytes[1] != 0) {
                    int i2 = this.len;
                    this.len = i2 + 1;
                    bArr[i2] = bytes[1];
                    if (bytes[2] != 0) {
                        int i3 = this.len;
                        this.len = i3 + 1;
                        bArr[i3] = bytes[2];
                        if (bytes[3] != 0) {
                            int i4 = this.len;
                            this.len = i4 + 1;
                            bArr[i4] = bytes[3];
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void appendReverseWeight16(int w) {
            byte b0 = (byte) (w >>> 8);
            byte b1 = (byte) w;
            int appendLength = b1 == 0 ? 1 : 2;
            if (this.len + appendLength > this.buffer.length && !ensureCapacity(appendLength)) {
                return;
            }
            if (b1 == 0) {
                byte[] bArr = this.buffer;
                int i = this.len;
                this.len = i + 1;
                bArr[i] = b0;
                return;
            }
            byte[] bArr2 = this.buffer;
            int i2 = this.len;
            bArr2[i2] = b1;
            bArr2[i2 + 1] = b0;
            this.len = i2 + 2;
        }

        /* access modifiers changed from: package-private */
        public void appendTo(SortKeyByteSink sink) {
            sink.Append(this.buffer, this.len - 1);
        }

        private boolean ensureCapacity(int appendCapacity) {
            int newCapacity = this.buffer.length * 2;
            int altCapacity = this.len + (appendCapacity * 2);
            if (newCapacity < altCapacity) {
                newCapacity = altCapacity;
            }
            if (newCapacity < 200) {
                newCapacity = 200;
            }
            byte[] newbuf = new byte[newCapacity];
            System.arraycopy(this.buffer, 0, newbuf, 0, this.len);
            this.buffer = newbuf;
            return true;
        }
    }

    private static SortKeyLevel getSortKeyLevel(int levels, int level) {
        if ((levels & level) != 0) {
            return new SortKeyLevel();
        }
        return null;
    }

    private CollationKeys() {
    }

    /* JADX INFO: Multiple debug info for r3v26 int: [D('last' int), D('secSegmentStart' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x03a0  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x03a6  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x046f  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x03fd A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeSortKeyUpToQuaternary(android.icu.impl.coll.CollationIterator r43, boolean[] r44, android.icu.impl.coll.CollationSettings r45, android.icu.impl.coll.CollationKeys.SortKeyByteSink r46, int r47, android.icu.impl.coll.CollationKeys.LevelCallback r48, boolean r49) {
        /*
        // Method dump skipped, instructions count: 1158
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationKeys.writeSortKeyUpToQuaternary(android.icu.impl.coll.CollationIterator, boolean[], android.icu.impl.coll.CollationSettings, android.icu.impl.coll.CollationKeys$SortKeyByteSink, int, android.icu.impl.coll.CollationKeys$LevelCallback, boolean):void");
    }
}
