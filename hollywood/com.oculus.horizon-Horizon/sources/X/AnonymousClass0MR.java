package X;

import java.io.IOException;

/* renamed from: X.0MR  reason: invalid class name */
public class AnonymousClass0MR extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$2";
    public final /* synthetic */ int A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ C07920vb A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MR(C07920vb r2, Object[] objArr, int i, long j) {
        super("OkHttp Window Update %s stream %d", objArr);
        this.A02 = r2;
        this.A00 = i;
        this.A01 = j;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        try {
            this.A02.A0F.A02(this.A00, this.A01);
        } catch (IOException unused) {
        }
    }
}
