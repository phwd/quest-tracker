package X;

import java.io.IOException;

/* renamed from: X.Kq  reason: case insensitive filesystem */
public class C0120Kq extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C0337d5 A02;
    public final /* synthetic */ AnonymousClass98 A03;
    public final /* synthetic */ boolean A04;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0120Kq(C0337d5 d5Var, Object[] objArr, int i, AnonymousClass98 r5, int i2, boolean z) {
        super("OkHttp %s Push Data[%s]", objArr);
        this.A02 = d5Var;
        this.A01 = i;
        this.A03 = r5;
        this.A00 = i2;
        this.A04 = z;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        try {
            C0337d5 d5Var = this.A02;
            AbstractC0331cy cyVar = d5Var.A09;
            int i = this.A01;
            AnonymousClass98 r2 = this.A03;
            int i2 = this.A00;
            boolean z = this.A04;
            if (cyVar.onData(i, r2, i2, z)) {
                d5Var.A0F.A03(i, dD.CANCEL);
            } else if (!z) {
                return;
            }
            synchronized (d5Var) {
                d5Var.A0C.remove(Integer.valueOf(i));
            }
        } catch (IOException unused) {
        }
    }
}
