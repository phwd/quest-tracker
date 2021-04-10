package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0ch  reason: invalid class name and case insensitive filesystem */
public class C03320ch extends AnonymousClass0yl<AtomicLong> {
    public final /* synthetic */ AnonymousClass0yl A00;

    public C03320ch(AnonymousClass0yl r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final AtomicLong A02(C09120zR r4) throws IOException {
        return new AtomicLong(((Number) this.A00.A02(r4)).longValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r4, AtomicLong atomicLong) throws IOException {
        this.A00.A03(r4, Long.valueOf(atomicLong.get()));
    }
}
