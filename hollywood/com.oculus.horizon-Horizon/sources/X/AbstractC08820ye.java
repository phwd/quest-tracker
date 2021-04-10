package X;

import java.io.IOException;
import java.io.StringWriter;

/* renamed from: X.0ye  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08820ye {
    public final C03100c7 A02() {
        if (this instanceof C03100c7) {
            return (C03100c7) this;
        }
        StringBuilder sb = new StringBuilder("Not a JSON Object: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            C09130zU r1 = new C09130zU(stringWriter);
            r1.A03 = true;
            C09080zN.A0H.A03(r1, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
