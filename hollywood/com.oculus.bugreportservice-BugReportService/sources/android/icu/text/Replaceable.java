package android.icu.text;

public interface Replaceable {
    int char32At(int i);

    char charAt(int i);

    void copy(int i, int i2, int i3);

    int length();

    void replace(int i, int i2, String str);
}
