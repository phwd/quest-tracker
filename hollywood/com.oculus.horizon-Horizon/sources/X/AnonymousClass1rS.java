package X;

/* renamed from: X.1rS  reason: invalid class name */
public class AnonymousClass1rS extends AnonymousClass1pm<T> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.ThreadHandoffProducer$1";
    public final /* synthetic */ AnonymousClass1qD A00;
    public final /* synthetic */ AnonymousClass1qU A01;
    public final /* synthetic */ AnonymousClass1qE A02;
    public final /* synthetic */ AnonymousClass1rG A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1rS(AnonymousClass1rG r2, AnonymousClass1qD r3, AnonymousClass1qE r4, AnonymousClass1qU r5, AnonymousClass1qE r6, AnonymousClass1qU r7, AnonymousClass1qD r8) {
        super(r3, r4, r5, "BackgroundThreadHandoffProducer");
        this.A03 = r2;
        this.A02 = r6;
        this.A01 = r7;
        this.A00 = r8;
    }

    @Override // X.AnonymousClass1lM, X.AnonymousClass1pm
    public final void A03(T t) {
        AnonymousClass1qE r3 = this.A02;
        AnonymousClass1qU r2 = this.A01;
        r3.A6Z(r2, "BackgroundThreadHandoffProducer", null);
        this.A03.A00.A7a(this.A00, r2);
    }
}
