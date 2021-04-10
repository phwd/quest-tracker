package java.lang;

public class RuntimeException extends Exception {
    static final long serialVersionUID = -7034897190745766939L;

    public RuntimeException() {
    }

    public RuntimeException(String str) {
        super(str);
    }

    public RuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public RuntimeException(Throwable th) {
        super(th);
    }
}
