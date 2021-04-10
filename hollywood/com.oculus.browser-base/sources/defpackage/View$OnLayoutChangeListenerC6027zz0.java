package defpackage;

import J.N;
import android.os.Handler;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: zz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC6027zz0 extends AbstractC6022zx1 implements AbstractC0576Jj, View.OnLayoutChangeListener {
    public final UH0 G;
    public final Runnable H;
    public final WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public final WebContents f11784J;
    public final AbstractC5007tz0 K;
    public final Handler L = new Handler();
    public final AbstractC4371qE M;
    public final M2 N;
    public final View O;
    public final AbstractC4448qj P;
    public final int Q;
    public int R = 0;
    public int S = -1;

    public View$OnLayoutChangeListenerC6027zz0(UH0 uh0, Runnable runnable, WebContents webContents, WebContents webContents2, AbstractC5007tz0 tz0, View view, int i, M2 m2, AbstractC4448qj qjVar) {
        super(webContents2);
        this.O = view;
        this.P = qjVar;
        this.I = webContents;
        this.f11784J = webContents2;
        this.Q = i;
        this.G = uh0;
        uh0.m(AbstractC0182Cz0.b, new RunnableC5347vz0(this));
        this.H = runnable;
        this.K = tz0;
        uh0.l(AbstractC0182Cz0.f7851a, ((int) (((float) view.getHeight()) * 0.9f)) - i);
        this.N = m2;
        C5857yz0 yz0 = new C5857yz0(this);
        this.M = yz0;
        m2.a(yz0);
    }

    public final void b(ChromeActivity chromeActivity, boolean z) {
        C1343Wa1 W0 = chromeActivity.W0();
        if (W0 != null) {
            if (z) {
                this.S = W0.a();
                return;
            }
            W0.f9156a.c(this.S);
            this.S = -1;
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void destroy() {
        this.N.b(this.M);
        int i = this.R;
        if (i == 1) {
            ServiceWorkerPaymentAppBridge.a(this.I, 16);
        } else if (i == 2) {
            ServiceWorkerPaymentAppBridge.a(this.I, 13);
        } else if (i == 3) {
            ServiceWorkerPaymentAppBridge.a(this.I, 15);
        } else if (i == 4) {
            ServiceWorkerPaymentAppBridge.a(this.I, 14);
        }
        this.L.removeCallbacksAndMessages(null);
        ChromeActivity J0 = ChromeActivity.J0(this.f11784J);
        if (J0 != null) {
            b(J0, false);
            GP0 gp0 = (GP0) ((C5638xj) this.P).M.get();
            if (gp0 != null) {
                gp0.b.a(true);
            }
        }
        super.destroy();
    }

    @Override // defpackage.AbstractC6022zx1
    public void didChangeVisibleSecurityState() {
        if (!N.Me8yLh8j(this.f11784J)) {
            this.L.post(new RunnableC5517wz0(this));
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFailLoad(boolean z, int i, GURL gurl) {
        if (z) {
            this.L.post(new RunnableC5687xz0(this));
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (!navigationHandle.c && !N.Me8yLh8j(this.f11784J)) {
            this.L.post(new RunnableC5517wz0(this));
        }
    }

    @Override // defpackage.AbstractC0576Jj
    public void f(float f, float f2) {
    }

    @Override // defpackage.AbstractC0576Jj
    public void g(int i) {
        C5894zB0 zb0 = ((AB0) this.K).t;
        zb0.f11732a = true;
        zb0.b();
        ChromeActivity J0 = ChromeActivity.J0(this.f11784J);
        C5638xj xjVar = (C5638xj) this.P;
        Objects.requireNonNull(xjVar);
        Map c = UH0.c(MP0.l);
        NH0 nh0 = MP0.f8474a;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = 0;
        HashMap hashMap = (HashMap) c;
        hashMap.put(nh0, jh0);
        MH0 mh0 = MP0.b;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(mh0, gh0);
        OH0 oh0 = MP0.c;
        BottomSheet bottomSheet = xjVar.F;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = bottomSheet;
        hashMap.put(oh0, lh0);
        MH0 mh02 = MP0.d;
        GH0 gh02 = new GH0(null);
        gh02.f8081a = false;
        hashMap.put(mh02, gh02);
        OH0 oh02 = MP0.f;
        RunnableC5298vj vjVar = new RunnableC5298vj(xjVar);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = vjVar;
        ((GP0) ((C5638xj) this.P).M.get()).a(AbstractC2531fV.o(hashMap, oh02, lh02, c, null));
        b(J0, true);
    }

    @Override // defpackage.AbstractC0576Jj
    public void h(AbstractC4277pj pjVar) {
    }

    @Override // defpackage.AbstractC0576Jj
    public void i(int i) {
        if (i == 0) {
            this.R = 1;
            this.L.post(this.H);
        }
    }

    @Override // defpackage.AbstractC0576Jj
    public void j() {
    }

    @Override // defpackage.AbstractC0576Jj
    public void k(int i) {
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.G.l(AbstractC0182Cz0.f7851a, ((int) (((float) this.O.getHeight()) * 0.9f)) - this.Q);
    }
}
