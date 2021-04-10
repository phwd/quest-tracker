package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1l2  reason: invalid class name */
public final class AnonymousClass1l2<T> implements AnonymousClass1j8<T> {
    public final AnonymousClass1j8<T> A00;
    public final AbstractC10441mf A01;

    public AnonymousClass1l2(AnonymousClass1j8<T> r1, AbstractC10441mf r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<T> consumer, ProducerContext producerContext) {
        try {
            C01060Pq.A00();
            AnonymousClass1l6 r4 = producerContext.A05;
            AnonymousClass1l3 r1 = new AnonymousClass1l3(this, consumer, r4, producerContext, r4, producerContext, consumer);
            producerContext.A04(new AnonymousClass1lC(this, r1));
            this.A01.A1N(r1);
            C01060Pq.A00();
        } catch (Throwable th) {
            C01060Pq.A00();
            throw th;
        }
    }
}
