package oculus.internal.license;

public class SignerNotFoundException extends Exception {
    public SignerNotFoundException(Throwable cause) {
        super(cause);
    }

    public SignerNotFoundException(String message) {
        super(message);
    }
}
