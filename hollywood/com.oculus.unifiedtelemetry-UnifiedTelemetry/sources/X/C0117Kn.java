package X;

import java.io.IOException;

/* renamed from: X.Kn  reason: case insensitive filesystem */
public class C0117Kn extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$ReaderRunnable$1";
    public final /* synthetic */ C0114Kk A00;
    public final /* synthetic */ C0335d3 A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0117Kn(C0114Kk kk, Object[] objArr, C0335d3 d3Var) {
        super("OkHttp %s stream %d", objArr);
        this.A00 = kk;
        this.A01 = d3Var;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        try {
            this.A00.A01.A08.A02(this.A01);
        } catch (IOException e) {
            C0324cr.A00.A04(4, AnonymousClass06.A04("Http2Connection.Listener failure for ", this.A00.A01.A0A), e);
            try {
                this.A01.A05(dD.PROTOCOL_ERROR);
            } catch (IOException unused) {
            }
        }
    }
}
