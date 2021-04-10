package defpackage;

import android.content.res.Resources;
import android.os.SystemClock;
import com.oculus.browser.R;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* renamed from: f11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2454f11 implements AbstractC3688mE0 {

    /* renamed from: a  reason: collision with root package name */
    public Tab f9892a;
    public C4163p11 b = new C4163p11();
    public C1872be1 c;
    public Runnable d;
    public AbstractC4758sY0 e;
    public AbstractC1404Xa1 f;
    public Callback g;
    public int h;
    public boolean i;
    public boolean j;
    public int k;
    public boolean l;
    public long m;
    public Q31 n;
    public Q31 o;

    public C2454f11(Tab tab, C0090Bk bk, Runnable runnable, Callback callback, Callback callback2) {
        this.f9892a = tab;
        C1872be1 e2 = C1872be1.e(this.f9892a);
        this.c = e2;
        e2.K = bk;
        e2.M = callback;
        e2.L = runnable;
        C2283e11 e11 = new C2283e11(this, null);
        this.f = e11;
        this.h = 0;
        this.f9892a.A(e11);
        this.g = callback2;
    }

    @Override // defpackage.AbstractC3688mE0
    public void a(int i2) {
        if (this.h == 1) {
            Objects.requireNonNull(this.b);
            AbstractC3364kK0.g("Browser.PaintPreview.TabbedPlayer.CompositorFailureReason", i2, 16);
            i(2);
        }
    }

    @Override // defpackage.AbstractC3688mE0
    public void b(GURL gurl) {
        if (this.f9892a != null && gurl.b && !gurl.j()) {
            this.f9892a.c(new LoadUrlParams(gurl.h(), 0));
            i(4);
        }
    }

    @Override // defpackage.AbstractC3688mE0
    public void c() {
    }

    @Override // defpackage.AbstractC3688mE0
    public void d() {
        Tab tab = this.f9892a;
        if (tab != null && tab.i() != null && !this.l && AbstractC5268vY0.a(this.f9892a.i()) != null) {
            if (this.e == null) {
                this.e = new C2113d11(this);
            }
            Resources resources = this.f9892a.getContext().getResources();
            C4076oY0 c2 = C4076oY0.c(resources.getString(R.string.f57370_resource_name_obfuscated_RES_2131953054), this.e, 1, 36);
            c2.d = resources.getString(R.string.f57360_resource_name_obfuscated_RES_2131953053);
            c2.e = null;
            c2.j = 8000;
            AbstractC5268vY0.a(this.f9892a.i()).l(c2);
            this.l = true;
            this.k++;
        }
    }

    @Override // defpackage.AbstractC3688mE0
    public void e() {
        i(0);
    }

    @Override // defpackage.AbstractC3688mE0
    public void f() {
        if (this.i) {
            i(3);
            return;
        }
        C4163p11 p11 = this.b;
        Objects.requireNonNull(p11);
        p11.b = System.currentTimeMillis();
    }

    @Override // defpackage.AbstractC3688mE0
    public void g() {
        if (this.h == 1) {
            C4163p11 p11 = this.b;
            long j2 = this.m;
            Q31 q31 = this.n;
            Callback callback = this.g;
            p11.c = true;
            if (q31 != null && ((Boolean) q31.get()).booleanValue()) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - j2;
                AbstractC3364kK0.i("Browser.PaintPreview.TabbedPlayer.TimeToFirstBitmap", elapsedRealtime);
                if (callback != null) {
                    callback.onResult(Long.valueOf(elapsedRealtime));
                }
            }
        }
    }

    public final void h() {
        Tab tab;
        View$OnClickListenerC5098uY0 a2;
        if (this.e != null && (tab = this.f9892a) != null && tab.i() != null && (a2 = AbstractC5268vY0.a(this.f9892a.i())) != null) {
            a2.j(this.e);
        }
    }

    public final void i(int i2) {
        Tab tab;
        Runnable runnable = this.d;
        if (runnable != null) {
            runnable.run();
        }
        this.d = null;
        this.f9892a.I(this.f);
        int i3 = this.h;
        this.h = 2;
        if (i3 == 1) {
            this.c.k(i2 == 3 || i2 == 1 || i2 == 0);
            if (i2 == 3 && (tab = this.f9892a) != null && !tab.isHidden()) {
                C1184Ti1.a(this.f9892a.getContext(), R.string.f57350_resource_name_obfuscated_RES_2131953052, 0).b.show();
            }
            h();
            C4163p11 p11 = this.b;
            int i4 = this.k;
            Objects.requireNonNull(p11);
            if (i2 == 1) {
                AbstractC3535lK0.a("PaintPreview.TabbedPlayer.Actionbar.Action");
            }
            AbstractC3535lK0.a("PaintPreview.TabbedPlayer.Removed");
            AbstractC3364kK0.d("Browser.PaintPreview.TabbedPlayer.SnackbarCount", i4);
            AbstractC3364kK0.g("Browser.PaintPreview.TabbedPlayer.ExitCause", i2, 9);
            if (p11.b != 0) {
                Map map = C4163p11.f11045a;
                if (map.containsKey(Integer.valueOf(i2))) {
                    AbstractC3364kK0.i((String) map.get(Integer.valueOf(i2)), System.currentTimeMillis() - p11.b);
                }
            }
        }
    }
}
