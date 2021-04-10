package android.icu.text;

import java.nio.BufferOverflowException;
import java.sql.Types;
import java.util.Arrays;

public final class Edits {
    private static final int LENGTH_IN_1TRAIL = 61;
    private static final int LENGTH_IN_2TRAIL = 62;
    private static final int MAX_SHORT_CHANGE = 28671;
    private static final int MAX_SHORT_CHANGE_NEW_LENGTH = 7;
    private static final int MAX_SHORT_CHANGE_OLD_LENGTH = 6;
    private static final int MAX_UNCHANGED = 4095;
    private static final int MAX_UNCHANGED_LENGTH = 4096;
    private static final int SHORT_CHANGE_NUM_MASK = 511;
    private static final int STACK_CAPACITY = 100;
    private char[] array = new char[100];
    private int delta;
    private int length;
    private int numChanges;

    public void reset() {
        this.numChanges = 0;
        this.delta = 0;
        this.length = 0;
    }

    private void setLastUnit(int last) {
        this.array[this.length - 1] = (char) last;
    }

    private int lastUnit() {
        int i = this.length;
        return i > 0 ? this.array[i - 1] : DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH;
    }

    public void addUnchanged(int unchangedLength) {
        if (unchangedLength >= 0) {
            int last = lastUnit();
            if (last < 4095) {
                int remaining = 4095 - last;
                if (remaining >= unchangedLength) {
                    setLastUnit(last + unchangedLength);
                    return;
                } else {
                    setLastUnit(4095);
                    unchangedLength -= remaining;
                }
            }
            while (unchangedLength >= 4096) {
                append(4095);
                unchangedLength -= 4096;
            }
            if (unchangedLength > 0) {
                append(unchangedLength - 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("addUnchanged(" + unchangedLength + "): length must not be negative");
    }

    public void addReplace(int oldLength, int newLength) {
        int head;
        int head2;
        int i;
        int i2;
        if (oldLength < 0 || newLength < 0) {
            throw new IllegalArgumentException("addReplace(" + oldLength + ", " + newLength + "): both lengths must be non-negative");
        } else if (oldLength != 0 || newLength != 0) {
            this.numChanges++;
            int newDelta = newLength - oldLength;
            if (newDelta != 0) {
                if ((newDelta <= 0 || (i2 = this.delta) < 0 || newDelta <= Integer.MAX_VALUE - i2) && (newDelta >= 0 || (i = this.delta) >= 0 || newDelta >= Integer.MIN_VALUE - i)) {
                    this.delta += newDelta;
                } else {
                    throw new IndexOutOfBoundsException();
                }
            }
            if (oldLength > 0 && oldLength <= 6 && newLength <= 7) {
                int u = (oldLength << 12) | (newLength << 9);
                int last = lastUnit();
                if (4095 >= last || last >= MAX_SHORT_CHANGE || (last & -512) != u || (last & 511) >= 511) {
                    append(u);
                } else {
                    setLastUnit(last + 1);
                }
            } else if (oldLength < 61 && newLength < 61) {
                append((oldLength << 6) | 28672 | newLength);
            } else if (this.array.length - this.length >= 5 || growArray()) {
                int limit = this.length + 1;
                if (oldLength < 61) {
                    head = (oldLength << 6) | 28672;
                } else if (oldLength <= 32767) {
                    head = 28672 | 3904;
                    this.array[limit] = (char) (oldLength | 32768);
                    limit++;
                } else {
                    head = (((oldLength >> 30) + 62) << 6) | 28672;
                    char[] cArr = this.array;
                    int limit2 = limit + 1;
                    cArr[limit] = (char) ((oldLength >> 15) | 32768);
                    limit = limit2 + 1;
                    cArr[limit2] = (char) (oldLength | 32768);
                }
                if (newLength < 61) {
                    head2 = head | newLength;
                } else if (newLength <= 32767) {
                    this.array[limit] = (char) (newLength | 32768);
                    head2 = head | 61;
                    limit++;
                } else {
                    char[] cArr2 = this.array;
                    int limit3 = limit + 1;
                    cArr2[limit] = (char) ((newLength >> 15) | 32768);
                    limit = limit3 + 1;
                    cArr2[limit3] = (char) (newLength | 32768);
                    head2 = head | ((newLength >> 30) + 62);
                }
                this.array[this.length] = (char) head2;
                this.length = limit;
            }
        }
    }

    private void append(int r) {
        if (this.length < this.array.length || growArray()) {
            char[] cArr = this.array;
            int i = this.length;
            this.length = i + 1;
            cArr[i] = (char) r;
        }
    }

    private boolean growArray() {
        int newCapacity;
        char[] cArr = this.array;
        if (cArr.length == 100) {
            newCapacity = Types.JAVA_OBJECT;
        } else if (cArr.length == Integer.MAX_VALUE) {
            throw new BufferOverflowException();
        } else if (cArr.length >= 1073741823) {
            newCapacity = Integer.MAX_VALUE;
        } else {
            newCapacity = cArr.length * 2;
        }
        char[] cArr2 = this.array;
        if (newCapacity - cArr2.length >= 5) {
            this.array = Arrays.copyOf(cArr2, newCapacity);
            return true;
        }
        throw new BufferOverflowException();
    }

    public int lengthDelta() {
        return this.delta;
    }

    public boolean hasChanges() {
        return this.numChanges != 0;
    }

    public int numberOfChanges() {
        return this.numChanges;
    }

    public static final class Iterator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final char[] array;
        private boolean changed;
        private final boolean coarse;
        private int destIndex;
        private int dir;
        private int index;
        private final int length;
        private int newLength_;
        private int oldLength_;
        private final boolean onlyChanges_;
        private int remaining;
        private int replIndex;
        private int srcIndex;

        private Iterator(char[] a, int len, boolean oc, boolean crs) {
            this.array = a;
            this.length = len;
            this.onlyChanges_ = oc;
            this.coarse = crs;
        }

        private int readLength(int head) {
            if (head < 61) {
                return head;
            }
            if (head < 62) {
                char[] cArr = this.array;
                int i = this.index;
                this.index = i + 1;
                return cArr[i] & 32767;
            }
            char[] cArr2 = this.array;
            int i2 = this.index;
            int len = ((head & 1) << 30) | ((cArr2[i2] & 32767) << 15) | (cArr2[i2 + 1] & 32767);
            this.index = i2 + 2;
            return len;
        }

        private void updateNextIndexes() {
            this.srcIndex += this.oldLength_;
            if (this.changed) {
                this.replIndex += this.newLength_;
            }
            this.destIndex += this.newLength_;
        }

        private void updatePreviousIndexes() {
            this.srcIndex -= this.oldLength_;
            if (this.changed) {
                this.replIndex -= this.newLength_;
            }
            this.destIndex -= this.newLength_;
        }

        private boolean noNext() {
            this.dir = 0;
            this.changed = false;
            this.newLength_ = 0;
            this.oldLength_ = 0;
            return false;
        }

        public boolean next() {
            return next(this.onlyChanges_);
        }

        private boolean next(boolean onlyChanges) {
            char c;
            int i = this.dir;
            if (i > 0) {
                updateNextIndexes();
            } else if (i >= 0 || this.remaining <= 0) {
                this.dir = 1;
            } else {
                this.index++;
                this.dir = 1;
                return true;
            }
            int i2 = this.remaining;
            if (i2 >= 1) {
                if (i2 > 1) {
                    this.remaining = i2 - 1;
                    return true;
                }
                this.remaining = 0;
            }
            int i3 = this.index;
            if (i3 >= this.length) {
                return noNext();
            }
            char[] cArr = this.array;
            this.index = i3 + 1;
            char c2 = cArr[i3];
            if (c2 <= 4095) {
                this.changed = false;
                this.oldLength_ = c2 + 1;
                while (true) {
                    int i4 = this.index;
                    if (i4 >= this.length) {
                        break;
                    }
                    char c3 = this.array[i4];
                    c2 = c3;
                    if (c3 > 4095) {
                        break;
                    }
                    this.index = i4 + 1;
                    this.oldLength_ += c2 + 1;
                }
                this.newLength_ = this.oldLength_;
                if (!onlyChanges) {
                    return true;
                }
                updateNextIndexes();
                int i5 = this.index;
                if (i5 >= this.length) {
                    return noNext();
                }
                this.index = i5 + 1;
            }
            this.changed = true;
            if (c2 <= Edits.MAX_SHORT_CHANGE) {
                int oldLen = c2 >> '\f';
                int newLen = (c2 >> '\t') & 7;
                int num = (c2 & 511) + 1;
                if (this.coarse) {
                    this.oldLength_ = num * oldLen;
                    this.newLength_ = num * newLen;
                } else {
                    this.oldLength_ = oldLen;
                    this.newLength_ = newLen;
                    if (num > 1) {
                        this.remaining = num;
                    }
                    return true;
                }
            } else {
                this.oldLength_ = readLength((c2 >> 6) & 63);
                this.newLength_ = readLength(c2 & '?');
                if (!this.coarse) {
                    return true;
                }
            }
            while (true) {
                int i6 = this.index;
                if (i6 >= this.length || (c = this.array[i6]) <= 4095) {
                    return true;
                }
                this.index = i6 + 1;
                if (c <= Edits.MAX_SHORT_CHANGE) {
                    int num2 = (c & 511) + 1;
                    this.oldLength_ += (c >> '\f') * num2;
                    this.newLength_ += ((c >> '\t') & 7) * num2;
                } else {
                    this.oldLength_ += readLength((c >> 6) & 63);
                    this.newLength_ += readLength(c & '?');
                }
            }
            return true;
        }

        private boolean previous() {
            char c;
            char c2;
            char c3;
            int i = this.dir;
            if (i >= 0) {
                if (i > 0) {
                    if (this.remaining > 0) {
                        this.index--;
                        this.dir = -1;
                        return true;
                    }
                    updateNextIndexes();
                }
                this.dir = -1;
            }
            int i2 = this.remaining;
            if (i2 > 0) {
                if (i2 <= (this.array[this.index] & 511)) {
                    this.remaining = i2 + 1;
                    updatePreviousIndexes();
                    return true;
                }
                this.remaining = 0;
            }
            int i3 = this.index;
            if (i3 <= 0) {
                return noNext();
            }
            char[] cArr = this.array;
            int i4 = i3 - 1;
            this.index = i4;
            char c4 = cArr[i4];
            if (c4 <= 4095) {
                this.changed = false;
                this.oldLength_ = c4 + 1;
                while (true) {
                    int i5 = this.index;
                    if (i5 <= 0 || (c3 = this.array[i5 - 1]) > 4095) {
                        this.newLength_ = this.oldLength_;
                        updatePreviousIndexes();
                    } else {
                        this.index = i5 - 1;
                        this.oldLength_ += c3 + 1;
                    }
                }
                this.newLength_ = this.oldLength_;
                updatePreviousIndexes();
                return true;
            }
            this.changed = true;
            if (c4 <= Edits.MAX_SHORT_CHANGE) {
                int oldLen = c4 >> '\f';
                int newLen = (c4 >> '\t') & 7;
                int num = (c4 & 511) + 1;
                if (this.coarse) {
                    this.oldLength_ = num * oldLen;
                    this.newLength_ = num * newLen;
                } else {
                    this.oldLength_ = oldLen;
                    this.newLength_ = newLen;
                    if (num > 1) {
                        this.remaining = 1;
                    }
                    updatePreviousIndexes();
                    return true;
                }
            } else {
                if (c4 <= 32767) {
                    this.oldLength_ = readLength((c4 >> 6) & 63);
                    this.newLength_ = readLength(c4 & '?');
                } else {
                    do {
                        char[] cArr2 = this.array;
                        int i6 = this.index - 1;
                        this.index = i6;
                        c2 = cArr2[i6];
                    } while (c2 > 32767);
                    int headIndex = this.index;
                    this.index = headIndex + 1;
                    this.oldLength_ = readLength((c2 >> 6) & 63);
                    this.newLength_ = readLength(c2 & '?');
                    this.index = headIndex;
                }
                if (!this.coarse) {
                    updatePreviousIndexes();
                    return true;
                }
            }
            while (true) {
                int i7 = this.index;
                if (i7 <= 0 || (c = this.array[i7 - 1]) <= 4095) {
                    updatePreviousIndexes();
                } else {
                    this.index = i7 - 1;
                    if (c <= Edits.MAX_SHORT_CHANGE) {
                        int num2 = (c & 511) + 1;
                        this.oldLength_ += (c >> '\f') * num2;
                        this.newLength_ += ((c >> '\t') & 7) * num2;
                    } else if (c <= 32767) {
                        int headIndex2 = this.index;
                        this.index = headIndex2 + 1;
                        this.oldLength_ += readLength((c >> 6) & 63);
                        this.newLength_ += readLength(c & '?');
                        this.index = headIndex2;
                    }
                }
            }
            updatePreviousIndexes();
            return true;
        }

        public boolean findSourceIndex(int i) {
            return findIndex(i, true) == 0;
        }

        public boolean findDestinationIndex(int i) {
            return findIndex(i, false) == 0;
        }

        private int findIndex(int i, boolean findSource) {
            int spanLength;
            int spanStart;
            int spanLength2;
            int spanStart2;
            if (i < 0) {
                return -1;
            }
            if (findSource) {
                spanStart = this.srcIndex;
                spanLength = this.oldLength_;
            } else {
                spanStart = this.destIndex;
                spanLength = this.newLength_;
            }
            if (i < spanStart) {
                if (i >= spanStart / 2) {
                    while (true) {
                        previous();
                        int spanStart3 = findSource ? this.srcIndex : this.destIndex;
                        if (i >= spanStart3) {
                            return 0;
                        }
                        if (this.remaining > 0) {
                            int spanLength3 = findSource ? this.oldLength_ : this.newLength_;
                            int i2 = this.remaining;
                            int num = ((this.array[this.index] & 511) + 1) - i2;
                            if (i >= spanStart3 - (num * spanLength3)) {
                                int n = (((spanStart3 - i) - 1) / spanLength3) + 1;
                                this.srcIndex -= this.oldLength_ * n;
                                int i3 = this.replIndex;
                                int i4 = this.newLength_;
                                this.replIndex = i3 - (n * i4);
                                this.destIndex -= i4 * n;
                                this.remaining = i2 + n;
                                return 0;
                            }
                            this.srcIndex -= this.oldLength_ * num;
                            int i5 = this.replIndex;
                            int i6 = this.newLength_;
                            this.replIndex = i5 - (num * i6);
                            this.destIndex -= i6 * num;
                            this.remaining = 0;
                        }
                    }
                } else {
                    this.dir = 0;
                    this.destIndex = 0;
                    this.replIndex = 0;
                    this.srcIndex = 0;
                    this.newLength_ = 0;
                    this.oldLength_ = 0;
                    this.remaining = 0;
                    this.index = 0;
                }
            } else if (i < spanStart + spanLength) {
                return 0;
            }
            while (next(false)) {
                if (findSource) {
                    spanStart2 = this.srcIndex;
                    spanLength2 = this.oldLength_;
                } else {
                    spanStart2 = this.destIndex;
                    spanLength2 = this.newLength_;
                }
                if (i < spanStart2 + spanLength2) {
                    return 0;
                }
                int i7 = this.remaining;
                if (i7 > 1) {
                    if (i < spanStart2 + (i7 * spanLength2)) {
                        int n2 = (i - spanStart2) / spanLength2;
                        this.srcIndex += this.oldLength_ * n2;
                        int i8 = this.replIndex;
                        int i9 = this.newLength_;
                        this.replIndex = i8 + (n2 * i9);
                        this.destIndex += i9 * n2;
                        this.remaining = i7 - n2;
                        return 0;
                    }
                    this.oldLength_ *= i7;
                    this.newLength_ *= i7;
                    this.remaining = 0;
                }
            }
            return 1;
        }

        public int destinationIndexFromSourceIndex(int i) {
            int i2;
            int where = findIndex(i, true);
            if (where < 0) {
                return 0;
            }
            if (where > 0 || i == (i2 = this.srcIndex)) {
                return this.destIndex;
            }
            if (this.changed) {
                return this.destIndex + this.newLength_;
            }
            return this.destIndex + (i - i2);
        }

        public int sourceIndexFromDestinationIndex(int i) {
            int i2;
            int where = findIndex(i, false);
            if (where < 0) {
                return 0;
            }
            if (where > 0 || i == (i2 = this.destIndex)) {
                return this.srcIndex;
            }
            if (this.changed) {
                return this.srcIndex + this.oldLength_;
            }
            return this.srcIndex + (i - i2);
        }

        public boolean hasChange() {
            return this.changed;
        }

        public int oldLength() {
            return this.oldLength_;
        }

        public int newLength() {
            return this.newLength_;
        }

        public int sourceIndex() {
            return this.srcIndex;
        }

        public int replacementIndex() {
            return this.replIndex;
        }

        public int destinationIndex() {
            return this.destIndex;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append("{ src[");
            sb.append(this.srcIndex);
            sb.append("..");
            sb.append(this.srcIndex + this.oldLength_);
            if (this.changed) {
                sb.append("] ⇝ dest[");
            } else {
                sb.append("] ≡ dest[");
            }
            sb.append(this.destIndex);
            sb.append("..");
            sb.append(this.destIndex + this.newLength_);
            if (this.changed) {
                sb.append("], repl[");
                sb.append(this.replIndex);
                sb.append("..");
                sb.append(this.replIndex + this.newLength_);
                sb.append("] }");
            } else {
                sb.append("] (no-change) }");
            }
            return sb.toString();
        }
    }

    public Iterator getCoarseChangesIterator() {
        return new Iterator(this.array, this.length, true, true);
    }

    public Iterator getCoarseIterator() {
        return new Iterator(this.array, this.length, false, true);
    }

    public Iterator getFineChangesIterator() {
        return new Iterator(this.array, this.length, true, false);
    }

    public Iterator getFineIterator() {
        return new Iterator(this.array, this.length, false, false);
    }

    public Edits mergeAndAppend(Edits ab, Edits bc) {
        Iterator abIter = ab.getFineIterator();
        Iterator bcIter = bc.getFineIterator();
        boolean abHasNext = true;
        boolean bcHasNext = true;
        int aLength = 0;
        int ab_bLength = 0;
        int bc_bLength = 0;
        int cLength = 0;
        int pending_aLength = 0;
        int pending_cLength = 0;
        while (true) {
            if (bc_bLength == 0 && bcHasNext) {
                boolean next = bcIter.next();
                bcHasNext = next;
                if (next) {
                    bc_bLength = bcIter.oldLength();
                    cLength = bcIter.newLength();
                    if (bc_bLength == 0) {
                        if (ab_bLength == 0 || !abIter.hasChange()) {
                            addReplace(pending_aLength, pending_cLength + cLength);
                            pending_cLength = 0;
                            pending_aLength = 0;
                        } else {
                            pending_cLength += cLength;
                        }
                    }
                }
            }
            if (ab_bLength == 0) {
                if (!abHasNext) {
                    break;
                }
                boolean next2 = abIter.next();
                abHasNext = next2;
                if (!next2) {
                    break;
                }
                aLength = abIter.oldLength();
                ab_bLength = abIter.newLength();
                if (ab_bLength == 0) {
                    if (bc_bLength == bcIter.oldLength() || !bcIter.hasChange()) {
                        addReplace(pending_aLength + aLength, pending_cLength);
                        pending_cLength = 0;
                        pending_aLength = 0;
                    } else {
                        pending_aLength += aLength;
                    }
                }
            }
            if (bc_bLength == 0) {
                throw new IllegalArgumentException("The bc input string is shorter than the ab output string.");
            } else if (abIter.hasChange() || bcIter.hasChange()) {
                if (abIter.hasChange() || !bcIter.hasChange()) {
                    if (!abIter.hasChange() || bcIter.hasChange()) {
                        if (ab_bLength == bc_bLength) {
                            addReplace(pending_aLength + aLength, pending_cLength + cLength);
                            pending_cLength = 0;
                            pending_aLength = 0;
                            bc_bLength = 0;
                            ab_bLength = 0;
                        }
                    } else if (ab_bLength <= bc_bLength) {
                        addReplace(pending_aLength + aLength, pending_cLength + ab_bLength);
                        pending_cLength = 0;
                        pending_aLength = 0;
                        int i = bc_bLength - ab_bLength;
                        bc_bLength = i;
                        cLength = i;
                        ab_bLength = 0;
                    }
                } else if (ab_bLength >= bc_bLength) {
                    addReplace(pending_aLength + bc_bLength, pending_cLength + cLength);
                    pending_cLength = 0;
                    pending_aLength = 0;
                    int i2 = ab_bLength - bc_bLength;
                    ab_bLength = i2;
                    aLength = i2;
                    bc_bLength = 0;
                }
                pending_aLength += aLength;
                pending_cLength += cLength;
                if (ab_bLength < bc_bLength) {
                    bc_bLength -= ab_bLength;
                    ab_bLength = 0;
                    cLength = 0;
                } else {
                    ab_bLength -= bc_bLength;
                    bc_bLength = 0;
                    aLength = 0;
                }
            } else {
                if (!(pending_aLength == 0 && pending_cLength == 0)) {
                    addReplace(pending_aLength, pending_cLength);
                    pending_cLength = 0;
                    pending_aLength = 0;
                }
                int unchangedLength = aLength <= cLength ? aLength : cLength;
                addUnchanged(unchangedLength);
                int i3 = aLength - unchangedLength;
                aLength = i3;
                ab_bLength = i3;
                int i4 = cLength - unchangedLength;
                cLength = i4;
                bc_bLength = i4;
            }
        }
        if (bc_bLength == 0) {
            if (!(pending_aLength == 0 && pending_cLength == 0)) {
                addReplace(pending_aLength, pending_cLength);
            }
            return this;
        }
        throw new IllegalArgumentException("The ab output string is shorter than the bc input string.");
    }
}
