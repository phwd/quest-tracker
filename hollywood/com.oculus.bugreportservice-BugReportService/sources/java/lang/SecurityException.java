package java.lang;

public class SecurityException extends RuntimeException {
    private static final long serialVersionUID = 6878364983674394167L;

    public SecurityException() {
    }

    public SecurityException(String str) {
        super(str);
    }

    public SecurityException(String str, Throwable th) {
        super(str, th);
    }
}
