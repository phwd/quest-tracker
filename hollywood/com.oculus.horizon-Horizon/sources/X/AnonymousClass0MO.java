package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0MO  reason: invalid class name */
public class AnonymousClass0MO extends AbstractRunnableC08170w7 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$4";
    public final /* synthetic */ int A00;
    public final /* synthetic */ List A01;
    public final /* synthetic */ C07920vb A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MO(C07920vb r2, Object[] objArr, int i, List list) {
        super("OkHttp %s Push Request[%s]", objArr);
        this.A02 = r2;
        this.A00 = i;
        this.A01 = list;
    }

    @Override // X.AbstractRunnableC08170w7
    public final void A00() {
        C07920vb r3 = this.A02;
        AbstractC07860vU r1 = r3.A09;
        int i = this.A00;
        if (r1.onRequest(i, this.A01)) {
            try {
                r3.A0F.A03(i, EnumC08000vj.CANCEL);
                synchronized (r3) {
                    r3.A0C.remove(Integer.valueOf(i));
                }
            } catch (IOException unused) {
            }
        }
    }
}
