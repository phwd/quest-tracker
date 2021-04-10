package X;

import android.os.Handler;
import java.util.List;

/* renamed from: X.1yL  reason: invalid class name */
public class AnonymousClass1yL implements AbstractC11131xk {
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AbstractC11131xk A01;
    public final /* synthetic */ AbstractC11151xn A02;
    public final /* synthetic */ C11211xt A03;
    public final /* synthetic */ List A04;

    public AnonymousClass1yL(C11211xt r1, AbstractC11131xk r2, Handler handler, AbstractC11151xn r4, List list) {
        this.A03 = r1;
        this.A01 = r2;
        this.A00 = handler;
        this.A02 = r4;
        this.A04 = list;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        this.A03.A04(new AnonymousClass1yN(this));
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        AnonymousClass1z6.A00(this.A01, this.A00);
        AbstractC11151xn r0 = this.A02;
        if (r0 != null) {
            r0.A6B();
        }
    }
}
