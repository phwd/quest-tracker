package java.lang;

public class ReflectiveOperationException extends Exception {
    static final long serialVersionUID = 123456789;

    public ReflectiveOperationException() {
    }

    public ReflectiveOperationException(String str) {
        super(str);
    }

    public ReflectiveOperationException(String str, Throwable th) {
        super(str, th);
    }

    public ReflectiveOperationException(Throwable th) {
        super(th);
    }
}
