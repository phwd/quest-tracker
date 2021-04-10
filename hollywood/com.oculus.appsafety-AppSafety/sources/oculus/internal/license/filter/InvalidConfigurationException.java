package oculus.internal.license.filter;

public final class InvalidConfigurationException extends IllegalArgumentException {
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }

    public InvalidConfigurationException(String message) {
        super(message);
    }
}
