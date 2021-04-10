package X;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1ch  reason: invalid class name and case insensitive filesystem */
public final class C07591ch implements AbstractC08171e8<InputStream> {
    public final C06741ax A00;

    @Override // X.AbstractC08171e8
    public final void A26() {
        this.A00.A01();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC08171e8
    @NonNull
    public final /* bridge */ /* synthetic */ InputStream A9Q() throws IOException {
        C06741ax r0 = this.A00;
        r0.reset();
        return r0;
    }

    public C07591ch(InputStream inputStream, AnonymousClass1hX r4) {
        C06741ax r1 = new C06741ax(inputStream, r4);
        this.A00 = r1;
        r1.mark(5242880);
    }
}
