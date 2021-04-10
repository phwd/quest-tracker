package X;

import javax.annotation.Nullable;

/* renamed from: X.1l3  reason: invalid class name */
public class AnonymousClass1l3 extends AbstractC09741jf<T> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.ThreadHandoffProducer$1";
    public final /* synthetic */ AbstractC10011kf A00;
    public final /* synthetic */ C10161kv A01;
    public final /* synthetic */ AnonymousClass1l6 A02;
    public final /* synthetic */ AnonymousClass1l2 A03;

    @Override // X.AnonymousClass0IL
    @Nullable
    public final T A06() throws Exception {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1l3(AnonymousClass1l2 r2, AbstractC10011kf r3, AnonymousClass1l6 r4, C10161kv r5, AnonymousClass1l6 r6, C10161kv r7, AbstractC10011kf r8) {
        super(r3, r4, r5, "BackgroundThreadHandoffProducer");
        this.A03 = r2;
        this.A02 = r6;
        this.A01 = r7;
        this.A00 = r8;
    }

    @Override // X.AnonymousClass0IL, X.AbstractC09741jf
    public final void A05(T t) {
        AnonymousClass1l6 r3 = this.A02;
        C10161kv r2 = this.A01;
        r3.A7c(r2, "BackgroundThreadHandoffProducer", null);
        this.A03.A00.A8d(this.A00, r2);
    }
}
