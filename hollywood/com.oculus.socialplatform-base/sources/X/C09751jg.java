package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.concurrent.Executor;

/* renamed from: X.1jg  reason: invalid class name and case insensitive filesystem */
public final class C09751jg implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass0JW A00;
    public final Executor A01;
    public final AnonymousClass1j8<AnonymousClass0PZ> A02;
    public final AbstractC01080Pu A03;
    public final boolean A04;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        this.A02.A8d(new C09721jc(this, consumer, producerContext, this.A04, this.A03), producerContext);
    }

    public C09751jg(Executor executor, AnonymousClass0JW r3, AnonymousClass1j8<AnonymousClass0PZ> r4, boolean z, AbstractC01080Pu r6) {
        if (executor != null) {
            this.A01 = executor;
            if (r3 != null) {
                this.A00 = r3;
                this.A02 = r4;
                if (r6 != null) {
                    this.A03 = r6;
                    this.A04 = z;
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }
}
