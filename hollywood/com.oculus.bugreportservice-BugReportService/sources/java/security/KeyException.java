package java.security;

public class KeyException extends GeneralSecurityException {
    private static final long serialVersionUID = -7483676942812432108L;

    public KeyException() {
    }

    public KeyException(String str) {
        super(str);
    }

    public KeyException(String str, Throwable th) {
        super(str, th);
    }

    public KeyException(Throwable th) {
        super(th);
    }
}
