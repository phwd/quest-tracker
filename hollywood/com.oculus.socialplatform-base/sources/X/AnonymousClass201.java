package X;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.201  reason: invalid class name */
public final class AnonymousClass201 extends AtomicBoolean implements AbstractC12271xB {
    public static final long serialVersionUID = 8943152917179642732L;
    public final AbstractC12941yc downstream;
    public final /* synthetic */ C13621zt this$0;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (compareAndSet(false, true)) {
            this.this$0.A03(this);
        }
    }

    public AnonymousClass201(C13621zt r1, AbstractC12941yc r2) {
        this.this$0 = r1;
        this.downstream = r2;
    }
}
