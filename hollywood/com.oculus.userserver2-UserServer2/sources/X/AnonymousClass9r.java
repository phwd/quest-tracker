package X;

import java.io.IOException;

/* renamed from: X.9r  reason: invalid class name */
public class AnonymousClass9r extends IOException {
    public static final long serialVersionUID = 123;
    public AnonymousClass9u _location;

    public AnonymousClass9r(String str, AnonymousClass9u r2, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = r2;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        AnonymousClass9u r2 = this._location;
        if (r2 == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        sb.append('\n');
        sb.append(" at ");
        sb.append(r2.toString());
        return sb.toString();
    }

    public final String toString() {
        return AnonymousClass06.A04(getClass().getName(), ": ", getMessage());
    }
}
