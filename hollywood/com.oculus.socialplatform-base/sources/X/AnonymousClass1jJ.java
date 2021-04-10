package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1jJ  reason: invalid class name */
public final class AnonymousClass1jJ implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass1j8<AnonymousClass0PZ> A00;
    public final AnonymousClass1j8<AnonymousClass0PZ> A01;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        this.A01.A8d(new AnonymousClass1jI(this, consumer, producerContext), producerContext);
    }

    public AnonymousClass1jJ(AnonymousClass1j8<AnonymousClass0PZ> r1, AnonymousClass1j8<AnonymousClass0PZ> r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
