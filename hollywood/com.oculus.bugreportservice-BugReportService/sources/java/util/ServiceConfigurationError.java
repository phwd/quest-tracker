package java.util;

public class ServiceConfigurationError extends Error {
    private static final long serialVersionUID = 74132770414881L;

    public ServiceConfigurationError(String str) {
        super(str);
    }

    public ServiceConfigurationError(String str, Throwable th) {
        super(str, th);
    }
}
