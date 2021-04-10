package oculus.internal.license.store;

public class LicenseStoreException extends Exception {
    public LicenseStoreException(Throwable cause) {
        super(cause);
    }

    public LicenseStoreException(String message) {
        super(message);
    }

    public LicenseStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
