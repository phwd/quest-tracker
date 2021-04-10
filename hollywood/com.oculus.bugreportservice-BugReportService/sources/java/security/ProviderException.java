package java.security;

public class ProviderException extends RuntimeException {
    private static final long serialVersionUID = 5256023526693665674L;

    public ProviderException() {
    }

    public ProviderException(String str) {
        super(str);
    }

    public ProviderException(Throwable th) {
        super(th);
    }
}
