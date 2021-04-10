package defpackage;

import android.telephony.TelephonyManager;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: v6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5198v6 implements Runnable {
    public final C5708y6 F;

    public RunnableC5198v6(C5708y6 y6Var) {
        this.F = y6Var;
    }

    public void run() {
        C5708y6 y6Var = this.F;
        TelephonyManager b = C5708y6.b();
        if (b != null) {
            Objects.requireNonNull(y6Var);
            Object obj = ThreadUtils.f10596a;
            C5538x6 x6Var = new C5538x6(y6Var, null);
            y6Var.d = x6Var;
            b.listen(x6Var, 1);
        }
    }
}
