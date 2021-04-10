package X;

import java.io.IOException;
import java.io.StringWriter;

/* renamed from: X.13R  reason: invalid class name */
public abstract class AnonymousClass13R {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            AnonymousClass14L r1 = new AnonymousClass14L(stringWriter);
            r1.A03 = true;
            AnonymousClass14E.A0H.A03(r1, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
