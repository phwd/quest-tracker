package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1jc  reason: invalid class name and case insensitive filesystem */
public class C09721jc extends AbstractC09791jm<AnonymousClass0PZ, AnonymousClass0PZ> {
    public boolean A00 = false;
    public final C10161kv A01;
    public final C10081km A02;
    public final AbstractC01080Pu A03;
    public final boolean A04;
    public final /* synthetic */ C09751jg A05;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C09721jc(C09751jg r5, Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext, boolean z, AbstractC01080Pu r9) {
        super(consumer);
        this.A05 = r5;
        this.A01 = producerContext;
        this.A04 = z;
        this.A03 = r9;
        this.A02 = new C10081km(r5.A01, new C09711jb(this, r5), 100);
        producerContext.A04(new AnonymousClass1jh(this, r5, consumer));
    }
}
