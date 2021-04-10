package X;

import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Map;
import retrofit.Endpoints;

/* renamed from: X.1pg  reason: invalid class name and case insensitive filesystem */
public final class C09831pg implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final C10331rw A00;
    public final AbstractC10301rt<AnonymousClass1kC, PooledByteBuffer> A01;
    public final AnonymousClass1pP<AnonymousClass1qQ> A02;

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        Map<String, String> map;
        try {
            AnonymousClass1zo.A00();
            AnonymousClass1qE r6 = producerContext.A05;
            r6.A6b(producerContext, "EncodedMemoryCacheProducer");
            C09811pd r12 = producerContext.A07;
            AnonymousClass1tG r10 = new AnonymousClass1tG(r12.A03.toString());
            AbstractC10301rt<AnonymousClass1kC, PooledByteBuffer> r9 = this.A01;
            AnonymousClass1qa<PooledByteBuffer> A2t = r9.A2t(r10);
            Map<String, String> map2 = null;
            if (A2t != null) {
                try {
                    AnonymousClass1qQ r2 = new AnonymousClass1qQ(A2t);
                    try {
                        if (r6.A8K(producerContext, "EncodedMemoryCacheProducer")) {
                            map2 = AnonymousClass0KP.A00("cached_value_found", "true");
                        }
                        r6.A6Z(producerContext, "EncodedMemoryCacheProducer", map2);
                        r6.A77(producerContext, "EncodedMemoryCacheProducer", true);
                        producerContext.A06("memory_encoded", Endpoints.DEFAULT_NAME);
                        consumer.A05(1.0f);
                        consumer.A06(r2, 1);
                        AnonymousClass1qQ.A03(r2);
                        A2t.close();
                    } catch (Throwable th) {
                        AnonymousClass1qQ.A03(r2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (A2t != null) {
                        A2t.close();
                    }
                    throw th2;
                }
            } else if (producerContext.A06.getValue() >= AnonymousClass1pG.ENCODED_MEMORY_CACHE.getValue()) {
                if (r6.A8K(producerContext, "EncodedMemoryCacheProducer")) {
                    map = AnonymousClass0KP.A00("cached_value_found", "false");
                } else {
                    map = null;
                }
                r6.A6Z(producerContext, "EncodedMemoryCacheProducer", map);
                r6.A77(producerContext, "EncodedMemoryCacheProducer", false);
                producerContext.A06("memory_encoded", "nil-result");
                consumer.A06(null, 1);
            } else {
                Consumer<AnonymousClass1qQ> r1 = new C09841pn(consumer, r9, r10, r12.A0D);
                if (r6.A8K(producerContext, "EncodedMemoryCacheProducer")) {
                    map2 = AnonymousClass0KP.A00("cached_value_found", "false");
                }
                r6.A6Z(producerContext, "EncodedMemoryCacheProducer", map2);
                this.A02.A7a(r1, producerContext);
            }
            AnonymousClass1zo.A00();
        } catch (Throwable th3) {
            AnonymousClass1zo.A00();
            throw th3;
        }
    }

    public C09831pg(AbstractC10301rt<AnonymousClass1kC, PooledByteBuffer> r1, CacheKeyFactory cacheKeyFactory, AnonymousClass1pP<AnonymousClass1qQ> r3) {
        this.A01 = r1;
        this.A00 = cacheKeyFactory;
        this.A02 = r3;
    }
}
