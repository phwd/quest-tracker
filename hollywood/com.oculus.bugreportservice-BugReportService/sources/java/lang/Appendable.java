package java.lang;

public interface Appendable {
    Appendable append(char c);

    Appendable append(CharSequence charSequence);

    Appendable append(CharSequence charSequence, int i, int i2);
}
