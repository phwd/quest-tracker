package X;

import java.util.Locale;

/* renamed from: X.Cy  reason: case insensitive filesystem */
public final class C0079Cy {
    public final D1 A00;

    public final String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.A00.A01()));
    }

    public C0079Cy(D1 d1) {
        this.A00 = d1;
    }
}
