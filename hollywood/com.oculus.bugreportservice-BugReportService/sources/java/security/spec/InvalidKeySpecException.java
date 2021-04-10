package java.security.spec;

import java.security.GeneralSecurityException;

public class InvalidKeySpecException extends GeneralSecurityException {
    private static final long serialVersionUID = 3546139293998810778L;

    public InvalidKeySpecException() {
    }

    public InvalidKeySpecException(String str) {
        super(str);
    }

    public InvalidKeySpecException(String str, Throwable th) {
        super(str, th);
    }
}
