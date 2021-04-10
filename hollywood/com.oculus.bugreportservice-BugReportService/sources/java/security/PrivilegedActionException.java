package java.security;

public class PrivilegedActionException extends Exception {
    private static final long serialVersionUID = 4724086851538908602L;
    private Exception exception;

    public PrivilegedActionException(Exception exc) {
        super((Throwable) null);
        this.exception = exc;
    }

    public Exception getException() {
        return this.exception;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.exception;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String name = PrivilegedActionException.class.getName();
        if (this.exception == null) {
            return name;
        }
        return name + ": " + this.exception.toString();
    }
}
