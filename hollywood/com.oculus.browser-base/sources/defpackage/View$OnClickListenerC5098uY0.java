package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: uY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5098uY0 extends Ep1 implements View.OnClickListener, Z9 {
    public Activity F;
    public C5948zY0 G;
    public final Handler H;
    public C4418qY0 I = new C4418qY0();

    /* renamed from: J  reason: collision with root package name */
    public boolean f11418J;
    public ViewGroup K;
    public final WindowAndroid L;
    public final Runnable M = new RunnableC4588rY0(this);

    public View$OnClickListenerC5098uY0(Activity activity, ViewGroup viewGroup, WindowAndroid windowAndroid) {
        this.F = activity;
        this.H = new Handler();
        this.K = viewGroup;
        this.L = windowAndroid;
        ApplicationStatus.g(this, this.F);
        if (ApplicationStatus.e(this.F) == 2 || ApplicationStatus.e(this.F) == 3) {
            this.f11418J = true;
        }
    }

    public void j(AbstractC4758sY0 sy0) {
        C4418qY0 qy0 = this.I;
        if (C4418qY0.d(qy0.f11148a, sy0) || C4418qY0.d(qy0.b, sy0)) {
            m();
        }
    }

    public void k(AbstractC4758sY0 sy0, Object obj) {
        C4418qY0 qy0 = this.I;
        if (C4418qY0.e(qy0.f11148a, sy0, obj) || C4418qY0.e(qy0.b, sy0, obj)) {
            m();
        }
    }

    public void l(C4076oY0 oy0) {
        if (this.f11418J) {
            AbstractC3100ip1.f10165a.d("Snackbar.Shown", oy0.m);
            C4418qY0 qy0 = this.I;
            Objects.requireNonNull(qy0);
            if (oy0.a()) {
                if (qy0.a() != null && !qy0.a().a()) {
                    qy0.c(false);
                }
                qy0.f11148a.addFirst(oy0);
            } else if (oy0.b()) {
                qy0.b.addFirst(oy0);
            } else {
                qy0.f11148a.addLast(oy0);
            }
            m();
            this.G.a();
        }
    }

    public final void m() {
        if (this.f11418J) {
            C4076oY0 a2 = this.I.a();
            if (a2 == null) {
                this.H.removeCallbacks(this.M);
                C5948zY0 zy0 = this.G;
                if (zy0 != null) {
                    zy0.b();
                    this.G = null;
                    return;
                }
                return;
            }
            C5948zY0 zy02 = this.G;
            boolean z = true;
            if (zy02 == null) {
                C5948zY0 zy03 = new C5948zY0(this.F, this, a2, this.K, this.L);
                this.G = zy03;
                zy03.f();
            } else {
                z = zy02.g(a2, true);
            }
            if (z) {
                this.H.removeCallbacks(this.M);
                if (!a2.b()) {
                    int i = a2.j;
                    if (i == 0) {
                        i = 3000;
                    }
                    if (C0283Ep.h().d() && (i = i * 2) < 10000) {
                        i = 10000;
                    }
                    this.H.postDelayed(this.M, (long) i);
                }
                this.G.a();
            }
        }
    }

    public void onClick(View view) {
        C5948zY0 zy0 = this.G;
        if (!TextUtils.isEmpty(zy0.k.f)) {
            zy0.b.announceForAccessibility(zy0.k.f);
        }
        this.I.c(true);
        m();
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 2) {
            this.f11418J = true;
        } else if (i == 5) {
            C4418qY0 qy0 = this.I;
            while (!qy0.b()) {
                qy0.c(false);
            }
            m();
            this.f11418J = false;
        }
    }
}
