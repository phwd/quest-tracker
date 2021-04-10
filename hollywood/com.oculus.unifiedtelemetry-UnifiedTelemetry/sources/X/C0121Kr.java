package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.Kr  reason: case insensitive filesystem */
public class C0121Kr extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$5";
    public final /* synthetic */ int A00;
    public final /* synthetic */ List A01;
    public final /* synthetic */ C0337d5 A02;
    public final /* synthetic */ boolean A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0121Kr(C0337d5 d5Var, Object[] objArr, int i, List list, boolean z) {
        super("OkHttp %s Push Headers[%s]", objArr);
        this.A02 = d5Var;
        this.A00 = i;
        this.A01 = list;
        this.A03 = z;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        C0337d5 d5Var = this.A02;
        AbstractC0331cy cyVar = d5Var.A09;
        int i = this.A00;
        List<C0343dC> list = this.A01;
        boolean z = this.A03;
        if (cyVar.onHeaders(i, list, z)) {
            try {
                d5Var.A0F.A03(i, dD.CANCEL);
            } catch (IOException unused) {
                return;
            }
        } else if (!z) {
            return;
        }
        synchronized (d5Var) {
            d5Var.A0C.remove(Integer.valueOf(i));
        }
    }
}
