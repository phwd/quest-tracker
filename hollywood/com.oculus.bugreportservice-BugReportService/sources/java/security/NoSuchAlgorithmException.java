package java.security;

public class NoSuchAlgorithmException extends GeneralSecurityException {
    private static final long serialVersionUID = -7443947487218346562L;

    public NoSuchAlgorithmException() {
    }

    public NoSuchAlgorithmException(String str) {
        super(str);
    }

    public NoSuchAlgorithmException(String str, Throwable th) {
        super(str, th);
    }

    public NoSuchAlgorithmException(Throwable th) {
        super(th);
    }
}
