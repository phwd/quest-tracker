package X;

import java.io.IOException;

/* renamed from: X.Ku  reason: case insensitive filesystem */
public class C0124Ku extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$2";
    public final /* synthetic */ int A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ C0337d5 A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0124Ku(C0337d5 d5Var, Object[] objArr, int i, long j) {
        super("OkHttp Window Update %s stream %d", objArr);
        this.A02 = d5Var;
        this.A00 = i;
        this.A01 = j;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        try {
            this.A02.A0F.A02(this.A00, this.A01);
        } catch (IOException unused) {
        }
    }
}
