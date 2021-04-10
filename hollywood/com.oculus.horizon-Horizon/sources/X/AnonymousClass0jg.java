package X;

import java.io.IOException;

/* renamed from: X.0jg  reason: invalid class name */
public class AnonymousClass0jg extends IOException {
    public static final long serialVersionUID = 123;
    public AnonymousClass0jc _location;

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        AnonymousClass0jc r2 = this._location;
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
        return AnonymousClass006.A07(getClass().getName(), ": ", getMessage());
    }

    public AnonymousClass0jg(String str) {
        super(str);
    }

    public AnonymousClass0jg(String str, AnonymousClass0jc r2, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = r2;
    }
}
