package oculus.internal.license;

public class LicenseFormatException extends Exception {
    public LicenseFormatException(Throwable cause) {
        super(cause);
    }

    public LicenseFormatException(String message) {
        super(message);
    }
}
