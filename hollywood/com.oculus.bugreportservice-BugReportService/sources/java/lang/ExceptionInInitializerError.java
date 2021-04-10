package java.lang;

public class ExceptionInInitializerError extends LinkageError {
    private static final long serialVersionUID = 1521711792217232256L;
    private Throwable exception;

    public ExceptionInInitializerError() {
        initCause(null);
    }

    public ExceptionInInitializerError(String str) {
        super(str);
        initCause(null);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.exception;
    }
}
