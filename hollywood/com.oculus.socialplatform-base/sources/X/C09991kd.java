package X;

import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1kd  reason: invalid class name and case insensitive filesystem */
public final class C09991kd implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final C09901kJ A00;
    public final C09901kJ A01;
    public final AnonymousClass1l8 A02;
    public final AnonymousClass1j8<AnonymousClass0PZ> A03;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        Consumer<AnonymousClass0PZ> consumer2 = consumer;
        if (producerContext.A06.getValue() >= AnonymousClass1l4.DISK_CACHE.getValue()) {
            producerContext.A06("disk", "nil-result_write");
            consumer.A07(null, 1);
            return;
        }
        if (producerContext.A07.A0C) {
            consumer2 = new AnonymousClass1kU(consumer2, producerContext, this.A00, this.A01, this.A02);
        }
        this.A03.A8d(consumer2, producerContext);
    }

    public C09991kd(C09901kJ r1, C09901kJ r2, CacheKeyFactory cacheKeyFactory, AnonymousClass1j8<AnonymousClass0PZ> r4) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = cacheKeyFactory;
        this.A03 = r4;
    }
}
