package X;

import com.facebook.tigon.oktigon.OkTigonRequestToken;
import java.io.IOException;

public final class LC extends AbstractRunnableC0354db {
    public static final String __redex_internal_original_name = "okhttp3.RealCall$AsyncCall";
    public final OkTigonRequestToken A00;
    public final /* synthetic */ LB A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LC(LB lb, OkTigonRequestToken okTigonRequestToken) {
        super("OkHttp %s", lb.A00());
        this.A01 = lb;
        this.A00 = okTigonRequestToken;
    }

    @Override // X.AbstractRunnableC0354db
    public final void A00() {
        LB lb;
        String str;
        String str2;
        try {
            LB lb2 = this.A01;
            lb = lb2;
            C0359dg A012 = lb2.A01();
            L1 l1 = lb2.A03;
            if (l1.A04) {
                try {
                    this.A00.onFailure(lb2, new IOException("Canceled"));
                } catch (IOException e) {
                    C0324cr crVar = C0324cr.A00;
                    if (l1.A04) {
                        str = "canceled ";
                    } else {
                        str = "";
                    }
                    if (lb2.A04) {
                        str2 = "web socket";
                    } else {
                        str2 = "call";
                    }
                    crVar.A04(4, AnonymousClass06.A04("Callback failure for ", AnonymousClass06.A06(str, str2, " to ", lb2.A00())), e);
                } catch (Throwable th) {
                    C0373dw dwVar = this.A01.A01.A0J;
                    C0373dw.A02(dwVar, dwVar.A02, this, true);
                    throw th;
                }
            } else {
                this.A00.onResponse(lb2, A012);
            }
        } catch (IOException e2) {
            OkTigonRequestToken okTigonRequestToken = this.A00;
            lb = this.A01;
            okTigonRequestToken.onFailure(lb, e2);
        }
        C0373dw dwVar2 = lb.A01.A0J;
        C0373dw.A02(dwVar2, dwVar2.A02, this, true);
    }
}
