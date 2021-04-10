package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Executor;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1q3  reason: invalid class name */
public final class AnonymousClass1q3 implements AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> {
    public final AnonymousClass0KW<Boolean> A00;
    public final C09761ow A01;
    public final AnonymousClass1tN A02;
    public final Executor A03;
    public final boolean A04;
    public final int A05;
    public final AnonymousClass0Km A06;
    public final AnonymousClass1tj A07;
    public final AnonymousClass1pP<AnonymousClass1qQ> A08;

    /* JADX WARN: Incorrect args count in method signature: (LX/0Km;Ljava/util/concurrent/Executor;LX/1tN;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZZZLX/1pP<LX/1qQ;>;ILX/1ow;Ljava/lang/Runnable;LX/0KW<Ljava/lang/Boolean;>;)V */
    public AnonymousClass1q3(AnonymousClass0Km r2, Executor executor, AnonymousClass1tN r4, AnonymousClass1tj r5, boolean z, AnonymousClass1pP r7, int i, C09761ow r9, AnonymousClass0KW r10) {
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
                    }
                }
            }
        }
        throw null;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qa<AnonymousClass1q1>> consumer, ProducerContext producerContext) {
        Consumer<AnonymousClass1qQ> r1;
        try {
            AnonymousClass1zo.A00();
            if (!AnonymousClass0MP.A02(producerContext.A07.A03)) {
                r1 = new AnonymousClass1qL(this, consumer, producerContext, this.A05);
            } else {
                r1 = new AnonymousClass1qX(this, consumer, producerContext, new C10421so(this.A06), this.A07, this.A05);
            }
            this.A08.A7a(r1, producerContext);
            AnonymousClass1zo.A00();
        } catch (Throwable th) {
            AnonymousClass1zo.A00();
            throw th;
        }
    }
}
