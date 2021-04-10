package android.icu.text;

import java.nio.BufferOverflowException;
import java.util.Arrays;

public final class Edits {
    private char[] array = new char[100];
    private int delta;
    private int length;
    private int numChanges;

    public void reset() {
        this.numChanges = 0;
        this.delta = 0;
        this.length = 0;
    }

    private void setLastUnit(int i) {
        this.array[this.length - 1] = (char) i;
    }

    private int lastUnit() {
        int i = this.length;
        if (i > 0) {
            return this.array[i - 1];
        }
        return 65535;
    }

    public void addUnchanged(int i) {
        if (i >= 0) {
            int lastUnit = lastUnit();
            if (lastUnit < 4095) {
                int i2 = 4095 - lastUnit;
                if (i2 >= i) {
                    setLastUnit(lastUnit + i);
                    return;
                } else {
                    setLastUnit(4095);
                    i -= i2;
                }
            }
            while (i >= 4096) {
                append(4095);
                i -= 4096;
            }
            if (i > 0) {
                append(i - 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("addUnchanged(" + i + "): length must not be negative");
    }

    public void addReplace(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("addReplace(" + i + ", " + i2 + "): both lengths must be non-negative");
        } else if (i != 0 || i2 != 0) {
            this.numChanges++;
            int i7 = i2 - i;
            if (i7 != 0) {
                if ((i7 <= 0 || (i6 = this.delta) < 0 || i7 <= Integer.MAX_VALUE - i6) && (i7 >= 0 || (i5 = this.delta) >= 0 || i7 >= Integer.MIN_VALUE - i5)) {
                    this.delta += i7;
                } else {
                    throw new IndexOutOfBoundsException();
                }
            }
            if (i > 0 && i <= 6 && i2 <= 7) {
                int i8 = (i << 12) | (i2 << 9);
                int lastUnit = lastUnit();
                if (4095 >= lastUnit || lastUnit >= 28671 || (lastUnit & -512) != i8 || (lastUnit & 511) >= 511) {
                    append(i8);
                } else {
                    setLastUnit(lastUnit + 1);
                }
            } else if (i < 61 && i2 < 61) {
                append((i << 6) | 28672 | i2);
            } else if (this.array.length - this.length >= 5 || growArray()) {
                int i9 = this.length + 1;
                if (i < 61) {
                    i3 = (i << 6) | 28672;
                } else if (i <= 32767) {
                    i3 = 32576;
                    this.array[i9] = (char) (i | 32768);
                    i9++;
                } else {
                    i3 = (((i >> 30) + 62) << 6) | 28672;
                    char[] cArr = this.array;
                    int i10 = i9 + 1;
                    cArr[i9] = (char) ((i >> 15) | 32768);
                    i9 = i10 + 1;
                    cArr[i10] = (char) (i | 32768);
                }
                if (i2 < 61) {
                    i4 = i3 | i2;
                } else if (i2 <= 32767) {
                    i4 = i3 | 61;
                    this.array[i9] = (char) (i2 | 32768);
                    i9++;
                } else {
                    i4 = ((i2 >> 30) + 62) | i3;
                    char[] cArr2 = this.array;
                    int i11 = i9 + 1;
                    cArr2[i9] = (char) ((i2 >> 15) | 32768);
                    i9 = i11 + 1;
                    cArr2[i11] = (char) (i2 | 32768);
                }
                this.array[this.length] = (char) i4;
                this.length = i9;
            }
        }
    }

    private void append(int i) {
        if (this.length < this.array.length || growArray()) {
            char[] cArr = this.array;
            int i2 = this.length;
            this.length = i2 + 1;
            cArr[i2] = (char) i;
        }
    }

    private boolean growArray() {
        char[] cArr = this.array;
        int i = Integer.MAX_VALUE;
        if (cArr.length == 100) {
            i = 2000;
        } else if (cArr.length == Integer.MAX_VALUE) {
            throw new BufferOverflowException();
        } else if (cArr.length < 1073741823) {
            i = cArr.length * 2;
        }
        char[] cArr2 = this.array;
        if (i - cArr2.length >= 5) {
            this.array = Arrays.copyOf(cArr2, i);
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

    public static final class Iterator {
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

        private Iterator(char[] cArr, int i, boolean z, boolean z2) {
            this.array = cArr;
            this.length = i;
            this.onlyChanges_ = z;
            this.coarse = z2;
        }

        private int readLength(int i) {
            if (i < 61) {
                return i;
            }
            if (i < 62) {
                char[] cArr = this.array;
                int i2 = this.index;
                this.index = i2 + 1;
                return cArr[i2] & 32767;
            }
            char[] cArr2 = this.array;
            int i3 = this.index;
            int i4 = ((i & 1) << 30) | ((cArr2[i3] & 32767) << 15) | (cArr2[i3 + 1] & 32767);
            this.index = i3 + 2;
            return i4;
        }

        private void updateNextIndexes() {
            this.srcIndex += this.oldLength_;
            if (this.changed) {
                this.replIndex += this.newLength_;
            }
            this.destIndex += this.newLength_;
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

        private boolean next(boolean z) {
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
                    if (i4 >= this.length || (c2 = this.array[i4]) > 4095) {
                        this.newLength_ = this.oldLength_;
                    } else {
                        this.index = i4 + 1;
                        this.oldLength_ += c2 + 1;
                    }
                }
                this.newLength_ = this.oldLength_;
                if (!z) {
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
            if (c2 <= 28671) {
                int i6 = c2 >> '\f';
                int i7 = (c2 >> '\t') & 7;
                int i8 = (c2 & 511) + 1;
                if (this.coarse) {
                    this.oldLength_ = i6 * i8;
                    this.newLength_ = i8 * i7;
                } else {
                    this.oldLength_ = i6;
                    this.newLength_ = i7;
                    if (i8 > 1) {
                        this.remaining = i8;
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
                int i9 = this.index;
                if (i9 >= this.length || (c = this.array[i9]) <= 4095) {
                    return true;
                }
                this.index = i9 + 1;
                if (c <= 28671) {
                    int i10 = (c & 511) + 1;
                    this.oldLength_ += (c >> '\f') * i10;
                    this.newLength_ += ((c >> '\t') & 7) * i10;
                } else {
                    this.oldLength_ += readLength((c >> 6) & 63);
                    this.newLength_ += readLength(c & '?');
                }
            }
            return true;
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

        public String toString() {
            new StringBuilder();
            super.toString();
            throw null;
        }
    }

    public Iterator getCoarseIterator() {
        return new Iterator(this.array, this.length, false, true);
    }
}
