package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.Ks  reason: case insensitive filesystem */
public class C0122Ks extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$4";
    public final /* synthetic */ int A00;
    public final /* synthetic */ List A01;
    public final /* synthetic */ C0337d5 A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0122Ks(C0337d5 d5Var, Object[] objArr, int i, List list) {
        super("OkHttp %s Push Request[%s]", objArr);
        this.A02 = d5Var;
        this.A00 = i;
        this.A01 = list;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        C0337d5 d5Var = this.A02;
        AbstractC0331cy cyVar = d5Var.A09;
        int i = this.A00;
        if (cyVar.onRequest(i, this.A01)) {
            try {
                d5Var.A0F.A03(i, dD.CANCEL);
                synchronized (d5Var) {
                    d5Var.A0C.remove(Integer.valueOf(i));
                }
            } catch (IOException unused) {
            }
        }
    }
}
