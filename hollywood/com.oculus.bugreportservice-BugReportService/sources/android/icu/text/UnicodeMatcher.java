package android.icu.text;

public interface UnicodeMatcher {
    void addMatchSetTo(UnicodeSet unicodeSet);

    int matches(Replaceable replaceable, int[] iArr, int i, boolean z);

    boolean matchesIndexValue(int i);

    String toPattern(boolean z);
}
