package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1rG  reason: invalid class name */
public final class AnonymousClass1rG<T> implements AnonymousClass1pP<T> {
    public final AnonymousClass1pP<T> A00;
    public final AnonymousClass1uO A01;

    public AnonymousClass1rG(AnonymousClass1pP<T> r1, AnonymousClass1uO r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<T> consumer, ProducerContext producerContext) {
        try {
            AnonymousClass1zo.A00();
            AnonymousClass1qE r4 = producerContext.A05;
            AnonymousClass1rS r1 = new AnonymousClass1rS(this, consumer, r4, producerContext, r4, producerContext, consumer);
            producerContext.A04(new AnonymousClass1s5(this, r1));
            this.A01.A1C(r1);
            AnonymousClass1zo.A00();
        } catch (Throwable th) {
            AnonymousClass1zo.A00();
            throw th;
        }
    }
}
