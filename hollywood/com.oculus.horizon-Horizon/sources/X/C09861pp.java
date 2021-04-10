package X;

import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1pp  reason: invalid class name and case insensitive filesystem */
public final class C09861pp implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1oQ A00;
    public final AnonymousClass1oQ A01;
    public final C10331rw A02;
    public final AnonymousClass1pP<AnonymousClass1qQ> A03;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        Consumer<AnonymousClass1qQ> consumer2 = consumer;
        if (producerContext.A06.getValue() >= AnonymousClass1pG.DISK_CACHE.getValue()) {
            producerContext.A06("disk", "nil-result_write");
            consumer.A06(null, 1);
            return;
        }
        if (producerContext.A07.A0C) {
            consumer2 = new AnonymousClass1pl(consumer2, producerContext, this.A00, this.A01, this.A02);
        }
        this.A03.A7a(consumer2, producerContext);
    }

    public C09861pp(AnonymousClass1oQ r1, AnonymousClass1oQ r2, CacheKeyFactory cacheKeyFactory, AnonymousClass1pP<AnonymousClass1qQ> r4) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = cacheKeyFactory;
        this.A03 = r4;
    }
}
