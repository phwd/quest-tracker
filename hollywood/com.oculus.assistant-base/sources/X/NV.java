package X;

import java.io.IOException;

public class NV extends IOException {
    public static final long serialVersionUID = 123;
    public NT _location;

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        NT nt = this._location;
        if (nt == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        sb.append('\n');
        sb.append(" at ");
        sb.append(nt.toString());
        return sb.toString();
    }

    public final String toString() {
        return AnonymousClass08.A05(getClass().getName(), ": ", getMessage());
    }

    public NV(String str) {
        super(str);
    }

    public NV(String str, NT nt, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = nt;
    }
}
