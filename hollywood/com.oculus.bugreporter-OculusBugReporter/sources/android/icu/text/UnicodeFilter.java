package android.icu.text;

public abstract class UnicodeFilter implements UnicodeMatcher {
    public abstract boolean contains(int i);

    @Override // android.icu.text.UnicodeMatcher
    public int matches(Replaceable text, int[] offset, int limit, boolean incremental) {
        if (offset[0] < limit) {
            int c = text.char32At(offset[0]);
            if (contains(c)) {
                offset[0] = offset[0] + UTF16.getCharCount(c);
                return 2;
            }
        }
        if (offset[0] > limit && contains(text.char32At(offset[0]))) {
            offset[0] = offset[0] - 1;
            if (offset[0] >= 0) {
                offset[0] = offset[0] - (UTF16.getCharCount(text.char32At(offset[0])) - 1);
            }
            return 2;
        } else if (!incremental || offset[0] != limit) {
            return 0;
        } else {
            return 1;
        }
    }

    @Deprecated
    protected UnicodeFilter() {
    }
}
