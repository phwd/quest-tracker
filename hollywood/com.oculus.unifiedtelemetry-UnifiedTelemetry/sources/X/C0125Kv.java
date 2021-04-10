package X;

import java.io.IOException;

/* renamed from: X.Kv  reason: case insensitive filesystem */
public class C0125Kv extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ dD A01;
    public final /* synthetic */ C0337d5 A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0125Kv(C0337d5 d5Var, Object[] objArr, int i, dD dDVar) {
        super("OkHttp %s stream %d", objArr);
        this.A02 = d5Var;
        this.A00 = i;
        this.A01 = dDVar;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        try {
            C0337d5 d5Var = this.A02;
            d5Var.A0F.A03(this.A00, this.A01);
        } catch (IOException unused) {
        }
    }
}
