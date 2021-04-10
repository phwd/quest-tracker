package X;

import java.io.IOException;

/* renamed from: X.0MS  reason: invalid class name */
public class AnonymousClass0MS extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ EnumC08000vj A01;
    public final /* synthetic */ C07920vb A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MS(C07920vb r2, Object[] objArr, int i, EnumC08000vj r5) {
        super("OkHttp %s stream %d", objArr);
        this.A02 = r2;
        this.A00 = i;
        this.A01 = r5;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        try {
            C07920vb r0 = this.A02;
            r0.A0F.A03(this.A00, this.A01);
        } catch (IOException unused) {
        }
    }
}
