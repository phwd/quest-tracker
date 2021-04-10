package X;

import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1pa  reason: invalid class name */
public final class AnonymousClass1pa implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1pP<AnonymousClass1qQ> A00;
    public final AnonymousClass1oQ A01;
    public final AnonymousClass1oQ A02;
    public final C10331rw A03;

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        AnonymousClass1oQ r3;
        AnonymousClass0DC r1;
        C09811pd r12 = producerContext.A07;
        if (r12.A0C) {
            AnonymousClass1qE r5 = producerContext.A05;
            r5.A6b(producerContext, "DiskCacheProducer");
            AnonymousClass1tG r7 = new AnonymousClass1tG(r12.A03.toString());
            if (r12.A09 == AnonymousClass1sR.SMALL) {
                r3 = this.A02;
            } else {
                r3 = this.A01;
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            try {
                AnonymousClass1zo.A00();
                AnonymousClass1qQ A002 = r3.A02.A00(r7);
                if (A002 != null) {
                    r1 = AnonymousClass0DC.A04(A002);
                } else {
                    try {
                        r1 = AnonymousClass0DC.A07(new AnonymousClass1oS(r3, atomicBoolean, r7), r3.A04, null);
                    } catch (Exception e) {
                        C01080Kb.A03(AnonymousClass1oQ.class, e, "Failed to schedule disk-cache read for %s", r7.A4c());
                        r1 = AnonymousClass0DC.A03(e);
                    }
                }
                AnonymousClass1zo.A00();
                r1.A09(new AnonymousClass1pZ(this, r5, producerContext, consumer));
                producerContext.A04(new AnonymousClass1tC(this, atomicBoolean));
            } catch (Throwable th) {
                AnonymousClass1zo.A00();
                throw th;
            }
        } else if (producerContext.A06.getValue() >= AnonymousClass1pG.DISK_CACHE.getValue()) {
            producerContext.A06("disk", "nil-result_read");
            consumer.A06(null, 1);
        } else {
            this.A00.A7a(consumer, producerContext);
        }
    }

    public AnonymousClass1pa(AnonymousClass1oQ r1, AnonymousClass1oQ r2, CacheKeyFactory cacheKeyFactory, AnonymousClass1pP<AnonymousClass1qQ> r4) {
        this.A01 = r1;
        this.A02 = r2;
        this.A03 = cacheKeyFactory;
        this.A00 = r4;
    }
}
