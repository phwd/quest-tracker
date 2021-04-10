package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0XY  reason: invalid class name */
public class AnonymousClass0XY extends AnonymousClass0Bd<AtomicLong> {
    public final /* synthetic */ AnonymousClass0Bd A00;

    public AnonymousClass0XY(AnonymousClass0Bd r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final AtomicLong A02(AnonymousClass0Fo r4) throws IOException {
        return new AtomicLong(((Number) this.A00.A02(r4)).longValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, AtomicLong atomicLong) throws IOException {
        this.A00.A03(r4, Long.valueOf(atomicLong.get()));
    }
}
