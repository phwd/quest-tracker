package defpackage;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.util.Rational;
import java.util.LinkedList;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: hD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2831hD0 {

    /* renamed from: a  reason: collision with root package name */
    public final List f10055a = new LinkedList();
    public final Activity b;
    public final C1595a3 c;
    public final UT d;

    public C2831hD0(Activity activity, C1595a3 a3Var, UT ut) {
        this.b = activity;
        this.c = a3Var;
        this.d = ut;
    }

    public static Rect g(WebContents webContents, Activity activity) {
        int i;
        int i2;
        Rect l = webContents.l();
        if (l == null || l.width() == 0 || l.height() == 0) {
            return null;
        }
        float b2 = AbstractC4089od0.b(((float) l.width()) / ((float) l.height()), 0.41841003f, 2.39f);
        int width = activity.getWindow().getDecorView().getWidth();
        int height = activity.getWindow().getDecorView().getHeight();
        float f = (float) width;
        float f2 = (float) height;
        if (b2 > f / f2) {
            i2 = (int) (f / b2);
            i = width;
        } else {
            i = (int) (f2 * b2);
            i2 = height;
        }
        int i3 = (width - i) / 2;
        int i4 = (height - i2) / 2;
        return new Rect(i3, i4, i + i3, i2 + i4);
    }

    public static final void i(WebContents webContents, Tab tab) {
        webContents.o0(false);
        W w = C3649m10.F;
        ((C3649m10) tab.M().c(C3649m10.class)).l(false);
    }

    public static final void k(long j) {
        AbstractC3364kK0.f("Media.VideoPersistence.Duration", SystemClock.elapsedRealtime() - j, 7000, 36000000, 50);
    }

    public static void l(int i) {
        AbstractC3364kK0.g("Media.VideoPersistence.AttemptResult", i, 9);
    }

    public void c() {
        if (m()) {
            WebContents h = h();
            Rect g = g(h, this.b);
            PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
            if (g != null) {
                builder.setAspectRatio(new Rational(g.width(), g.height()));
                builder.setSourceRectHint(g);
            }
            try {
                if (this.b.enterPictureInPictureMode(builder.build())) {
                    h.o0(true);
                    Tab tab = this.c.H;
                    C3649m10.h(tab).l(true);
                    this.f10055a.add(new RunnableC1626aD0(h, tab));
                    Activity activity = this.b;
                    C2489fD0 fd0 = new C2489fD0(this, activity);
                    C2318eD0 ed0 = new C2318eD0(this, activity, tab, null);
                    C2660gD0 gd0 = new C2660gD0(this, activity);
                    C2148dD0 dd0 = new C2148dD0(this);
                    tab.A(fd0);
                    h.c0(gd0);
                    this.d.b(dd0);
                    C1595a3 a3Var = this.c;
                    a3Var.F.b(ed0);
                    ed0.a(a3Var.H, false);
                    this.f10055a.add(new RunnableC1806bD0(this, tab, fd0, h, gd0, dd0, ed0));
                    this.f10055a.add(new RunnableC1977cD0(SystemClock.elapsedRealtime()));
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                AbstractC1220Ua0.a("VideoPersist", "Error entering PiP with bounds (%d, %d): %s", Integer.valueOf(g.width()), Integer.valueOf(g.height()), e);
            }
        }
    }

    public void d() {
        e(0);
    }

    public final void e(int i) {
        if (!this.f10055a.isEmpty()) {
            for (Runnable runnable : this.f10055a) {
                runnable.run();
            }
            this.f10055a.clear();
            AbstractC3364kK0.g("Media.VideoPersistence.EndReason", i, 8);
        }
    }

    public final void f(Activity activity, int i) {
        activity.moveTaskToBack(true);
        e(i);
    }

    public final WebContents h() {
        Tab tab = this.c.H;
        if (tab == null) {
            return null;
        }
        return tab.l();
    }

    public final void j(Tab tab, AbstractC1404Xa1 xa1, WebContents webContents, AbstractC6022zx1 zx1, TT tt, W2 w2) {
        tab.I(xa1);
        webContents.Q(zx1);
        this.d.f(tt);
        this.c.F.c(w2);
    }

    public final boolean m() {
        WebContents h = h();
        if (h == null) {
            l(7);
            return false;
        } else if (!h.w() || !h.Y()) {
            return false;
        } else {
            if (Build.VERSION.SDK_INT < 26) {
                l(1);
                return false;
            } else if (!this.b.getPackageManager().hasSystemFeature("android.software.picture_in_picture")) {
                l(2);
                return false;
            } else if (this.b.isInPictureInPictureMode()) {
                l(4);
                return false;
            } else if (this.b.isChangingConfigurations()) {
                l(5);
                return false;
            } else if (this.b.isFinishing()) {
                l(6);
                return false;
            } else {
                l(0);
                return true;
            }
        }
    }
}
