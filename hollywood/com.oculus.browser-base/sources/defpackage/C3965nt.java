package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.content_public.browser.WebContents;

/* renamed from: nt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3965nt extends AbstractC3056ia1 implements AbstractC2230dk {
    public final ChromeActivity M;
    public final Q31 N;
    public final UT O;
    public final AbstractC1888bk P;
    public final C3794mt Q = new C3794mt();
    public Tab R;
    public ViewGroup S;
    public boolean T;
    public boolean U;
    public View V;
    public int W;
    public boolean X;
    public int Y = -1;

    public C3965nt(ChromeActivity chromeActivity, Q31 q31) {
        super(chromeActivity);
        this.M = chromeActivity;
        this.N = q31;
        this.O = chromeActivity.S0();
        C1551Zj M0 = chromeActivity.M0();
        this.P = M0;
        M0.Y.b(this);
    }

    public static int m(Resources resources, AbstractC2400ek ekVar) {
        return ((C1551Zj) ekVar).M - resources.getDimensionPixelSize(R.dimen.f25670_resource_name_obfuscated_RES_2131166186);
    }

    public static boolean n(Tab tab) {
        Y51 c = Y51.c(tab);
        Object obj = Boolean.FALSE;
        if (c.G.containsKey("isTabModalDialogShowing")) {
            obj = c.e("isTabModalDialogShowing");
        }
        return ((Boolean) obj).booleanValue();
    }

    @Override // defpackage.AbstractC2575fl0
    public void e(UH0 uh0) {
        this.U = false;
        ((C1343Wa1) this.N.get()).f9156a.c(this.Y);
        this.Y = -1;
        i(false);
        ModalDialogView modalDialogView = this.f10146J;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        if (modalDialogView.isAttachedToWindow()) {
            ModalDialogView modalDialogView2 = this.f10146J;
            modalDialogView2.clearFocus();
            this.I.animate().cancel();
            this.I.animate().setDuration(200).alpha(0.0f).setInterpolator(animation.InterpolatorC5286vf.f).setListener(new C2543fa1(this, modalDialogView2)).start();
        } else {
            this.I.animate().cancel();
        }
        ZH0 zh0 = this.K;
        if (zh0 != null) {
            zh0.b();
            this.K = null;
        }
        this.f10146J = null;
    }

    @Override // defpackage.AbstractC2230dk
    public void h(int i, int i2) {
        this.W = i;
        this.X = true;
    }

    @Override // defpackage.AbstractC3056ia1
    public void i(boolean z) {
        Uk1 uk1 = this.M.b1.V;
        if (uk1 != null) {
            View k = uk1.k();
            if (z) {
                this.R = this.M.K0();
                ContextualSearchManager contextualSearchManager = this.M.M0;
                if (contextualSearchManager != null) {
                    contextualSearchManager.i(0);
                }
                WebContents l = this.R.l();
                if (l != null) {
                    g(l, true);
                }
                o(true);
                this.M.b1.V.p(false, 12);
                k.setEnabled(false);
                return;
            }
            WebContents l2 = this.R.l();
            if (l2 != null) {
                g(l2, false);
            }
            o(false);
            k.setEnabled(true);
            this.R = null;
        }
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        if (this.G != null && this.U && AbstractC2571fk.a(this.P)) {
            this.U = false;
            f();
        }
    }

    @Override // defpackage.AbstractC2230dk
    public void k(int i, int i2) {
        this.X = true;
    }

    @Override // defpackage.AbstractC3056ia1
    public void l(boolean z) {
        if (z) {
            this.f10146J.announceForAccessibility(AbstractC2575fl0.d(this.G));
            this.f10146J.setImportantForAccessibility(1);
            this.f10146J.requestFocus();
        } else {
            this.f10146J.clearFocus();
            this.f10146J.setImportantForAccessibility(4);
        }
        if (z != this.T) {
            this.T = z;
            if (z) {
                this.I.bringToFront();
                return;
            }
            AbstractC2417ep1.k(this.I);
            AbstractC2417ep1.h(this.S, this.I, this.V, false);
        }
    }

    public final void o(boolean z) {
        Y51 c = Y51.c(this.R);
        Object valueOf = Boolean.valueOf(z);
        Map map = c.G;
        if (valueOf == null) {
            valueOf = Y51.F;
        }
        map.put("isTabModalDialogShowing", valueOf);
        C3794mt mtVar = this.Q;
        Tab tab = this.R;
        Objects.requireNonNull(mtVar);
        if (tab != null) {
            mtVar.m(Integer.valueOf(n(tab) ? 1 : 3));
        }
        this.O.d(this.R);
        if (!z || !this.R.l().N().b()) {
            C1786b61.j(this.R, 1, !((C1551Zj) this.P).c0);
        } else {
            ((C1551Zj) this.P).j(true);
        }
    }
}
