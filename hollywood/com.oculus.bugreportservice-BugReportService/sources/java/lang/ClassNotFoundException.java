package java.lang;

public class ClassNotFoundException extends ReflectiveOperationException {
    private static final long serialVersionUID = 9176873029745254542L;
    private Throwable ex;

    public ClassNotFoundException() {
        super((Throwable) null);
    }

    public ClassNotFoundException(String str) {
        super(str, null);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.ex;
    }
}
