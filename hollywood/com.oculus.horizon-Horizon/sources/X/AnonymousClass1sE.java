package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1sE  reason: invalid class name */
public final class AnonymousClass1sE implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1pP<AnonymousClass1qQ> A00;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        this.A00.A7a(new AnonymousClass1sD(consumer), producerContext);
    }

    public AnonymousClass1sE(AnonymousClass1pP<AnonymousClass1qQ> r1) {
        this.A00 = r1;
    }
}
