package X;

import android.os.Handler;
import android.os.Looper;
import com.oculus.assistant.assistantutils.SystemUXUtil;

/* renamed from: X.6r  reason: invalid class name and case insensitive filesystem */
public final class C00426r extends AbstractC1409yv {
    @Override // X.AbstractC1409yv, X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r6, AnonymousClass8H r7) {
        super.A1I(r6, r7);
        String A01 = C0398Vv.A01();
        C00799i.A00.logFulfillment(AnonymousClass08.A04("Closing app: ", A01));
        YP.A00().A03();
        BX.A00().sendBroadcast(SystemUXUtil.A03(ZG.HOME, false, new String[0]));
        new Handler(Looper.myLooper()).postDelayed(new Yf(A01, r6, r7), 500);
    }
}
