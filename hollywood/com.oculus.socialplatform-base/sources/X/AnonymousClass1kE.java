package X;

import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Map;

/* renamed from: X.1kE  reason: invalid class name */
public final class AnonymousClass1kE implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass1l8 A00;
    public final AbstractC03450mg<AnonymousClass0H3, AnonymousClass0JV> A01;
    public final AnonymousClass1j8<AnonymousClass0PZ> A02;

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        Map<String, String> map;
        try {
            C01060Pq.A00();
            AnonymousClass1l6 r6 = producerContext.A05;
            r6.A7e(producerContext, "EncodedMemoryCacheProducer");
            AnonymousClass1kA r12 = producerContext.A07;
            AnonymousClass0sM r10 = new AnonymousClass0sM(r12.A03.toString());
            AbstractC03450mg<AnonymousClass0H3, AnonymousClass0JV> r9 = this.A01;
            AbstractC00820Ju<AnonymousClass0JV> A3K = r9.A3K(r10);
            Map<String, String> map2 = null;
            if (A3K != null) {
                try {
                    AnonymousClass0PZ r2 = new AnonymousClass0PZ(A3K);
                    try {
                        if (r6.A9I(producerContext, "EncodedMemoryCacheProducer")) {
                            map2 = C00690Id.A00("cached_value_found", "true");
                        }
                        r6.A7c(producerContext, "EncodedMemoryCacheProducer", map2);
                        r6.A8F(producerContext, "EncodedMemoryCacheProducer", true);
                        producerContext.A06("memory_encoded", "default");
                        consumer.A06(1.0f);
                        consumer.A07(r2, 1);
                    } finally {
                        AnonymousClass0PZ.A04(r2);
                    }
                } catch (Throwable th) {
                    AbstractC00820Ju.A03(A3K);
                    throw th;
                }
            } else if (producerContext.A06.getValue() >= AnonymousClass1l4.ENCODED_MEMORY_CACHE.getValue()) {
                if (r6.A9I(producerContext, "EncodedMemoryCacheProducer")) {
                    map = C00690Id.A00("cached_value_found", "false");
                } else {
                    map = null;
                }
                r6.A7c(producerContext, "EncodedMemoryCacheProducer", map);
                r6.A8F(producerContext, "EncodedMemoryCacheProducer", false);
                producerContext.A06("memory_encoded", "nil-result");
                consumer.A07(null, 1);
            } else {
                Consumer<AnonymousClass0PZ> r1 = new AnonymousClass1kV(consumer, r9, r10, r12.A0D);
                if (r6.A9I(producerContext, "EncodedMemoryCacheProducer")) {
                    map2 = C00690Id.A00("cached_value_found", "false");
                }
                r6.A7c(producerContext, "EncodedMemoryCacheProducer", map2);
                this.A02.A8d(r1, producerContext);
            }
            AbstractC00820Ju.A03(A3K);
            C01060Pq.A00();
        } catch (Throwable th2) {
            C01060Pq.A00();
            throw th2;
        }
    }

    public AnonymousClass1kE(AbstractC03450mg<AnonymousClass0H3, AnonymousClass0JV> r1, CacheKeyFactory cacheKeyFactory, AnonymousClass1j8<AnonymousClass0PZ> r3) {
        this.A01 = r1;
        this.A00 = cacheKeyFactory;
        this.A02 = r3;
    }
}
