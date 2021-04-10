package X;

import retrofit.Endpoints;

/* renamed from: X.1p1  reason: invalid class name */
public class AnonymousClass1p1 extends AnonymousClass1pm<AnonymousClass1qa<AnonymousClass1q1>> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer$1";
    public final /* synthetic */ AnonymousClass1qE A00;
    public final /* synthetic */ AnonymousClass1qU A01;
    public final /* synthetic */ AnonymousClass1p2 A02;
    public final /* synthetic */ C09811pd A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1p1(AnonymousClass1p2 r2, AnonymousClass1qD r3, AnonymousClass1qE r4, AnonymousClass1qU r5, AnonymousClass1qE r6, AnonymousClass1qU r7, C09811pd r8) {
        super(r3, r4, r5, "VideoThumbnailProducer");
        this.A02 = r2;
        this.A00 = r6;
        this.A01 = r7;
        this.A03 = r8;
    }

    @Override // X.AnonymousClass1lM, X.AnonymousClass1pm
    public final void A01(Exception exc) {
        super.A01(exc);
        AnonymousClass1qE r3 = this.A00;
        AnonymousClass1qU r2 = this.A01;
        r3.A77(r2, "VideoThumbnailProducer", false);
        r2.A06("local", Endpoints.DEFAULT_NAME);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1lM, X.AnonymousClass1pm
    public final void A03(AnonymousClass1qa<AnonymousClass1q1> r5) {
        super.A03(r5);
        AnonymousClass1qE r3 = this.A00;
        AnonymousClass1qU r2 = this.A01;
        boolean z = false;
        if (r5 != null) {
            z = true;
        }
        r3.A77(r2, "VideoThumbnailProducer", z);
        r2.A06("local", Endpoints.DEFAULT_NAME);
    }
}
