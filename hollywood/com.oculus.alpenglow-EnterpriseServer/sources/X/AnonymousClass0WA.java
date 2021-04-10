package X;

import java.io.IOException;
import java.io.Writer;

/* renamed from: X.0WA  reason: invalid class name */
public class AnonymousClass0WA extends AnonymousClass0Bd<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r3, Boolean bool) throws IOException {
        String str;
        Boolean bool2 = bool;
        if (bool2 == null) {
            r3.A0A();
            return;
        }
        AnonymousClass0GL.A04(r3);
        AnonymousClass0GL.A03(r3);
        Writer writer = r3.A08;
        if (bool2.booleanValue()) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Boolean A02(AnonymousClass0Fo r3) throws IOException {
        boolean A0O;
        Integer A0D = r3.A0D();
        if (A0D == AnonymousClass007.A0I) {
            r3.A0L();
            return null;
        }
        if (A0D == AnonymousClass007.A0F) {
            A0O = Boolean.parseBoolean(r3.A0F());
        } else {
            A0O = r3.A0O();
        }
        return Boolean.valueOf(A0O);
    }
}
