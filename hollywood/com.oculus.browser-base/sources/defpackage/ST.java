package defpackage;

import J.N;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: ST  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ST implements Z9, AbstractC2200da, View.OnSystemUiVisibilityChangeListener, UT {
    public final Activity F;
    public final Handler G;
    public final C1078Rq0 H;
    public final AbstractC0956Pq0 I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f8895J;
    public final C1322Vq0 K = new C1322Vq0();
    public WebContents L;
    public View M;
    public Tab N;
    public VT O;
    public C1184Ti1 P;
    public View.OnLayoutChangeListener Q;
    public VT R;
    public Y2 S;
    public AbstractC1099Sa1 T;
    public Tab U;
    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy V;

    public ST(Activity activity, AbstractC0956Pq0 pq0, boolean z) {
        this.F = activity;
        this.I = pq0;
        ((C1078Rq0) pq0).l(new LT(this));
        this.G = new RT(this);
        C1078Rq0 rq0 = new C1078Rq0();
        this.H = rq0;
        rq0.m(Boolean.FALSE);
        this.f8895J = z;
    }

    @Override // defpackage.UT
    public boolean a() {
        return ((Boolean) this.H.H).booleanValue();
    }

    @Override // defpackage.UT
    public void b(TT tt) {
        this.K.b(tt);
    }

    @Override // defpackage.UT
    public void c() {
        if (a()) {
            this.H.m(Boolean.FALSE);
            WebContents webContents = this.L;
            if (webContents == null || this.N == null) {
                this.R = null;
            } else {
                View view = this.M;
                C1184Ti1 ti1 = this.P;
                if (ti1 != null) {
                    ti1.b.cancel();
                    this.P = null;
                }
                this.G.removeMessages(1);
                this.G.removeMessages(2);
                h(67108864);
                view.setSystemUiVisibility(view.getSystemUiVisibility() & -1025 & -4616);
                View.OnLayoutChangeListener onLayoutChangeListener = this.Q;
                if (onLayoutChangeListener != null) {
                    view.removeOnLayoutChangeListener(onLayoutChangeListener);
                }
                OT ot = new OT(this, view);
                this.Q = ot;
                view.addOnLayoutChangeListener(ot);
                if (webContents != null && !webContents.g()) {
                    webContents.g0();
                }
            }
            this.L = null;
            this.M = null;
            this.N = null;
            this.O = null;
        }
        l(true);
    }

    @Override // defpackage.UT
    public void d(Tab tab) {
        k(tab, null);
        if (tab == this.U) {
            c();
        }
        Iterator it = this.K.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((TT) uq0.next()).a(tab);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2200da
    public void e(Activity activity, boolean z) {
        C1184Ti1 ti1;
        if (this.F == activity) {
            if (!z && (ti1 = this.P) != null) {
                ti1.b.cancel();
                this.P = null;
            }
            this.G.removeMessages(1);
            this.G.removeMessages(2);
            if (this.N != null && a() && z) {
                this.G.sendEmptyMessageDelayed(1, 200);
            }
        }
    }

    @Override // defpackage.UT
    public void f(TT tt) {
        this.K.c(tt);
    }

    public final int g(int i) {
        if (this.O == null) {
            return i | 4615;
        }
        throw null;
    }

    public final void h(int i) {
        Window window = this.F.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int i2 = attributes.flags;
        if ((i2 & i) != 0) {
            attributes.flags = (~i) & i2;
            window.setAttributes(attributes);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0077, code lost:
        if ((((java.lang.Integer) r2.getClass().getMethod("getWindowMode", null).invoke(r2, null)).intValue() & ((java.lang.Integer) java.lang.Class.forName("android.view.WindowManagerPolicy").getField("WINDOW_MODE_FREESTYLE").get(null)).intValue()) != 0) goto L_0x007b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i(org.chromium.chrome.browser.tab.Tab r11, defpackage.VT r12) {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ST.i(org.chromium.chrome.browser.tab.Tab, VT):void");
    }

    public final void j(AbstractViewGroup$OnHierarchyChangeListenerC1520Yy yy) {
        AbstractViewGroup$OnHierarchyChangeListenerC1520Yy yy2 = this.V;
        if (yy != yy2) {
            if (yy2 != null) {
                yy2.I.c(this);
            }
            this.V = yy;
            if (yy != null) {
                yy.I.b(this);
            }
        }
    }

    public final void k(Tab tab, Runnable runnable) {
        Y51 c = Y51.c(tab);
        if (runnable == null) {
            c.G.remove("EnterFullscreen");
        } else {
            c.G.put("EnterFullscreen", runnable);
        }
    }

    public final void l(boolean z) {
        WebContents l;
        GestureListenerManagerImpl s0;
        Tab tab = this.U;
        if (tab != null && !tab.isHidden() && (l = this.U.l()) != null && (s0 = GestureListenerManagerImpl.s0(l)) != null) {
            long j = s0.K;
            if (j != 0) {
                N.M6a5zchR(j, s0, z);
            }
        }
    }

    public void onSystemUiVisibilityChange(int i) {
        if (this.N != null && a()) {
            this.G.sendEmptyMessageDelayed(1, 200);
        }
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 5 && this.f8895J) {
            c();
        } else if (i == 6) {
            ApplicationStatus.h(this);
            ApplicationStatus.i.c(this);
        }
    }
}
