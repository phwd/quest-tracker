package X;

import android.os.Bundle;

/* renamed from: X.1m  reason: invalid class name */
public final class AnonymousClass1m extends C0333Rn {
    @Override // X.C00859p, X.X1, X.C1300wu
    public final boolean A42(String str, String str2, Bundle bundle) {
        C0514bB.A02(str, "id");
        C0514bB.A02(str2, "action");
        if (!C0514bB.A05("DOUBLE_PRESS_FEEDBACK_SKIP", str2)) {
            return super.A42(str, str2, bundle);
        }
        C0410Wn.A00.logNuxEvent("nux_vc_skip_step");
        C0112Aq.A00().A01(new C1290wk());
        return true;
    }
}
