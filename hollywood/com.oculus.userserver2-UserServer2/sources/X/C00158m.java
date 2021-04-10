package X;

import java.io.IOException;

/* renamed from: X.8m  reason: invalid class name and case insensitive filesystem */
public class C00158m extends Dm {
    public final /* synthetic */ EM A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C00158m(EM em, WF wf) {
        super(wf);
        this.A00 = em;
    }

    @Override // X.Dm, java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        EM em = this.A00;
        em.A01.A05(false, em);
        super.close();
    }
}
