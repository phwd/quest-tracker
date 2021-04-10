package android.icu.text;

public interface RbnfLenientScanner {
    boolean allIgnorable(String str);

    int[] findText(String str, String str2, int i);

    int prefixLength(String str, String str2);
}
