package X;

public final class D4 extends RuntimeException {
    public D4(Exception exc) {
        super("An exception was thrown by an Executor", exc);
    }
}
