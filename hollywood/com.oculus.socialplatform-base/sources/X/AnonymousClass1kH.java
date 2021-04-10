package X;

import bolts.Task;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1kH  reason: invalid class name */
public final class AnonymousClass1kH implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass1j8<AnonymousClass0PZ> A00;
    public final C09901kJ A01;
    public final C09901kJ A02;
    public final AnonymousClass1l8 A03;

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        C09901kJ r3;
        Task task;
        AnonymousClass1kA r1 = producerContext.A07;
        if (r1.A0C) {
            AnonymousClass1l6 r5 = producerContext.A05;
            r5.A7e(producerContext, "DiskCacheProducer");
            AnonymousClass0sM r6 = new AnonymousClass0sM(r1.A03.toString());
            if (r1.A09 == AnonymousClass1lL.SMALL) {
                r3 = this.A02;
            } else {
                r3 = this.A01;
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            try {
                C01060Pq.A00();
                AnonymousClass0PZ A002 = r3.A03.A00(r6);
                if (A002 != null) {
                    task = Task.forResult(A002);
                } else {
                    try {
                        task = Task.call(new AnonymousClass1kK(r3, atomicBoolean, r6), r3.A04, null);
                    } catch (Exception e) {
                        AnonymousClass0J5.A03(C09901kJ.class, e, "Failed to schedule disk-cache read for %s", r6.A5D());
                        task = Task.forError(e);
                    }
                }
                C01060Pq.A00();
                task.continueWith(new AnonymousClass1kG(this, r5, producerContext, consumer));
                producerContext.A04(new AnonymousClass1m7(this, atomicBoolean));
            } catch (Throwable th) {
                C01060Pq.A00();
                throw th;
            }
        } else if (producerContext.A06.getValue() >= AnonymousClass1l4.DISK_CACHE.getValue()) {
            producerContext.A06("disk", "nil-result_read");
            consumer.A07(null, 1);
        } else {
            this.A00.A8d(consumer, producerContext);
        }
    }

    public AnonymousClass1kH(C09901kJ r1, C09901kJ r2, CacheKeyFactory cacheKeyFactory, AnonymousClass1j8<AnonymousClass0PZ> r4) {
        this.A01 = r1;
        this.A02 = r2;
        this.A03 = cacheKeyFactory;
        this.A00 = r4;
    }
}
