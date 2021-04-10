package java.lang.reflect;

public class InvocationTargetException extends ReflectiveOperationException {
    private static final long serialVersionUID = 4085088731926701167L;
    private Throwable target;

    protected InvocationTargetException() {
        super((Throwable) null);
    }

    public Throwable getTargetException() {
        return this.target;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.target;
    }
}
