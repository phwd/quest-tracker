package X;

import java.io.IOException;

/* renamed from: X.sn  reason: case insensitive filesystem */
public class C0513sn extends AbstractC0361di {
    public final /* synthetic */ AbstractC0361di A00;

    public C0513sn(AbstractC0361di diVar) {
        this.A00 = diVar;
    }

    @Override // X.AbstractC0361di
    public final C0366dn A01() {
        return this.A00.A01();
    }

    @Override // X.AbstractC0361di
    public final void A02(KJ kj) throws IOException {
        AnonymousClass94 r1 = new AnonymousClass94(new K3(kj));
        this.A00.A02(r1);
        r1.close();
    }
}
