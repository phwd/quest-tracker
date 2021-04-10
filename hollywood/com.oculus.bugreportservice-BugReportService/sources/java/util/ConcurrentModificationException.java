package java.util;

public class ConcurrentModificationException extends RuntimeException {
    private static final long serialVersionUID = -3666751008965953603L;

    public ConcurrentModificationException() {
    }

    public ConcurrentModificationException(String str) {
        super(str);
    }
}
