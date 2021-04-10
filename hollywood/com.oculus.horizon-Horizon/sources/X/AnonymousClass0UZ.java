package X;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.0UZ  reason: invalid class name */
public class AnonymousClass0UZ extends AnonymousClass0yl<AtomicBoolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r3, AtomicBoolean atomicBoolean) throws IOException {
        String str;
        boolean z = atomicBoolean.get();
        C09130zU.A03(r3);
        C09130zU.A02(r3);
        Writer writer = r3.A07;
        if (z) {
            str = "true";
        } else {
            str = "false";
        }
        writer.write(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final AtomicBoolean A02(C09120zR r3) throws IOException {
        return new AtomicBoolean(r3.A0S());
    }
}
