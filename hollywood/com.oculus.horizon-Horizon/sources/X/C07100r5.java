package X;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.0r5  reason: invalid class name and case insensitive filesystem */
public class C07100r5 implements AnonymousClass0D4<TResult, Void> {
    public final /* synthetic */ AnonymousClass0DD A00;
    public final /* synthetic */ AtomicBoolean A01;

    public C07100r5(AtomicBoolean atomicBoolean, AnonymousClass0DD r2) {
        this.A01 = atomicBoolean;
        this.A00 = r2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0D4
    public final Void then(AnonymousClass0DC r4) throws Exception {
        if (this.A01.compareAndSet(false, true)) {
            this.A00.A02(r4);
            return null;
        }
        r4.A0F();
        return null;
    }
}
