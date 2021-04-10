package java.security;

public class InvalidKeyException extends KeyException {
    private static final long serialVersionUID = 5698479920593359816L;

    public InvalidKeyException() {
    }

    public InvalidKeyException(String str) {
        super(str);
    }

    public InvalidKeyException(String str, Throwable th) {
        super(str, th);
    }

    public InvalidKeyException(Throwable th) {
        super(th);
    }
}
