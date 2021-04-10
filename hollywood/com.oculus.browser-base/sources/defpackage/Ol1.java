package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: Ol1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ol1 extends AbstractC2059ck implements View.OnClickListener, Z9 {
    public final Handler F = new Handler();
    public final Runnable G = new Nl1(this);
    public Activity H;
    public C4076oY0 I;

    /* renamed from: J  reason: collision with root package name */
    public Pl1 f8645J;

    public final void a(boolean z) {
        C4076oY0 oy0 = this.I;
        if (oy0 != null) {
            if (z) {
                oy0.f10557a.c(null);
            } else {
                oy0.f10557a.d(null);
            }
            ApplicationStatus.h(this);
            Activity activity = this.H;
            if (activity instanceof ChromeActivity) {
                ((ChromeActivity) activity).M0().Y.c(this);
            }
            this.F.removeCallbacks(this.G);
            Pl1 pl1 = this.f8645J;
            if (pl1 != null) {
                pl1.b();
                this.f8645J = null;
            }
            this.I = null;
        }
    }

    @Override // defpackage.AbstractC2230dk, defpackage.AbstractC2059ck
    public void h(int i, int i2) {
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        a(false);
    }

    public void onClick(View view) {
        a(true);
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 4 || i == 5) {
            a(false);
        }
    }
}
