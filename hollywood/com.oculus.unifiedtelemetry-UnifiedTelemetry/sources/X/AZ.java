package X;

public class AZ extends Throwable {
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }

    public AZ() {
        super("Failure occurred while trying to finish a future.");
    }
}
