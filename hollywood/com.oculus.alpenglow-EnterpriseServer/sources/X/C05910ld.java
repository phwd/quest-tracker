package X;

import java.io.IOException;

/* renamed from: X.0ld  reason: invalid class name and case insensitive filesystem */
public class C05910ld extends IOException {
    public static final long serialVersionUID = 123;
    public C05880lZ _location;

    public String A03() {
        return null;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        C05880lZ r3 = this._location;
        String A03 = A03();
        if (r3 == null && A03 == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (A03 != null) {
            sb.append(A03);
        }
        if (r3 != null) {
            sb.append('\n');
            sb.append(" at ");
            sb.append(r3.toString());
        }
        return sb.toString();
    }

    public String toString() {
        return AnonymousClass006.A07(getClass().getName(), ": ", getMessage());
    }

    public C05910ld(String str) {
        super(str);
    }

    public C05910ld(String str, C05880lZ r2, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = r2;
    }
}
