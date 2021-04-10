package java.lang;

public class NoSuchMethodException extends ReflectiveOperationException {
    private static final long serialVersionUID = 5034388446362600923L;

    public NoSuchMethodException() {
    }

    public NoSuchMethodException(String str) {
        super(str);
    }
}
