package X;

public class WZ extends Throwable {
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }

    public WZ() {
        super("Failure occurred while trying to finish a future.");
    }
}
