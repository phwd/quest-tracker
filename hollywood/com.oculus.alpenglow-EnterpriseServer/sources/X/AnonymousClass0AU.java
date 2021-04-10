package X;

import java.io.IOException;
import java.io.StringWriter;

/* renamed from: X.0AU  reason: invalid class name */
public abstract class AnonymousClass0AU {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            AnonymousClass0GL r1 = new AnonymousClass0GL(stringWriter);
            r1.A04 = true;
            C01220Fb.A0H.A03(r1, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
