package X;

import java.io.IOException;
import java.io.StringWriter;

public abstract class M4 {
    public final U2 A02() {
        if (this instanceof U2) {
            return (U2) this;
        }
        StringBuilder sb = new StringBuilder("Not a JSON Object: ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            mm mmVar = new mm(stringWriter);
            mmVar.A03 = true;
            C0433hz.A0H.A03(mmVar, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public String A03() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }
}
