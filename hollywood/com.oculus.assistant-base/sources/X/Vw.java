package X;

public final class Vw extends Exception {
    public Exception mChildException;

    public final String getMessage() {
        return this.mChildException.getMessage();
    }

    public Vw(Exception exc) {
        this.mChildException = exc;
    }
}
