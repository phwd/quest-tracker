package X;

public class hJ implements CharSequence {
    public char[] A00;

    public final char charAt(int i) {
        return this.A00[i];
    }

    public final int length() {
        return this.A00.length;
    }

    public final CharSequence subSequence(int i, int i2) {
        return new String(this.A00, i, i2 - i);
    }
}
