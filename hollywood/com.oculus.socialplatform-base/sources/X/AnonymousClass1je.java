package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Executor;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1je  reason: invalid class name */
public final class AnonymousClass1je implements AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> {
    public final AbstractC00750Ik<Boolean> A00;
    public final AnonymousClass1Wf A01;
    public final AnonymousClass0PW A02;
    public final Executor A03;
    public final boolean A04;
    public final int A05;
    public final AnonymousClass0VT A06;
    public final AnonymousClass1k2 A07;
    public final AnonymousClass1j8<AnonymousClass0PZ> A08;

    /* JADX WARN: Incorrect args count in method signature: (LX/0VT;Ljava/util/concurrent/Executor;LX/0PW;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZZZLX/1j8<LX/0PZ;>;ILX/1Wf;Ljava/lang/Runnable;LX/0Ik<Ljava/lang/Boolean;>;)V */
    public AnonymousClass1je(AnonymousClass0VT r2, Executor executor, AnonymousClass0PW r4, AnonymousClass1k2 r5, boolean z, AnonymousClass1j8 r7, int i, AnonymousClass1Wf r9, AbstractC00750Ik r10) {
        if (r2 != null) {
            this.A06 = r2;
            if (executor != null) {
                this.A03 = executor;
                if (r4 != null) {
                    this.A02 = r4;
                    if (r5 != null) {
                        this.A07 = r5;
                        this.A04 = z;
                        if (r7 != null) {
                            this.A08 = r7;
                            this.A05 = i;
                            this.A01 = r9;
                            this.A00 = r10;
                            return;
                        }
                        throw null;
                    }
                    throw null;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AbstractC00820Ju<AnonymousClass0VM>> consumer, ProducerContext producerContext) {
        Consumer<AnonymousClass0PZ> r1;
        try {
            C01060Pq.A00();
            if (!AnonymousClass0LJ.A02(producerContext.A07.A03)) {
                r1 = new C09781jl(this, consumer, producerContext, this.A05);
            } else {
                r1 = new C09731jd(this, consumer, producerContext, new AnonymousClass1jr(this.A06), this.A07, this.A05);
            }
            this.A08.A8d(r1, producerContext);
            C01060Pq.A00();
        } catch (Throwable th) {
            C01060Pq.A00();
            throw th;
        }
    }
}
