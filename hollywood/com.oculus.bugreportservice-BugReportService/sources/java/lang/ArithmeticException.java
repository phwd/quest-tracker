package java.lang;

public class ArithmeticException extends RuntimeException {
    private static final long serialVersionUID = 2256477558314496007L;

    public ArithmeticException() {
    }

    public ArithmeticException(String str) {
        super(str);
    }
}
