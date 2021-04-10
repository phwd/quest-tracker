package X;

import android.os.Bundle;

/* renamed from: X.1i  reason: invalid class name */
public final class AnonymousClass1i extends C0333Rn {
    public final /* synthetic */ AbstractC1279wZ A00;

    public AnonymousClass1i(AbstractC1279wZ wZVar) {
        this.A00 = wZVar;
    }

    @Override // X.C00859p, X.X1, X.C1300wu
    public final boolean A42(String str, String str2, Bundle bundle) {
        String str3;
        C0514bB.A02(str, "id");
        C0514bB.A02(str2, "action");
        if (!(this.A00 instanceof A1)) {
            str3 = "NUX_TAKE_A_PHOTO_STEP_DIALOG";
        } else {
            str3 = "NUX_WHAT_TIME_IS_IT_STEP_DIALOG";
        }
        if (!C0514bB.A05(AnonymousClass08.A04("VOICE_COMMAND_SKIP-", str3), str2)) {
            return super.A42(str, str2, bundle);
        }
        C0112Aq.A00().A01(new C1293wn());
        C0410Wn.A00.logNuxEvent("nux_vc_skip_step");
        C0112Aq.A00().A01(new C1290wk());
        return true;
    }
}
