package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1kr  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC10121kr<T> extends AbstractC03830od<T> {
    public final AnonymousClass1m8 A00;
    public final AnonymousClass1l1 A01;

    public void A08(@Nullable T t, int i, ProducerContext producerContext) {
        boolean z = true;
        if ((i & 1) != 1) {
            z = false;
        }
        if (super.A05(t, z, producerContext.A0C) && z) {
            this.A00.A7v(this.A01);
        }
    }

    public AbstractC10121kr(AnonymousClass1j8<T> r3, AnonymousClass1l1 r4, AnonymousClass1m8 r5) {
        C01060Pq.A00();
        this.A01 = r4;
        this.A00 = r5;
        this.A04 = r4.A0C;
        C01060Pq.A00();
        this.A00.A7s(this.A01);
        C01060Pq.A00();
        C01060Pq.A00();
        r3.A8d(new AnonymousClass1l0(this), r4);
        C01060Pq.A00();
        C01060Pq.A00();
    }

    @Override // X.AbstractC03830od, X.AnonymousClass0M8
    public final boolean A29() {
        if (!super.A29()) {
            return false;
        }
        if (A5y()) {
            return true;
        }
        AnonymousClass1m8 r1 = this.A00;
        AnonymousClass1l1 r0 = this.A01;
        r1.A7n(r0);
        r0.A03();
        return true;
    }
}
