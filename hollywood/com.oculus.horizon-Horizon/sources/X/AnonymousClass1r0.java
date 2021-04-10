package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1r0  reason: invalid class name */
public abstract class AnonymousClass1r0<T> extends AnonymousClass1r1<T> {
    public final AnonymousClass1t0 A00;
    public final AnonymousClass1rN A01;

    public void A09(@Nullable T t, int i, ProducerContext producerContext) {
        boolean z = true;
        if ((i & 1) != 1) {
            z = false;
        }
        if (super.A06(t, z, producerContext.A0C) && z) {
            this.A00.A6p(this.A01);
        }
    }

    public AnonymousClass1r0(AnonymousClass1pP<T> r3, AnonymousClass1rN r4, AnonymousClass1t0 r5) {
        AnonymousClass1zo.A00();
        this.A01 = r4;
        this.A00 = r5;
        this.A04 = r4.A0C;
        AnonymousClass1zo.A00();
        this.A00.A6n(this.A01);
        AnonymousClass1zo.A00();
        AnonymousClass1zo.A00();
        r3.A7a(new AnonymousClass1rC(this), r4);
        AnonymousClass1zo.A00();
        AnonymousClass1zo.A00();
    }

    @Override // X.AnonymousClass1r1
    public final boolean A04() {
        if (!super.A04()) {
            return false;
        }
        if (A05()) {
            return true;
        }
        AnonymousClass1t0 r1 = this.A00;
        AnonymousClass1rN r0 = this.A01;
        r1.A6j(r0);
        r0.A03();
        return true;
    }
}
