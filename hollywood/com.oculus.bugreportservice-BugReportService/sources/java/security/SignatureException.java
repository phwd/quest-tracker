package java.security;

public class SignatureException extends GeneralSecurityException {
    private static final long serialVersionUID = 7509989324975124438L;

    public SignatureException() {
    }

    public SignatureException(String str) {
        super(str);
    }

    public SignatureException(String str, Throwable th) {
        super(str, th);
    }
}
