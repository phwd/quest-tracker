package X;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.0d5  reason: invalid class name */
public class AnonymousClass0d5 extends AnonymousClass13Y<AtomicBoolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r3, AtomicBoolean atomicBoolean) throws IOException {
        String str;
        boolean z = atomicBoolean.get();
        AnonymousClass14L.A03(r3);
        AnonymousClass14L.A02(r3);
        Writer writer = r3.A07;
        if (z) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final AtomicBoolean A02(AnonymousClass14I r3) throws IOException {
        return new AtomicBoolean(r3.A0S());
    }
}
