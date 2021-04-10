package android.icu.util;

import android.icu.text.UTF16;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CodePointMap implements Iterable<Range> {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public enum RangeOption {
        NORMAL,
        FIXED_LEAD_SURROGATES,
        FIXED_ALL_SURROGATES
    }

    public interface ValueFilter {
        int apply(int i);
    }

    public abstract int get(int i);

    public abstract boolean getRange(int i, ValueFilter valueFilter, Range range);

    public static final class Range {
        private int end = -1;
        private int start = -1;
        private int value = 0;

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public int getValue() {
            return this.value;
        }

        public void set(int start2, int end2, int value2) {
            this.start = start2;
            this.end = end2;
            this.value = value2;
        }
    }

    private final class RangeIterator implements Iterator<Range> {
        private Range range;

        private RangeIterator() {
            this.range = new Range();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return -1 <= this.range.end && this.range.end < 1114111;
        }

        @Override // java.util.Iterator
        public Range next() {
            if (CodePointMap.this.getRange(this.range.end + 1, null, this.range)) {
                return this.range;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class StringIterator {
        @Deprecated
        protected int c = -1;
        @Deprecated
        protected CharSequence s;
        @Deprecated
        protected int sIndex;
        @Deprecated
        protected int value = 0;

        @Deprecated
        protected StringIterator(CharSequence s2, int sIndex2) {
            this.s = s2;
            this.sIndex = sIndex2;
        }

        public void reset(CharSequence s2, int sIndex2) {
            this.s = s2;
            this.sIndex = sIndex2;
            this.c = -1;
            this.value = 0;
        }

        public boolean next() {
            if (this.sIndex >= this.s.length()) {
                return false;
            }
            this.c = Character.codePointAt(this.s, this.sIndex);
            this.sIndex += Character.charCount(this.c);
            this.value = CodePointMap.this.get(this.c);
            return true;
        }

        public boolean previous() {
            int i = this.sIndex;
            if (i <= 0) {
                return false;
            }
            this.c = Character.codePointBefore(this.s, i);
            this.sIndex -= Character.charCount(this.c);
            this.value = CodePointMap.this.get(this.c);
            return true;
        }

        public final int getIndex() {
            return this.sIndex;
        }

        public final int getCodePoint() {
            return this.c;
        }

        public final int getValue() {
            return this.value;
        }
    }

    protected CodePointMap() {
    }

    public boolean getRange(int start, RangeOption option, int surrogateValue, ValueFilter filter, Range range) {
        if (!getRange(start, filter, range)) {
            return false;
        }
        if (option == RangeOption.NORMAL) {
            return true;
        }
        int surrEnd = option == RangeOption.FIXED_ALL_SURROGATES ? 57343 : UTF16.LEAD_SURROGATE_MAX_VALUE;
        int end = range.end;
        if (end < 55295 || start > surrEnd) {
            return true;
        }
        if (range.value == surrogateValue) {
            if (end >= surrEnd) {
                return true;
            }
        } else if (start <= 55295) {
            range.end = 55295;
            return true;
        } else {
            range.value = surrogateValue;
            if (end > surrEnd) {
                range.end = surrEnd;
                return true;
            }
        }
        if (!getRange(surrEnd + 1, filter, range) || range.value != surrogateValue) {
            range.start = start;
            range.end = surrEnd;
            range.value = surrogateValue;
            return true;
        }
        range.start = start;
        return true;
    }

    @Override // java.lang.Iterable
    public Iterator<Range> iterator() {
        return new RangeIterator();
    }

    public StringIterator stringIterator(CharSequence s, int sIndex) {
        return new StringIterator(s, sIndex);
    }
}
