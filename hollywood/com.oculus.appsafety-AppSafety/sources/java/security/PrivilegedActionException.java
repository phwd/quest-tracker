package java.security;

import android.icu.text.PluralRules;

public class PrivilegedActionException extends Exception {
    private static final long serialVersionUID = 4724086851538908602L;
    private Exception exception;

    public PrivilegedActionException(Exception exception2) {
        super((Throwable) null);
        this.exception = exception2;
    }

    public Exception getException() {
        return this.exception;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.exception;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String s = getClass().getName();
        if (this.exception == null) {
            return s;
        }
        return s + PluralRules.KEYWORD_RULE_SEPARATOR + this.exception.toString();
    }
}
