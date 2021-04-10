package X;

import java.io.IOException;
import java.io.Writer;

/* renamed from: X.0dB  reason: invalid class name */
public class AnonymousClass0dB extends AnonymousClass13Y<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r3, Boolean bool) throws IOException {
        String str;
        Boolean bool2 = bool;
        if (bool2 == null) {
            r3.A09();
            return;
        }
        AnonymousClass14L.A03(r3);
        AnonymousClass14L.A02(r3);
        Writer writer = r3.A07;
        if (bool2.booleanValue()) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Boolean A02(AnonymousClass14I r3) throws IOException {
        boolean A0S;
        Integer A0G = r3.A0G();
        if (A0G == AnonymousClass007.A09) {
            r3.A0P();
            return null;
        }
        if (A0G == AnonymousClass007.A06) {
            A0S = Boolean.parseBoolean(r3.A0J());
        } else {
            A0S = r3.A0S();
        }
        return Boolean.valueOf(A0S);
    }
}
