package android.icu.util;

import android.icu.text.UTF16;
import android.icu.util.BytesTrie;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public final class CharsTrie implements Cloneable, Iterable {
    private static BytesTrie.Result[] valueResults_ = {BytesTrie.Result.INTERMEDIATE_VALUE, BytesTrie.Result.FINAL_VALUE};
    private CharSequence chars_;
    private int pos_;
    private int remainingMatchLength_ = -1;
    private int root_;

    public static final class State {
        private CharSequence chars;
        private int pos;
        private int remainingMatchLength;
        private int root;
    }

    /* access modifiers changed from: private */
    public static int skipNodeValue(int i, int i2) {
        return i2 >= 16448 ? i2 < 32704 ? i + 1 : i + 2 : i;
    }

    /* access modifiers changed from: private */
    public static int skipValue(int i, int i2) {
        return i2 >= 16384 ? i2 < 32767 ? i + 1 : i + 2 : i;
    }

    public CharsTrie(CharSequence charSequence, int i) {
        this.chars_ = charSequence;
        this.root_ = i;
        this.pos_ = i;
    }

    public Object clone() {
        return super.clone();
    }

    public CharsTrie reset() {
        this.pos_ = this.root_;
        this.remainingMatchLength_ = -1;
        return this;
    }

    public CharsTrie saveState(State state) {
        state.chars = this.chars_;
        state.root = this.root_;
        state.pos = this.pos_;
        state.remainingMatchLength = this.remainingMatchLength_;
        return this;
    }

    public CharsTrie resetToState(State state) {
        if (this.chars_ == state.chars && this.chars_ != null && this.root_ == state.root) {
            this.pos_ = state.pos;
            this.remainingMatchLength_ = state.remainingMatchLength;
            return this;
        }
        throw new IllegalArgumentException("incompatible trie state");
    }

    public BytesTrie.Result first(int i) {
        this.remainingMatchLength_ = -1;
        return nextImpl(this.root_, i);
    }

    public BytesTrie.Result firstForCodePoint(int i) {
        if (i <= 65535) {
            return first(i);
        }
        if (first(UTF16.getLeadSurrogate(i)).hasNext()) {
            return next(UTF16.getTrailSurrogate(i));
        }
        return BytesTrie.Result.NO_MATCH;
    }

    public BytesTrie.Result next(int i) {
        char charAt;
        int i2 = this.pos_;
        if (i2 < 0) {
            return BytesTrie.Result.NO_MATCH;
        }
        int i3 = this.remainingMatchLength_;
        if (i3 < 0) {
            return nextImpl(i2, i);
        }
        int i4 = i2 + 1;
        if (i == this.chars_.charAt(i2)) {
            int i5 = i3 - 1;
            this.remainingMatchLength_ = i5;
            this.pos_ = i4;
            return (i5 >= 0 || (charAt = this.chars_.charAt(i4)) < '@') ? BytesTrie.Result.NO_VALUE : valueResults_[charAt >> 15];
        }
        stop();
        return BytesTrie.Result.NO_MATCH;
    }

    public BytesTrie.Result nextForCodePoint(int i) {
        if (i <= 65535) {
            return next(i);
        }
        if (next(UTF16.getLeadSurrogate(i)).hasNext()) {
            return next(UTF16.getTrailSurrogate(i));
        }
        return BytesTrie.Result.NO_MATCH;
    }

    public int getValue() {
        int i = this.pos_;
        int i2 = i + 1;
        char charAt = this.chars_.charAt(i);
        return (32768 & charAt) != 0 ? readValue(this.chars_, i2, charAt & 32767) : readNodeValue(this.chars_, i2, charAt);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new Iterator(this.chars_, this.pos_, this.remainingMatchLength_, 0);
    }

    public static final class Entry {
        public CharSequence chars;
        public int value;

        private Entry() {
        }
    }

    public static final class Iterator implements java.util.Iterator {
        private CharSequence chars_;
        private Entry entry_;
        private int initialPos_;
        private int initialRemainingMatchLength_;
        private int maxLength_;
        private int pos_;
        private int remainingMatchLength_;
        private boolean skipValue_;
        private ArrayList stack_;
        private StringBuilder str_;

        private Iterator(CharSequence charSequence, int i, int i2, int i3) {
            this.str_ = new StringBuilder();
            this.entry_ = new Entry();
            this.stack_ = new ArrayList();
            this.chars_ = charSequence;
            this.initialPos_ = i;
            this.pos_ = i;
            this.initialRemainingMatchLength_ = i2;
            this.remainingMatchLength_ = i2;
            this.maxLength_ = i3;
            int i4 = this.remainingMatchLength_;
            if (i4 >= 0) {
                int i5 = i4 + 1;
                int i6 = this.maxLength_;
                if (i6 > 0 && i5 > i6) {
                    i5 = i6;
                }
                StringBuilder sb = this.str_;
                CharSequence charSequence2 = this.chars_;
                int i7 = this.pos_;
                sb.append(charSequence2, i7, i7 + i5);
                this.pos_ += i5;
                this.remainingMatchLength_ -= i5;
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
            if (i3 < 0) {
                if (!this.stack_.isEmpty()) {
                    ArrayList arrayList = this.stack_;
                    long longValue = ((Long) arrayList.remove(arrayList.size() - 1)).longValue();
                    int i4 = (int) longValue;
                    int i5 = (int) (longValue >> 32);
                    this.str_.setLength(65535 & i4);
                    int i6 = i4 >>> 16;
                    if (i6 > 1) {
                        i3 = branchNext(i5, i6);
                        if (i3 < 0) {
                            return this.entry_;
                        }
                    } else {
                        this.str_.append(this.chars_.charAt(i5));
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
                char charAt = this.chars_.charAt(i3);
                if (charAt >= 64) {
                    boolean z = false;
                    if (this.skipValue_) {
                        i7 = CharsTrie.skipNodeValue(i7, charAt);
                        charAt &= 63;
                        this.skipValue_ = false;
                    } else {
                        if ((32768 & charAt) != 0) {
                            z = true;
                        }
                        if (z) {
                            this.entry_.value = CharsTrie.readValue(this.chars_, i7, charAt & 32767);
                        } else {
                            this.entry_.value = CharsTrie.readNodeValue(this.chars_, i7, charAt);
                        }
                        if (z || (this.maxLength_ > 0 && this.str_.length() == this.maxLength_)) {
                            this.pos_ = -1;
                        } else {
                            this.pos_ = i7 - 1;
                            this.skipValue_ = true;
                        }
                        Entry entry = this.entry_;
                        entry.chars = this.str_;
                        return entry;
                    }
                }
                if (this.maxLength_ > 0 && this.str_.length() == this.maxLength_) {
                    return truncateAndStop();
                }
                if (charAt < 48) {
                    if (charAt == 0) {
                        i2 = i7 + 1;
                        charAt = this.chars_.charAt(i7);
                    } else {
                        i2 = i7;
                    }
                    i3 = branchNext(i2, charAt + 1);
                    if (i3 < 0) {
                        return this.entry_;
                    }
                } else {
                    int i8 = (charAt - 48) + 1;
                    if (this.maxLength_ <= 0 || this.str_.length() + i8 <= (i = this.maxLength_)) {
                        i3 = i8 + i7;
                        this.str_.append(this.chars_, i7, i3);
                    } else {
                        StringBuilder sb = this.str_;
                        sb.append(this.chars_, i7, (i + i7) - sb.length());
                        return truncateAndStop();
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
            entry.chars = this.str_;
            entry.value = -1;
            return entry;
        }

        private int branchNext(int i, int i2) {
            while (i2 > 5) {
                int i3 = i + 1;
                int i4 = i2 >> 1;
                this.stack_.add(Long.valueOf((((long) CharsTrie.skipDelta(this.chars_, i3)) << 32) | ((long) ((i2 - i4) << 16)) | ((long) this.str_.length())));
                i = CharsTrie.jumpByDelta(this.chars_, i3);
                i2 = i4;
            }
            int i5 = i + 1;
            char charAt = this.chars_.charAt(i);
            int i6 = i5 + 1;
            char charAt2 = this.chars_.charAt(i5);
            boolean z = (32768 & charAt2) != 0;
            int i7 = charAt2 & 32767;
            int readValue = CharsTrie.readValue(this.chars_, i6, i7);
            int skipValue = CharsTrie.skipValue(i6, i7);
            this.stack_.add(Long.valueOf((((long) skipValue) << 32) | ((long) ((i2 - 1) << 16)) | ((long) this.str_.length())));
            this.str_.append(charAt);
            if (!z) {
                return skipValue + readValue;
            }
            this.pos_ = -1;
            Entry entry = this.entry_;
            entry.chars = this.str_;
            entry.value = readValue;
            return -1;
        }
    }

    private void stop() {
        this.pos_ = -1;
    }

    /* access modifiers changed from: private */
    public static int readValue(CharSequence charSequence, int i, int i2) {
        int i3;
        char c;
        if (i2 < 16384) {
            return i2;
        }
        if (i2 < 32767) {
            i3 = (i2 - 16384) << 16;
            c = charSequence.charAt(i);
        } else {
            i3 = charSequence.charAt(i) << 16;
            c = charSequence.charAt(i + 1);
        }
        return i3 | c;
    }

    private static int skipValue(CharSequence charSequence, int i) {
        return skipValue(i + 1, charSequence.charAt(i) & 32767);
    }

    /* access modifiers changed from: private */
    public static int readNodeValue(CharSequence charSequence, int i, int i2) {
        int i3;
        char c;
        if (i2 < 16448) {
            return (i2 >> 6) - 1;
        }
        if (i2 < 32704) {
            i3 = ((i2 & 32704) - 16448) << 10;
            c = charSequence.charAt(i);
        } else {
            i3 = charSequence.charAt(i) << 16;
            c = charSequence.charAt(i + 1);
        }
        return c | i3;
    }

    /* access modifiers changed from: private */
    public static int jumpByDelta(CharSequence charSequence, int i) {
        int i2 = i + 1;
        int charAt = charSequence.charAt(i);
        if (charAt >= 64512) {
            if (charAt == 65535) {
                charAt = (charSequence.charAt(i2) << 16) | charSequence.charAt(i2 + 1);
                i2 += 2;
            } else {
                charAt = ((charAt - 64512) << 16) | charSequence.charAt(i2);
                i2++;
            }
        }
        return i2 + charAt;
    }

    /* access modifiers changed from: private */
    public static int skipDelta(CharSequence charSequence, int i) {
        int i2 = i + 1;
        char charAt = charSequence.charAt(i);
        if (charAt >= 64512) {
            return charAt == 65535 ? i2 + 2 : i2 + 1;
        }
        return i2;
    }

    private BytesTrie.Result branchNext(int i, int i2, int i3) {
        BytesTrie.Result result;
        if (i2 == 0) {
            i2 = this.chars_.charAt(i);
            i++;
        }
        int i4 = i2 + 1;
        while (i4 > 5) {
            int i5 = i + 1;
            if (i3 < this.chars_.charAt(i)) {
                i4 >>= 1;
                i = jumpByDelta(this.chars_, i5);
            } else {
                i4 -= i4 >> 1;
                i = skipDelta(this.chars_, i5);
            }
        }
        do {
            int i6 = i + 1;
            if (i3 == this.chars_.charAt(i)) {
                int charAt = this.chars_.charAt(i6);
                if ((32768 & charAt) != 0) {
                    result = BytesTrie.Result.FINAL_VALUE;
                } else {
                    int i7 = i6 + 1;
                    if (charAt >= 16384) {
                        if (charAt < 32767) {
                            charAt = ((charAt - 16384) << 16) | this.chars_.charAt(i7);
                            i7++;
                        } else {
                            charAt = (this.chars_.charAt(i7) << 16) | this.chars_.charAt(i7 + 1);
                            i7 += 2;
                        }
                    }
                    i6 = i7 + charAt;
                    char charAt2 = this.chars_.charAt(i6);
                    result = charAt2 >= '@' ? valueResults_[charAt2 >> 15] : BytesTrie.Result.NO_VALUE;
                }
                this.pos_ = i6;
                return result;
            }
            i4--;
            i = skipValue(this.chars_, i6);
        } while (i4 > 1);
        int i8 = i + 1;
        if (i3 == this.chars_.charAt(i)) {
            this.pos_ = i8;
            char charAt3 = this.chars_.charAt(i8);
            return charAt3 >= '@' ? valueResults_[charAt3 >> 15] : BytesTrie.Result.NO_VALUE;
        }
        stop();
        return BytesTrie.Result.NO_MATCH;
    }

    private BytesTrie.Result nextImpl(int i, int i2) {
        char charAt;
        int i3 = i + 1;
        int charAt2 = this.chars_.charAt(i);
        while (charAt2 >= 48) {
            if (charAt2 < 64) {
                int i4 = charAt2 - 48;
                int i5 = i3 + 1;
                if (i2 == this.chars_.charAt(i3)) {
                    int i6 = i4 - 1;
                    this.remainingMatchLength_ = i6;
                    this.pos_ = i5;
                    return (i6 >= 0 || (charAt = this.chars_.charAt(i5)) < '@') ? BytesTrie.Result.NO_VALUE : valueResults_[charAt >> 15];
                }
            } else if ((32768 & charAt2) == 0) {
                i3 = skipNodeValue(i3, charAt2);
                charAt2 &= 63;
            }
            stop();
            return BytesTrie.Result.NO_MATCH;
        }
        return branchNext(i3, charAt2, i2);
    }
}
