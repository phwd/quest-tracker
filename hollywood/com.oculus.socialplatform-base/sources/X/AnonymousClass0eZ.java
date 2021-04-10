package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0eZ  reason: invalid class name */
public class AnonymousClass0eZ extends AnonymousClass13Y<AtomicLong> {
    public final /* synthetic */ AnonymousClass13Y A00;

    public AnonymousClass0eZ(AnonymousClass13Y r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final AtomicLong A02(AnonymousClass14I r4) throws IOException {
        return new AtomicLong(((Number) this.A00.A02(r4)).longValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, AtomicLong atomicLong) throws IOException {
        this.A00.A03(r4, Long.valueOf(atomicLong.get()));
    }
}
