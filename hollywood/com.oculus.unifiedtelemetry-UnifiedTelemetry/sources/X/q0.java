package X;

import java.io.IOException;

public class q0 extends IOException {
    public static final long serialVersionUID = 123;
    public pw _location;

    public String A03() {
        return null;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        pw pwVar = this._location;
        String A03 = A03();
        if (pwVar == null && A03 == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (A03 != null) {
            sb.append(A03);
        }
        if (pwVar != null) {
            sb.append('\n');
            sb.append(" at ");
            sb.append(pwVar.toString());
        }
        return sb.toString();
    }

    public final String toString() {
        return AnonymousClass06.A05(getClass().getName(), ": ", getMessage());
    }

    public q0(String str) {
        super(str);
    }

    public q0(String str, pw pwVar, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = pwVar;
    }
}
