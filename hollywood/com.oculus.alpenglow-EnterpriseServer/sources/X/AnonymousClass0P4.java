package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0P4  reason: invalid class name */
public class AnonymousClass0P4 extends AbstractRunnableC05610k6 {
    public static final String __redex_internal_original_name = "okhttp3.internal.http2.Http2Connection$5";
    public final /* synthetic */ int A00;
    public final /* synthetic */ List A01;
    public final /* synthetic */ C04780hU A02;
    public final /* synthetic */ boolean A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0P4(C04780hU r2, Object[] objArr, int i, List list, boolean z) {
        super("OkHttp %s Push Headers[%s]", objArr);
        this.A02 = r2;
        this.A00 = i;
        this.A01 = list;
        this.A03 = z;
    }

    @Override // X.AbstractRunnableC05610k6
    public final void A00() {
        C04780hU r4 = this.A02;
        AbstractC04720hN r3 = r4.A09;
        int i = this.A00;
        List<C04870hr> list = this.A01;
        boolean z = this.A03;
        if (r3.onHeaders(i, list, z)) {
            try {
                r4.A0F.A03(i, EnumC04880hs.CANCEL);
            } catch (IOException unused) {
                return;
            }
        } else if (!z) {
            return;
        }
        synchronized (r4) {
            r4.A0C.remove(Integer.valueOf(i));
        }
    }
}
