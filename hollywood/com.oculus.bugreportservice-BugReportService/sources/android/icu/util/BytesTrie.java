package android.icu.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public final class BytesTrie implements Cloneable, Iterable {
    private static Result[] valueResults_ = {Result.INTERMEDIATE_VALUE, Result.FINAL_VALUE};
    private byte[] bytes_;
    private int pos_;
    private int remainingMatchLength_ = -1;
    private int root_;

    /* access modifiers changed from: private */
    public static int skipValue(int i, int i2) {
        return i2 >= 162 ? i2 < 216 ? i + 1 : i2 < 252 ? i + 2 : i + ((i2 >> 1) & 1) + 3 : i;
    }

    public BytesTrie(byte[] bArr, int i) {
        this.bytes_ = bArr;
        this.root_ = i;
        this.pos_ = i;
    }

    public Object clone() {
        return super.clone();
    }

    public enum Result {
        NO_MATCH,
        NO_VALUE,
        FINAL_VALUE,
        INTERMEDIATE_VALUE;

        public boolean matches() {
            return this != NO_MATCH;
        }

        public boolean hasValue() {
            return ordinal() >= 2;
        }

        public boolean hasNext() {
            return (ordinal() & 1) != 0;
        }
    }

    public Result first(int i) {
        this.remainingMatchLength_ = -1;
        if (i < 0) {
            i += 256;
        }
        return nextImpl(this.root_, i);
    }

    public Result next(int i) {
        int i2;
        int i3 = this.pos_;
        if (i3 < 0) {
            return Result.NO_MATCH;
        }
        if (i < 0) {
            i += 256;
        }
        int i4 = this.remainingMatchLength_;
        if (i4 < 0) {
            return nextImpl(i3, i);
        }
        byte[] bArr = this.bytes_;
        int i5 = i3 + 1;
        if (i == (bArr[i3] & 255)) {
            int i6 = i4 - 1;
            this.remainingMatchLength_ = i6;
            this.pos_ = i5;
            return (i6 >= 0 || (i2 = bArr[i5] & 255) < 32) ? Result.NO_VALUE : valueResults_[i2 & 1];
        }
        stop();
        return Result.NO_MATCH;
    }

    public int getValue() {
        int i = this.pos_;
        byte[] bArr = this.bytes_;
        return readValue(bArr, i + 1, (bArr[i] & 255) >> 1);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new Iterator(this.bytes_, this.pos_, this.remainingMatchLength_, 0);
    }

    public static final class Entry {
        private byte[] bytes;
        private int length;
        public int value;

        private Entry(int i) {
            this.bytes = new byte[i];
        }

        private void ensureCapacity(int i) {
            byte[] bArr = this.bytes;
            if (bArr.length < i) {
                byte[] bArr2 = new byte[Math.min(bArr.length * 2, i * 2)];
                System.arraycopy(this.bytes, 0, bArr2, 0, this.length);
                this.bytes = bArr2;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void append(byte b) {
            ensureCapacity(this.length + 1);
            byte[] bArr = this.bytes;
            int i = this.length;
            this.length = i + 1;
            bArr[i] = b;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void append(byte[] bArr, int i, int i2) {
            ensureCapacity(this.length + i2);
            System.arraycopy(bArr, i, this.bytes, this.length, i2);
            this.length += i2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void truncateString(int i) {
            this.length = i;
        }
    }

    public static final class Iterator implements java.util.Iterator {
        private byte[] bytes_;
        private Entry entry_;
        private int initialPos_;
        private int initialRemainingMatchLength_;
        private int maxLength_;
        private int pos_;
        private int remainingMatchLength_;
        private ArrayList stack_;

        private Iterator(byte[] bArr, int i, int i2, int i3) {
            this.stack_ = new ArrayList();
            this.bytes_ = bArr;
            this.initialPos_ = i;
            this.pos_ = i;
            this.initialRemainingMatchLength_ = i2;
            this.remainingMatchLength_ = i2;
            this.maxLength_ = i3;
            int i4 = this.maxLength_;
            this.entry_ = new Entry(i4 == 0 ? 32 : i4);
            int i5 = this.remainingMatchLength_;
            if (i5 >= 0) {
                int i6 = i5 + 1;
                int i7 = this.maxLength_;
                if (i7 > 0 && i6 > i7) {
                    i6 = i7;
                }
                this.entry_.append(this.bytes_, this.pos_, i6);
                this.pos_ += i6;
                this.remainingMatchLength_ -= i6;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos_ >= 0 || !this.stack_.isEmpty();
        }

        @Override // java.util.Iterator
        public Entry next() {
            int i;
            int i2;
            int i3 = this.pos_;
            boolean z = true;
            if (i3 < 0) {
                if (!this.stack_.isEmpty()) {
                    ArrayList arrayList = this.stack_;
                    long longValue = ((Long) arrayList.remove(arrayList.size() - 1)).longValue();
                    int i4 = (int) longValue;
                    int i5 = (int) (longValue >> 32);
                    this.entry_.truncateString(65535 & i4);
                    int i6 = i4 >>> 16;
                    if (i6 > 1) {
                        i3 = branchNext(i5, i6);
                        if (i3 < 0) {
                            return this.entry_;
                        }
                    } else {
                        this.entry_.append(this.bytes_[i5]);
                        i3 = i5 + 1;
                    }
                } else {
                    throw new NoSuchElementException();
                }
            }
            if (this.remainingMatchLength_ >= 0) {
                return truncateAndStop();
            }
            while (true) {
                int i7 = i3 + 1;
                int i8 = this.bytes_[i3] & 255;
                if (i8 >= 32) {
                    if ((i8 & 1) == 0) {
                        z = false;
                    }
                    this.entry_.value = BytesTrie.readValue(this.bytes_, i7, i8 >> 1);
                    if (z || (this.maxLength_ > 0 && this.entry_.length == this.maxLength_)) {
                        this.pos_ = -1;
                    } else {
                        this.pos_ = BytesTrie.skipValue(i7, i8);
                    }
                    return this.entry_;
                } else if (this.maxLength_ > 0 && this.entry_.length == this.maxLength_) {
                    return truncateAndStop();
                } else {
                    if (i8 < 16) {
                        if (i8 == 0) {
                            i2 = i7 + 1;
                            i8 = this.bytes_[i7] & 255;
                        } else {
                            i2 = i7;
                        }
                        i3 = branchNext(i2, i8 + 1);
                        if (i3 < 0) {
                            return this.entry_;
                        }
                    } else {
                        int i9 = (i8 - 16) + 1;
                        if (this.maxLength_ <= 0 || this.entry_.length + i9 <= (i = this.maxLength_)) {
                            this.entry_.append(this.bytes_, i7, i9);
                            i3 = i7 + i9;
                        } else {
                            Entry entry = this.entry_;
                            entry.append(this.bytes_, i7, i - entry.length);
                            return truncateAndStop();
                        }
                    }
                }
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private Entry truncateAndStop() {
            this.pos_ = -1;
            Entry entry = this.entry_;
            entry.value = -1;
            return entry;
        }

        private int branchNext(int i, int i2) {
            while (i2 > 5) {
                int i3 = i + 1;
                int i4 = i2 >> 1;
                this.stack_.add(Long.valueOf((((long) BytesTrie.skipDelta(this.bytes_, i3)) << 32) | ((long) ((i2 - i4) << 16)) | ((long) this.entry_.length)));
                i = BytesTrie.jumpByDelta(this.bytes_, i3);
                i2 = i4;
            }
            byte[] bArr = this.bytes_;
            int i5 = i + 1;
            byte b = bArr[i];
            int i6 = i5 + 1;
            int i7 = bArr[i5] & 255;
            boolean z = (i7 & 1) != 0;
            int readValue = BytesTrie.readValue(this.bytes_, i6, i7 >> 1);
            int skipValue = BytesTrie.skipValue(i6, i7);
            this.stack_.add(Long.valueOf((((long) skipValue) << 32) | ((long) ((i2 - 1) << 16)) | ((long) this.entry_.length)));
            this.entry_.append(b);
            if (!z) {
                return skipValue + readValue;
            }
            this.pos_ = -1;
            this.entry_.value = readValue;
            return -1;
        }
    }

    private void stop() {
        this.pos_ = -1;
    }

    /* access modifiers changed from: private */
    public static int readValue(byte[] bArr, int i, int i2) {
        int i3;
        byte b;
        if (i2 < 81) {
            return i2 - 16;
        }
        if (i2 < 108) {
            i3 = (i2 - 81) << 8;
            b = bArr[i];
        } else if (i2 < 126) {
            i3 = ((i2 - 108) << 16) | ((bArr[i] & 255) << 8);
            b = bArr[i + 1];
        } else if (i2 == 126) {
            i3 = ((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
            b = bArr[i + 2];
        } else {
            i3 = (bArr[i] << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
            b = bArr[i + 3];
        }
        return i3 | (b & 255);
    }

    private static int skipValue(byte[] bArr, int i) {
        return skipValue(i + 1, bArr[i] & 255);
    }

    /* access modifiers changed from: private */
    public static int jumpByDelta(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = bArr[i] & 255;
        if (i3 >= 192) {
            if (i3 < 240) {
                i3 = ((i3 - 192) << 8) | (bArr[i2] & 255);
                i2++;
            } else if (i3 < 254) {
                i3 = ((i3 - 240) << 16) | ((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255);
                i2 += 2;
            } else if (i3 == 254) {
                i3 = ((bArr[i2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8) | (bArr[i2 + 2] & 255);
                i2 += 3;
            } else {
                i3 = (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8) | (bArr[i2 + 3] & 255);
                i2 += 4;
            }
        }
        return i2 + i3;
    }

    /* access modifiers changed from: private */
    public static int skipDelta(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = bArr[i] & 255;
        if (i3 < 192) {
            return i2;
        }
        if (i3 < 240) {
            return i2 + 1;
        }
        return i3 < 254 ? i2 + 2 : i2 + (i3 & 1) + 3;
    }

    private Result branchNext(int i, int i2, int i3) {
        Result result;
        int i4;
        if (i2 == 0) {
            i2 = this.bytes_[i] & 255;
            i++;
        }
        int i5 = i2 + 1;
        while (i5 > 5) {
            byte[] bArr = this.bytes_;
            int i6 = i + 1;
            if (i3 < (bArr[i] & 255)) {
                i5 >>= 1;
                i = jumpByDelta(bArr, i6);
            } else {
                i5 -= i5 >> 1;
                i = skipDelta(bArr, i6);
            }
        }
        do {
            byte[] bArr2 = this.bytes_;
            int i7 = i + 1;
            if (i3 == (bArr2[i] & 255)) {
                int i8 = bArr2[i7] & 255;
                if ((i8 & 1) != 0) {
                    result = Result.FINAL_VALUE;
                } else {
                    int i9 = i7 + 1;
                    int i10 = i8 >> 1;
                    if (i10 < 81) {
                        i4 = i10 - 16;
                    } else if (i10 < 108) {
                        i4 = ((i10 - 81) << 8) | (bArr2[i9] & 255);
                        i9++;
                    } else if (i10 < 126) {
                        i4 = ((i10 - 108) << 16) | ((bArr2[i9] & 255) << 8) | (bArr2[i9 + 1] & 255);
                        i9 += 2;
                    } else if (i10 == 126) {
                        i4 = ((bArr2[i9] & 255) << 16) | ((bArr2[i9 + 1] & 255) << 8) | (bArr2[i9 + 2] & 255);
                        i9 += 3;
                    } else {
                        i4 = (bArr2[i9] << 24) | ((bArr2[i9 + 1] & 255) << 16) | ((bArr2[i9 + 2] & 255) << 8) | (bArr2[i9 + 3] & 255);
                        i9 += 4;
                    }
                    i7 = i9 + i4;
                    int i11 = this.bytes_[i7] & 255;
                    result = i11 >= 32 ? valueResults_[i11 & 1] : Result.NO_VALUE;
                }
                this.pos_ = i7;
                return result;
            }
            i5--;
            i = skipValue(bArr2, i7);
        } while (i5 > 1);
        byte[] bArr3 = this.bytes_;
        int i12 = i + 1;
        if (i3 == (bArr3[i] & 255)) {
            this.pos_ = i12;
            int i13 = bArr3[i12] & 255;
            return i13 >= 32 ? valueResults_[i13 & 1] : Result.NO_VALUE;
        }
        stop();
        return Result.NO_MATCH;
    }

    private Result nextImpl(int i, int i2) {
        int i3;
        while (true) {
            byte[] bArr = this.bytes_;
            int i4 = i + 1;
            int i5 = bArr[i] & 255;
            if (i5 < 16) {
                return branchNext(i4, i5, i2);
            }
            if (i5 < 32) {
                int i6 = i5 - 16;
                int i7 = i4 + 1;
                if (i2 == (bArr[i4] & 255)) {
                    int i8 = i6 - 1;
                    this.remainingMatchLength_ = i8;
                    this.pos_ = i7;
                    return (i8 >= 0 || (i3 = bArr[i7] & 255) < 32) ? Result.NO_VALUE : valueResults_[i3 & 1];
                }
            } else if ((i5 & 1) != 0) {
                break;
            } else {
                i = skipValue(i4, i5);
            }
        }
        stop();
        return Result.NO_MATCH;
    }
}
