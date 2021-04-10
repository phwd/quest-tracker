package X;

import java.io.IOException;
import java.io.StringWriter;

/* renamed from: X.hm  reason: case insensitive filesystem */
public abstract class AbstractC0241hm {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            h3 h3Var = new h3(stringWriter);
            h3Var.A03 = true;
            hA.A0H.A03(h3Var, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
