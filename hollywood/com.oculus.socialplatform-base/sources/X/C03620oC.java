package X;

import com.oculus.deviceconfigclient.DeviceConfigClient;
import java.io.IOException;

/* renamed from: X.0oC  reason: invalid class name and case insensitive filesystem */
public class C03620oC extends IOException {
    public static final long serialVersionUID = 123;
    public AnonymousClass0o8 _location;

    public String A03() {
        return null;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = DeviceConfigClient.NOT_AVAILABLE;
        }
        AnonymousClass0o8 r3 = this._location;
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

    public final String toString() {
        return AnonymousClass006.A09(getClass().getName(), ": ", getMessage());
    }

    public C03620oC(String str) {
        super(str);
    }

    public C03620oC(String str, AnonymousClass0o8 r2, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = r2;
    }
}
