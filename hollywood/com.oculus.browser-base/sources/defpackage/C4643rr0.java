package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: rr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4643rr0 extends AbstractC4758sY0 implements AbstractC1457Xx, AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public static C4643rr0 f11228a;
    public boolean b;
    public boolean c;
    public boolean d;
    public C1518Yx e;
    public ChromeActivity f;
    public boolean g;
    public long h;
    public Ol1 i;

    public C4643rr0() {
        if (e()) {
            this.i = new Ol1();
        }
        this.e = new C1518Yx(this, "OfflineIndicatorController");
        ApplicationStatus.h.b(this);
    }

    public static boolean e() {
        return !N.M6bsIDpc("OfflineIndicator", "bottom_offline_indicator", false);
    }

    @Override // defpackage.AbstractC1678aa
    public void a(int i2) {
        boolean z = false;
        if (!(i2 == 1 || i2 == 2)) {
            this.c = false;
        }
        if (i2 == 1) {
            C1518Yx yx = this.e;
            Objects.requireNonNull(yx);
            yx.a(NetworkChangeNotifier.f11004a.getCurrentConnectionType());
            if (this.e.f9307J == 4) {
                z = true;
            }
            f(z);
        }
    }

    @Override // defpackage.AbstractC1457Xx
    public void b(int i2) {
        if (i2 != 0) {
            f(i2 == 4);
        }
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        this.b = false;
        DownloadUtils.showDownloadManager(null, null, 11, true);
        AbstractC3364kK0.g("OfflineIndicator.CTR", 1, 2);
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
        this.b = false;
    }

    public final void f(boolean z) {
        Activity activity;
        boolean z2;
        int e2;
        WebContents l;
        if (z != this.g) {
            if (z) {
                this.d = false;
                this.h = SystemClock.elapsedRealtime();
            } else {
                this.d = SystemClock.elapsedRealtime() - this.h >= ((long) (N.M37SqSAy("OfflineIndicator", "stable_offline_wait_s", 20) * 1000));
            }
            this.g = z;
        }
        if (ApplicationStatus.getStateForApplication() == 1 && (activity = ApplicationStatus.e) != null && (activity instanceof ChromeActivity)) {
            ChromeActivity chromeActivity = (ChromeActivity) activity;
            if (chromeActivity.U() != null) {
                if (z) {
                    if (this.b) {
                        if (e()) {
                            this.i.a(false);
                        } else {
                            chromeActivity.U().j(this);
                        }
                    }
                } else if (!this.b) {
                    Tab K0 = chromeActivity.K0();
                    if (K0 != null && !K0.p() && !AbstractC2254ds0.f(K0) && !TextUtils.equals(K0.s(), "about:blank")) {
                        Tab K02 = chromeActivity.K0();
                        if (K02 != null && ((l = K02.l()) == null || l.d())) {
                            if (this.f != chromeActivity) {
                                this.f = chromeActivity;
                                K02.A(new C4473qr0(this));
                            }
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            if (!this.c || this.d) {
                                Drawable a2 = AbstractC5544x8.a(chromeActivity, R.drawable.f32350_resource_name_obfuscated_RES_2131231275);
                                C4076oY0 c2 = C4076oY0.c(chromeActivity.getString(R.string.f56470_resource_name_obfuscated_RES_2131952964), this, 0, 25);
                                c2.i = true;
                                c2.k = a2;
                                c2.g = -16777216;
                                c2.h = R.style.f72000_resource_name_obfuscated_RES_2132017773;
                                c2.j = 10000;
                                c2.d = chromeActivity.getString(R.string.f56500_resource_name_obfuscated_RES_2131952967);
                                c2.e = null;
                                if (e()) {
                                    Ol1 ol1 = this.i;
                                    if (ol1.I == null && ((e2 = ApplicationStatus.e(chromeActivity)) == 2 || e2 == 3)) {
                                        ol1.H = chromeActivity;
                                        ol1.I = c2;
                                        Pl1 pl1 = new Pl1(chromeActivity, ol1, c2, chromeActivity.b0);
                                        ol1.f8645J = pl1;
                                        pl1.f();
                                        ol1.f8645J.a();
                                        ol1.F.removeCallbacks(ol1.G);
                                        ol1.F.postDelayed(ol1.G, (long) ol1.I.j);
                                        chromeActivity.M0().Y.b(ol1);
                                        ApplicationStatus.g(ol1, chromeActivity);
                                    }
                                } else {
                                    chromeActivity.U().l(c2);
                                }
                                AbstractC3364kK0.g("OfflineIndicator.CTR", 0, 2);
                                this.b = true;
                                this.c = true;
                            }
                        }
                    }
                }
            }
        }
    }
}
