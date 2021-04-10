package oculus.internal.license.filter;

public final class UnrecognizedConditionException extends Exception {
    public UnrecognizedConditionException(Throwable cause) {
        super(cause);
    }

    public UnrecognizedConditionException(String message) {
        super(message);
    }
}
