package java.lang;

public interface CharSequence {
    char charAt(int i);

    int length();

    CharSequence subSequence(int i, int i2);

    String toString();
}
