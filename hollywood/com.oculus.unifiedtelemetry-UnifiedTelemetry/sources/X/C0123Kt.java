package X;

import java.io.IOException;

/* renamed from: X.Kt  reason: case insensitive filesystem */
public class C0123Kt extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$3";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C0337d5 A02;
    public final /* synthetic */ boolean A03 = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0123Kt(C0337d5 d5Var, Object[] objArr, int i, int i2) {
        super("OkHttp %s ping %08x%08x", objArr);
        this.A02 = d5Var;
        this.A00 = i;
        this.A01 = i2;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        try {
            C0337d5 d5Var = this.A02;
            boolean z = this.A03;
            int i = this.A00;
            int i2 = this.A01;
            C0334d2 d2Var = d5Var.A0F;
            synchronized (d2Var) {
                if (!d2Var.A01) {
                    byte b = 0;
                    if (z) {
                        b = 1;
                    }
                    C0334d2.A00(d2Var, 0, 8, (byte) 6, b);
                    KJ kj = d2Var.A04;
                    kj.A5t(i);
                    kj.A5t(i2);
                    kj.flush();
                } else {
                    throw new IOException("closed");
                }
            }
        } catch (IOException unused) {
        }
    }
}
