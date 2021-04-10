package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1rn  reason: invalid class name and case insensitive filesystem */
public final class C10241rn implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1pP<AnonymousClass1qQ> A00;
    public final AnonymousClass1pP<AnonymousClass1qQ> A01;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        this.A01.A7a(new C09881pu(this, consumer, producerContext), producerContext);
    }

    public C10241rn(AnonymousClass1pP<AnonymousClass1qQ> r1, AnonymousClass1pP<AnonymousClass1qQ> r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
