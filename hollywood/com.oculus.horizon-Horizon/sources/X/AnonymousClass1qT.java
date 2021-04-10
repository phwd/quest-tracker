package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1qT  reason: invalid class name */
public class AnonymousClass1qT extends AnonymousClass1rX<AnonymousClass1qQ, AnonymousClass1qQ> {
    public boolean A00 = false;
    public final AnonymousClass1qU A01;
    public final C09971qh A02;
    public final AnonymousClass1lX A03;
    public final boolean A04;
    public final /* synthetic */ C09901px A05;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1qT(C09901px r5, Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext, boolean z, AnonymousClass1lX r9) {
        super(consumer);
        this.A05 = r5;
        this.A01 = producerContext;
        this.A04 = z;
        this.A03 = r9;
        this.A02 = new C09971qh(r5.A01, new AnonymousClass1qO(this, r5), 100);
        producerContext.A04(new C10161re(this, r5, consumer));
    }
}
