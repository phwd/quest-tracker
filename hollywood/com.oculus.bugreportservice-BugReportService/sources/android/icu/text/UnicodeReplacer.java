package android.icu.text;

interface UnicodeReplacer {
    int replace(Replaceable replaceable, int i, int i2, int[] iArr);

    String toReplacerPattern(boolean z);
}
