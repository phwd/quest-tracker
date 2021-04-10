package java.security.cert;

import java.security.GeneralSecurityException;

public class CRLException extends GeneralSecurityException {
    private static final long serialVersionUID = -6694728944094197147L;

    public CRLException() {
    }

    public CRLException(String str) {
        super(str);
    }
}
