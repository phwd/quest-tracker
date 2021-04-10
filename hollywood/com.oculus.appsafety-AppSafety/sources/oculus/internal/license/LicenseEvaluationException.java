package oculus.internal.license;

public class LicenseEvaluationException extends Exception {
    public LicenseEvaluationException(Throwable cause) {
        super(cause);
    }

    public LicenseEvaluationException(String message) {
        super(message);
    }
}
