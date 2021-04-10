package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: zi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5976zi0 {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f11760a;
    public final UH0 b;
    public C0330Fi0 c;
    public AbstractView$OnTouchListenerC4526r9 d;
    public MenuButton e;
    public ZH0 f;

    public C5976zi0(AbstractC1509Ys0 ys0, C0090Bk bk, WindowAndroid windowAndroid, AbstractC5806yi0 yi0, Runnable runnable, boolean z, Q31 q31, AbstractC1117Sg1 sg1, int i) {
        Activity activity = (Activity) windowAndroid.s0().get();
        this.f11760a = activity;
        this.e = (MenuButton) activity.findViewById(i);
        Map c2 = UH0.c(AbstractC0513Ii0.j);
        TH0 th0 = AbstractC0513Ii0.g;
        C0391Gi0 gi0 = new C0391Gi0(false, false);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = gi0;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(th0, lh0);
        TH0 th02 = AbstractC0513Ii0.h;
        C0452Hi0 hi0 = new C0452Hi0(sg1.a(), sg1.d());
        LH0 lh02 = new LH0(null);
        lh02.f8415a = hi0;
        hashMap.put(th02, lh02);
        QH0 qh0 = AbstractC0513Ii0.f;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        UH0 uh0 = new UH0(c2, null);
        this.b = uh0;
        C0330Fi0 fi0 = new C0330Fi0(uh0, z, new C5466wi0(this), runnable, sg1, q31, bk, yi0, ys0, windowAndroid);
        this.c = fi0;
        fi0.d.l(new C5636xi0(this));
        MenuButton menuButton = this.e;
        if (menuButton != null) {
            this.f = ZH0.a(uh0, menuButton, new C0574Ji0());
        }
    }

    public void a() {
        C0330Fi0 fi0 = this.c;
        if (fi0 != null) {
            if (fi0.c != null) {
                ((C5887z9) fi0.e).f11727J.remove(fi0);
                fi0.c = null;
            }
            if (fi0.n != null) {
                C2249dq1 a2 = C2249dq1.a();
                a2.c.c(fi0.n);
                fi0.n = null;
            }
            this.c = null;
        }
        ZH0 zh0 = this.f;
        if (zh0 != null) {
            zh0.b();
            this.f = null;
        }
        this.e = null;
        this.d = null;
    }

    public Animator b(boolean z) {
        float f2;
        C0330Fi0 fi0 = this.c;
        MenuButton menuButton = this.e;
        boolean z2 = menuButton != null && menuButton.getLayoutDirection() == 1;
        Objects.requireNonNull(fi0);
        float f3 = 0.0f;
        if (z) {
            float f4 = fi0.t.getDisplayMetrics().density;
            int i = fi0.v;
            if (z2) {
                i = -i;
            }
            float f5 = ((float) i) * f4;
            f2 = 0.0f;
            f3 = f5;
        } else {
            f2 = 1.0f;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(WH0.a(fi0.h, AbstractC0513Ii0.i, f3), WH0.a(fi0.h, AbstractC0513Ii0.f8243a, f2));
        return animatorSet;
    }

    public boolean c() {
        MenuButton menuButton;
        AbstractView$OnTouchListenerC4526r9 r9Var = this.d;
        if (r9Var == null || (menuButton = this.e) == null) {
            return false;
        }
        return ((C4697s9) r9Var).a(menuButton.F, false);
    }

    public void d(boolean z) {
        C0330Fi0 fi0 = this.c;
        if (fi0 != null) {
            fi0.s = z;
            if (z) {
                fi0.a(false);
                return;
            }
            if ((C2249dq1.a().f.b != null) && fi0.m) {
                fi0.b(false);
            }
        }
    }

    public void e(boolean z) {
        C0330Fi0 fi0 = this.c;
        if (fi0 != null) {
            fi0.h.j(AbstractC0513Ii0.f, z);
        }
    }
}
