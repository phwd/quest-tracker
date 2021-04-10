package defpackage;

import android.os.SystemClock;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.components.signin.AccountTrackerService;

/* renamed from: QV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QV0 extends T1 {
    public final /* synthetic */ AccountTrackerService F;
    public final /* synthetic */ long G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ SigninFragmentBase I;

    public QV0(SigninFragmentBase signinFragmentBase, AccountTrackerService accountTrackerService, long j, boolean z) {
        this.I = signinFragmentBase;
        this.F = accountTrackerService;
        this.G = j;
        this.H = z;
    }

    @Override // defpackage.U1
    public void v() {
        AccountTrackerService accountTrackerService = this.F;
        Objects.requireNonNull(accountTrackerService);
        Object obj = ThreadUtils.f10596a;
        accountTrackerService.e.c(this);
        long j = this.G;
        int i = SigninFragmentBase.y0;
        AbstractC3364kK0.k("Signin.AndroidAccountSigninViewSeedingTime", SystemClock.elapsedRealtime() - j);
        SigninFragmentBase signinFragmentBase = this.I;
        if (!signinFragmentBase.M0) {
            signinFragmentBase.t1(this.H);
        }
    }
}
