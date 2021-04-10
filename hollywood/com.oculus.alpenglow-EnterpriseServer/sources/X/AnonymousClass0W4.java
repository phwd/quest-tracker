package X;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.0W4  reason: invalid class name */
public class AnonymousClass0W4 extends AnonymousClass0Bd<AtomicBoolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r3, AtomicBoolean atomicBoolean) throws IOException {
        String str;
        boolean z = atomicBoolean.get();
        AnonymousClass0GL.A04(r3);
        AnonymousClass0GL.A03(r3);
        Writer writer = r3.A08;
        if (z) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final AtomicBoolean A02(AnonymousClass0Fo r3) throws IOException {
        return new AtomicBoolean(r3.A0O());
    }
}
