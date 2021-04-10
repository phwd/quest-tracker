package java.lang;

public class StringIndexOutOfBoundsException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = -6762910422159637258L;

    public StringIndexOutOfBoundsException() {
    }

    public StringIndexOutOfBoundsException(String str) {
        super(str);
    }

    public StringIndexOutOfBoundsException(int i) {
        super("String index out of range: " + i);
    }

    StringIndexOutOfBoundsException(String str, int i) {
        this(str.length(), i);
    }

    StringIndexOutOfBoundsException(int i, int i2) {
        super("length=" + i + "; index=" + i2);
    }

    StringIndexOutOfBoundsException(String str, int i, int i2) {
        this(str.length(), i, i2);
    }

    StringIndexOutOfBoundsException(int i, int i2, int i3) {
        super("length=" + i + "; regionStart=" + i2 + "; regionLength=" + i3);
    }
}
