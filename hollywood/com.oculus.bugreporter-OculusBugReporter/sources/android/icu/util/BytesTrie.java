package android.icu.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public final class BytesTrie implements Cloneable, Iterable<Entry> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int kFiveByteDeltaLead = 255;
    static final int kFiveByteValueLead = 127;
    static final int kFourByteDeltaLead = 254;
    static final int kFourByteValueLead = 126;
    static final int kMaxBranchLinearSubNodeLength = 5;
    static final int kMaxLinearMatchLength = 16;
    static final int kMaxOneByteDelta = 191;
    static final int kMaxOneByteValue = 64;
    static final int kMaxThreeByteDelta = 917503;
    static final int kMaxThreeByteValue = 1179647;
    static final int kMaxTwoByteDelta = 12287;
    static final int kMaxTwoByteValue = 6911;
    static final int kMinLinearMatch = 16;
    static final int kMinOneByteValueLead = 16;
    static final int kMinThreeByteDeltaLead = 240;
    static final int kMinThreeByteValueLead = 108;
    static final int kMinTwoByteDeltaLead = 192;
    static final int kMinTwoByteValueLead = 81;
    static final int kMinValueLead = 32;
    private static final int kValueIsFinal = 1;
    private static Result[] valueResults_ = {Result.INTERMEDIATE_VALUE, Result.FINAL_VALUE};
    private byte[] bytes_;
    private int pos_;
    private int remainingMatchLength_ = -1;
    private int root_;

    public static final class State {
        private byte[] bytes;
        private int pos;
        private int remainingMatchLength;
        private int root;
    }

    public BytesTrie(byte[] trieBytes, int offset) {
        this.bytes_ = trieBytes;
        this.root_ = offset;
        this.pos_ = offset;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public BytesTrie reset() {
        this.pos_ = this.root_;
        this.remainingMatchLength_ = -1;
        return this;
    }

    public BytesTrie saveState(State state) {
        state.bytes = this.bytes_;
        state.root = this.root_;
        state.pos = this.pos_;
        state.remainingMatchLength = this.remainingMatchLength_;
        return this;
    }

    public BytesTrie resetToState(State state) {
        if (this.bytes_ == state.bytes && this.bytes_ != null && this.root_ == state.root) {
            this.pos_ = state.pos;
            this.remainingMatchLength_ = state.remainingMatchLength;
            return this;
        }
        throw new IllegalArgumentException("incompatible trie state");
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

    public Result current() {
        int node;
        int pos = this.pos_;
        if (pos < 0) {
            return Result.NO_MATCH;
        }
        return (this.remainingMatchLength_ >= 0 || (node = this.bytes_[pos] & 255) < 32) ? Result.NO_VALUE : valueResults_[node & 1];
    }

    public Result first(int inByte) {
        this.remainingMatchLength_ = -1;
        if (inByte < 0) {
            inByte += 256;
        }
        return nextImpl(this.root_, inByte);
    }

    public Result next(int inByte) {
        int node;
        int pos = this.pos_;
        if (pos < 0) {
            return Result.NO_MATCH;
        }
        if (inByte < 0) {
            inByte += 256;
        }
        int length = this.remainingMatchLength_;
        if (length < 0) {
            return nextImpl(pos, inByte);
        }
        byte[] bArr = this.bytes_;
        int pos2 = pos + 1;
        if (inByte == (bArr[pos] & 255)) {
            int length2 = length - 1;
            this.remainingMatchLength_ = length2;
            this.pos_ = pos2;
            return (length2 >= 0 || (node = bArr[pos2] & 255) < 32) ? Result.NO_VALUE : valueResults_[node & 1];
        }
        stop();
        return Result.NO_MATCH;
    }

    /* JADX INFO: Multiple debug info for r9v2 byte: [D('sIndex' int), D('inByte' byte)] */
    public Result next(byte[] s, int sIndex, int sLimit) {
        int node;
        if (sIndex >= sLimit) {
            return current();
        }
        int node2 = this.pos_;
        if (node2 < 0) {
            return Result.NO_MATCH;
        }
        int length = this.remainingMatchLength_;
        while (sIndex != sLimit) {
            int pos = sIndex + 1;
            byte inByte = s[sIndex];
            if (length < 0) {
                this.remainingMatchLength_ = length;
                while (true) {
                    byte[] bArr = this.bytes_;
                    int pos2 = node2 + 1;
                    int node3 = bArr[node2] & 255;
                    if (node3 < 16) {
                        Result result = branchNext(pos2, node3, inByte & 255);
                        if (result == Result.NO_MATCH) {
                            return Result.NO_MATCH;
                        }
                        if (pos == sLimit) {
                            return result;
                        }
                        if (result == Result.FINAL_VALUE) {
                            stop();
                            return Result.NO_MATCH;
                        }
                        inByte = s[pos];
                        node2 = this.pos_;
                        pos++;
                    } else if (node3 < 32) {
                        int length2 = node3 - 16;
                        if (inByte != bArr[pos2]) {
                            stop();
                            return Result.NO_MATCH;
                        }
                        length = length2 - 1;
                        node2 = pos2 + 1;
                        sIndex = pos;
                    } else if ((node3 & 1) != 0) {
                        stop();
                        return Result.NO_MATCH;
                    } else {
                        node2 = skipValue(pos2, node3);
                    }
                }
            } else if (inByte != this.bytes_[node2]) {
                stop();
                return Result.NO_MATCH;
            } else {
                node2++;
                length--;
                sIndex = pos;
            }
        }
        this.remainingMatchLength_ = length;
        this.pos_ = node2;
        return (length >= 0 || (node = this.bytes_[node2] & 255) < 32) ? Result.NO_VALUE : valueResults_[node & 1];
    }

    public int getValue() {
        int pos = this.pos_;
        byte[] bArr = this.bytes_;
        return readValue(bArr, pos + 1, (bArr[pos] & 255) >> 1);
    }

    public long getUniqueValue() {
        int pos = this.pos_;
        if (pos < 0) {
            return 0;
        }
        return (findUniqueValue(this.bytes_, (this.remainingMatchLength_ + pos) + 1, 0) << 31) >> 31;
    }

    public int getNextBytes(Appendable out) {
        int pos;
        int pos2 = this.pos_;
        if (pos2 < 0) {
            return 0;
        }
        if (this.remainingMatchLength_ >= 0) {
            append(out, this.bytes_[pos2] & 255);
            return 1;
        }
        int pos3 = pos2 + 1;
        int node = this.bytes_[pos2] & 255;
        if (node >= 32) {
            if ((node & 1) != 0) {
                return 0;
            }
            int pos4 = skipValue(pos3, node);
            pos3 = pos4 + 1;
            node = this.bytes_[pos4] & 255;
        }
        if (node < 16) {
            if (node == 0) {
                pos = pos3 + 1;
                node = this.bytes_[pos3] & 255;
            } else {
                pos = pos3;
            }
            int node2 = node + 1;
            getNextBranchBytes(this.bytes_, pos, node2, out);
            return node2;
        }
        append(out, this.bytes_[pos3] & 255);
        return 1;
    }

    /* Return type fixed from 'android.icu.util.BytesTrie$Iterator' to match base method */
    @Override // java.lang.Iterable
    public java.util.Iterator<Entry> iterator() {
        return new Iterator(this.bytes_, this.pos_, this.remainingMatchLength_, 0);
    }

    public Iterator iterator(int maxStringLength) {
        return new Iterator(this.bytes_, this.pos_, this.remainingMatchLength_, maxStringLength);
    }

    public static Iterator iterator(byte[] trieBytes, int offset, int maxStringLength) {
        return new Iterator(trieBytes, offset, -1, maxStringLength);
    }

    public static final class Entry {
        private byte[] bytes;
        private int length;
        public int value;

        private Entry(int capacity) {
            this.bytes = new byte[capacity];
        }

        public int bytesLength() {
            return this.length;
        }

        public byte byteAt(int index) {
            return this.bytes[index];
        }

        public void copyBytesTo(byte[] dest, int destOffset) {
            System.arraycopy(this.bytes, 0, dest, destOffset, this.length);
        }

        public ByteBuffer bytesAsByteBuffer() {
            return ByteBuffer.wrap(this.bytes, 0, this.length).asReadOnlyBuffer();
        }

        private void ensureCapacity(int len) {
            byte[] bArr = this.bytes;
            if (bArr.length < len) {
                byte[] newBytes = new byte[Math.min(bArr.length * 2, len * 2)];
                System.arraycopy(this.bytes, 0, newBytes, 0, this.length);
                this.bytes = newBytes;
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
        private void append(byte[] b, int off, int len) {
            ensureCapacity(this.length + len);
            System.arraycopy(b, off, this.bytes, this.length, len);
            this.length += len;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void truncateString(int newLength) {
            this.length = newLength;
        }
    }

    public static final class Iterator implements java.util.Iterator<Entry> {
        private byte[] bytes_;
        private Entry entry_;
        private int initialPos_;
        private int initialRemainingMatchLength_;
        private int maxLength_;
        private int pos_;
        private int remainingMatchLength_;
        private ArrayList<Long> stack_;

        private Iterator(byte[] trieBytes, int offset, int remainingMatchLength, int maxStringLength) {
            this.stack_ = new ArrayList<>();
            this.bytes_ = trieBytes;
            this.initialPos_ = offset;
            this.pos_ = offset;
            this.initialRemainingMatchLength_ = remainingMatchLength;
            this.remainingMatchLength_ = remainingMatchLength;
            this.maxLength_ = maxStringLength;
            int i = this.maxLength_;
            this.entry_ = new Entry(i == 0 ? 32 : i);
            int length = this.remainingMatchLength_;
            if (length >= 0) {
                int length2 = length + 1;
                int i2 = this.maxLength_;
                if (i2 > 0 && length2 > i2) {
                    length2 = this.maxLength_;
                }
                this.entry_.append(this.bytes_, this.pos_, length2);
                this.pos_ += length2;
                this.remainingMatchLength_ -= length2;
            }
        }

        public Iterator reset() {
            this.pos_ = this.initialPos_;
            this.remainingMatchLength_ = this.initialRemainingMatchLength_;
            int length = this.remainingMatchLength_ + 1;
            int i = this.maxLength_;
            if (i > 0 && length > i) {
                length = this.maxLength_;
            }
            this.entry_.truncateString(length);
            this.pos_ += length;
            this.remainingMatchLength_ -= length;
            this.stack_.clear();
            return this;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos_ >= 0 || !this.stack_.isEmpty();
        }

        @Override // java.util.Iterator
        public Entry next() {
            int i;
            int pos = this.pos_;
            boolean isFinal = true;
            if (pos < 0) {
                if (!this.stack_.isEmpty()) {
                    ArrayList<Long> arrayList = this.stack_;
                    long top = arrayList.remove(arrayList.size() - 1).longValue();
                    int length = (int) top;
                    int pos2 = (int) (top >> 32);
                    this.entry_.truncateString(65535 & length);
                    int length2 = length >>> 16;
                    if (length2 > 1) {
                        pos = branchNext(pos2, length2);
                        if (pos < 0) {
                            return this.entry_;
                        }
                    } else {
                        this.entry_.append(this.bytes_[pos2]);
                        pos = pos2 + 1;
                    }
                } else {
                    throw new NoSuchElementException();
                }
            }
            if (this.remainingMatchLength_ >= 0) {
                return truncateAndStop();
            }
            while (true) {
                int pos3 = pos + 1;
                int node = this.bytes_[pos] & 255;
                if (node >= 32) {
                    if ((node & 1) == 0) {
                        isFinal = false;
                    }
                    this.entry_.value = BytesTrie.readValue(this.bytes_, pos3, node >> 1);
                    if (isFinal || (this.maxLength_ > 0 && this.entry_.length == this.maxLength_)) {
                        this.pos_ = -1;
                    } else {
                        this.pos_ = BytesTrie.skipValue(pos3, node);
                    }
                    return this.entry_;
                } else if (this.maxLength_ > 0 && this.entry_.length == this.maxLength_) {
                    return truncateAndStop();
                } else {
                    if (node < 16) {
                        if (node == 0) {
                            node = this.bytes_[pos3] & 255;
                            pos3++;
                        }
                        int pos4 = branchNext(pos3, node + 1);
                        if (pos4 < 0) {
                            return this.entry_;
                        }
                        pos = pos4;
                    } else {
                        int length3 = (node - 16) + 1;
                        if (this.maxLength_ <= 0 || this.entry_.length + length3 <= (i = this.maxLength_)) {
                            this.entry_.append(this.bytes_, pos3, length3);
                            pos = pos3 + length3;
                        } else {
                            Entry entry = this.entry_;
                            entry.append(this.bytes_, pos3, i - entry.length);
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

        /* JADX INFO: Multiple debug info for r11v2 byte: [D('pos' int), D('trieByte' byte)] */
        private int branchNext(int pos, int length) {
            while (length > 5) {
                int pos2 = pos + 1;
                this.stack_.add(Long.valueOf((((long) BytesTrie.skipDelta(this.bytes_, pos2)) << 32) | ((long) ((length - (length >> 1)) << 16)) | ((long) this.entry_.length)));
                length >>= 1;
                pos = BytesTrie.jumpByDelta(this.bytes_, pos2);
            }
            byte[] bArr = this.bytes_;
            int pos3 = pos + 1;
            byte trieByte = bArr[pos];
            int pos4 = pos3 + 1;
            int node = bArr[pos3] & 255;
            boolean isFinal = (node & 1) != 0;
            int value = BytesTrie.readValue(this.bytes_, pos4, node >> 1);
            int pos5 = BytesTrie.skipValue(pos4, node);
            this.stack_.add(Long.valueOf((((long) pos5) << 32) | ((long) ((length - 1) << 16)) | ((long) this.entry_.length)));
            this.entry_.append(trieByte);
            if (!isFinal) {
                return pos5 + value;
            }
            this.pos_ = -1;
            this.entry_.value = value;
            return -1;
        }
    }

    private void stop() {
        this.pos_ = -1;
    }

    /* access modifiers changed from: private */
    public static int readValue(byte[] bytes, int pos, int leadByte) {
        if (leadByte < 81) {
            return leadByte - 16;
        }
        if (leadByte < 108) {
            return ((leadByte - 81) << 8) | (bytes[pos] & 255);
        }
        if (leadByte < 126) {
            return ((leadByte - 108) << 16) | ((bytes[pos] & 255) << 8) | (bytes[pos + 1] & 255);
        }
        if (leadByte == 126) {
            return ((bytes[pos] & 255) << 16) | ((bytes[pos + 1] & 255) << 8) | (bytes[pos + 2] & 255);
        }
        return (bytes[pos] << 24) | ((bytes[pos + 1] & 255) << 16) | ((bytes[pos + 2] & 255) << 8) | (bytes[pos + 3] & 255);
    }

    /* access modifiers changed from: private */
    public static int skipValue(int pos, int leadByte) {
        if (leadByte < 162) {
            return pos;
        }
        if (leadByte < 216) {
            return pos + 1;
        }
        if (leadByte < 252) {
            return pos + 2;
        }
        return pos + ((leadByte >> 1) & 1) + 3;
    }

    private static int skipValue(byte[] bytes, int pos) {
        return skipValue(pos + 1, bytes[pos] & 255);
    }

    /* access modifiers changed from: private */
    public static int jumpByDelta(byte[] bytes, int pos) {
        int pos2 = pos + 1;
        int delta = bytes[pos] & 255;
        if (delta >= 192) {
            if (delta < 240) {
                delta = ((delta - 192) << 8) | (bytes[pos2] & 255);
                pos2++;
            } else if (delta < 254) {
                delta = ((delta - 240) << 16) | ((bytes[pos2] & 255) << 8) | (bytes[pos2 + 1] & 255);
                pos2 += 2;
            } else if (delta == 254) {
                delta = ((bytes[pos2] & 255) << 16) | ((bytes[pos2 + 1] & 255) << 8) | (bytes[pos2 + 2] & 255);
                pos2 += 3;
            } else {
                delta = (bytes[pos2] << 24) | ((bytes[pos2 + 1] & 255) << 16) | ((bytes[pos2 + 2] & 255) << 8) | (bytes[pos2 + 3] & 255);
                pos2 += 4;
            }
        }
        return pos2 + delta;
    }

    /* access modifiers changed from: private */
    public static int skipDelta(byte[] bytes, int pos) {
        int pos2 = pos + 1;
        int delta = bytes[pos] & 255;
        if (delta < 192) {
            return pos2;
        }
        if (delta < 240) {
            return pos2 + 1;
        }
        if (delta < 254) {
            return pos2 + 2;
        }
        return pos2 + (delta & 1) + 3;
    }

    private Result branchNext(int pos, int length, int inByte) {
        Result result;
        int delta;
        if (length == 0) {
            length = this.bytes_[pos] & 255;
            pos++;
        }
        int length2 = length + 1;
        while (length2 > 5) {
            byte[] bArr = this.bytes_;
            int pos2 = pos + 1;
            if (inByte < (bArr[pos] & 255)) {
                length2 >>= 1;
                pos = jumpByDelta(bArr, pos2);
            } else {
                length2 -= length2 >> 1;
                pos = skipDelta(bArr, pos2);
            }
        }
        do {
            byte[] bArr2 = this.bytes_;
            int pos3 = pos + 1;
            if (inByte == (bArr2[pos] & 255)) {
                int node = bArr2[pos3] & 255;
                if ((node & 1) != 0) {
                    result = Result.FINAL_VALUE;
                } else {
                    int pos4 = pos3 + 1;
                    int node2 = node >> 1;
                    if (node2 < 81) {
                        delta = node2 - 16;
                    } else if (node2 < 108) {
                        delta = ((node2 - 81) << 8) | (bArr2[pos4] & 255);
                        pos4++;
                    } else if (node2 < 126) {
                        delta = ((node2 - 108) << 16) | ((bArr2[pos4] & 255) << 8) | (bArr2[pos4 + 1] & 255);
                        pos4 += 2;
                    } else if (node2 == 126) {
                        delta = ((bArr2[pos4] & 255) << 16) | ((bArr2[pos4 + 1] & 255) << 8) | (bArr2[pos4 + 2] & 255);
                        pos4 += 3;
                    } else {
                        delta = (bArr2[pos4] << 24) | ((bArr2[pos4 + 1] & 255) << 16) | ((bArr2[pos4 + 2] & 255) << 8) | (bArr2[pos4 + 3] & 255);
                        pos4 += 4;
                    }
                    pos3 = pos4 + delta;
                    int node3 = this.bytes_[pos3] & 255;
                    result = node3 >= 32 ? valueResults_[node3 & 1] : Result.NO_VALUE;
                }
                this.pos_ = pos3;
                return result;
            }
            length2--;
            pos = skipValue(bArr2, pos3);
        } while (length2 > 1);
        byte[] bArr3 = this.bytes_;
        int pos5 = pos + 1;
        if (inByte == (bArr3[pos] & 255)) {
            this.pos_ = pos5;
            int node4 = bArr3[pos5] & 255;
            return node4 >= 32 ? valueResults_[node4 & 1] : Result.NO_VALUE;
        }
        stop();
        return Result.NO_MATCH;
    }

    private Result nextImpl(int node, int inByte) {
        int node2;
        while (true) {
            byte[] bArr = this.bytes_;
            int pos = node + 1;
            int node3 = bArr[node] & 255;
            if (node3 < 16) {
                return branchNext(pos, node3, inByte);
            }
            if (node3 < 32) {
                int length = node3 - 16;
                int pos2 = pos + 1;
                if (inByte == (bArr[pos] & 255)) {
                    int length2 = length - 1;
                    this.remainingMatchLength_ = length2;
                    this.pos_ = pos2;
                    return (length2 >= 0 || (node2 = bArr[pos2] & 255) < 32) ? Result.NO_VALUE : valueResults_[node2 & 1];
                }
            } else if ((node3 & 1) != 0) {
                break;
            } else {
                node = skipValue(pos, node3);
            }
        }
        stop();
        return Result.NO_MATCH;
    }

    private static long findUniqueValueFromBranch(byte[] bytes, int pos, int length, long uniqueValue) {
        while (length > 5) {
            int pos2 = pos + 1;
            uniqueValue = findUniqueValueFromBranch(bytes, jumpByDelta(bytes, pos2), length >> 1, uniqueValue);
            if (uniqueValue == 0) {
                return 0;
            }
            length -= length >> 1;
            pos = skipDelta(bytes, pos2);
        }
        while (true) {
            int pos3 = pos + 1;
            int pos4 = pos3 + 1;
            int node = bytes[pos3] & 255;
            boolean isFinal = (node & 1) != 0;
            int value = readValue(bytes, pos4, node >> 1);
            int pos5 = skipValue(pos4, node);
            if (!isFinal) {
                uniqueValue = findUniqueValue(bytes, pos5 + value, uniqueValue);
                if (uniqueValue == 0) {
                    return 0;
                }
            } else if (uniqueValue == 0) {
                uniqueValue = (((long) value) << 1) | 1;
            } else if (value != ((int) (uniqueValue >> 1))) {
                return 0;
            }
            length--;
            if (length <= 1) {
                return (((long) (pos5 + 1)) << 33) | (8589934591L & uniqueValue);
            }
            pos = pos5;
        }
    }

    private static long findUniqueValue(byte[] bytes, int pos, long uniqueValue) {
        int pos2;
        while (true) {
            int pos3 = pos2 + 1;
            int node = bytes[pos2] & 255;
            if (node < 16) {
                if (node == 0) {
                    node = bytes[pos3] & 255;
                    pos3++;
                }
                uniqueValue = findUniqueValueFromBranch(bytes, pos3, node + 1, uniqueValue);
                if (uniqueValue == 0) {
                    return 0;
                }
                pos2 = (int) (uniqueValue >>> 33);
            } else if (node < 32) {
                pos2 = pos3 + (node - 16) + 1;
            } else {
                boolean isFinal = (node & 1) != 0;
                int value = readValue(bytes, pos3, node >> 1);
                if (uniqueValue == 0) {
                    uniqueValue = (((long) value) << 1) | 1;
                } else if (value != ((int) (uniqueValue >> 1))) {
                    return 0;
                }
                if (isFinal) {
                    return uniqueValue;
                }
                pos2 = skipValue(pos3, node);
            }
        }
    }

    private static void getNextBranchBytes(byte[] bytes, int pos, int length, Appendable out) {
        while (length > 5) {
            int pos2 = pos + 1;
            getNextBranchBytes(bytes, jumpByDelta(bytes, pos2), length >> 1, out);
            length -= length >> 1;
            pos = skipDelta(bytes, pos2);
        }
        do {
            append(out, bytes[pos] & 255);
            pos = skipValue(bytes, pos + 1);
            length--;
        } while (length > 1);
        append(out, bytes[pos] & 255);
    }

    private static void append(Appendable out, int c) {
        try {
            out.append((char) c);
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }
}
