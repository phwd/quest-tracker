package X;

import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.concurrent.Executor;

/* renamed from: X.1px  reason: invalid class name and case insensitive filesystem */
public final class C09901px implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1pV A00;
    public final Executor A01;
    public final AnonymousClass1pP<AnonymousClass1qQ> A02;
    public final AnonymousClass1lX A03;
    public final boolean A04;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        this.A02.A7a(new AnonymousClass1qT(this, consumer, producerContext, this.A04, this.A03), producerContext);
    }

    public C09901px(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, AnonymousClass1pP<AnonymousClass1qQ> r4, boolean z, AnonymousClass1lX r6) {
        if (executor != null) {
            this.A01 = executor;
            if (pooledByteBufferFactory != null) {
                this.A00 = pooledByteBufferFactory;
                this.A02 = r4;
                if (r6 != null) {
                    this.A03 = r6;
                    this.A04 = z;
                    return;
                }
            }
        }
        throw null;
    }
}
