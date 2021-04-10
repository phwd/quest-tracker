package defpackage;

import android.view.ActionMode;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;

/* renamed from: Oq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Oq1 extends AbstractC1740ar1 implements Qq1 {
    public static final Runnable F = new Nq1();
    public UrlBarApi26 G;
    public Tq1 H;
    public C3493l60 I;

    /* renamed from: J  reason: collision with root package name */
    public Uy1 f8652J;
    public Runnable K;
    public Runnable L;

    public Oq1(UrlBarApi26 urlBarApi26, Uy1 uy1, ActionMode.Callback callback, Callback callback2, Hq1 hq1, C3493l60 l60) {
        Runnable runnable = F;
        this.K = runnable;
        this.L = runnable;
        this.G = urlBarApi26;
        this.I = l60;
        this.f8652J = uy1;
        Map c = UH0.c(Wq1.n);
        TH0 th0 = Wq1.f9176a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = callback;
        HashMap hashMap = (HashMap) c;
        hashMap.put(th0, lh0);
        TH0 th02 = Wq1.m;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = uy1;
        hashMap.put(th02, lh02);
        TH0 th03 = Wq1.d;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = hq1;
        hashMap.put(th03, lh03);
        UH0 uh0 = new UH0(c, null);
        ZH0.a(uh0, urlBarApi26, new Kq1());
        this.H = new Tq1(uh0, callback2);
    }

    @Override // defpackage.AbstractC1740ar1
    public void e(boolean z) {
        this.G.removeCallbacks(this.K);
    }

    public int f() {
        return this.G.getSelectionStart();
    }

    public String g() {
        AbstractC1286Vc vc = this.G.K;
        if (vc == null) {
            return "";
        }
        return vc.b();
    }

    public String h() {
        AbstractC1286Vc vc = this.G.K;
        if (vc == null) {
            return "";
        }
        return vc.f();
    }

    public void i(boolean z, boolean z2) {
        this.G.removeCallbacks(this.L);
        if (z) {
            j(32, false);
            this.I.i(this.G);
            return;
        }
        Lq1 lq1 = new Lq1(this);
        this.L = lq1;
        this.G.postDelayed(lq1, z2 ? 150 : 0);
        j(16, true);
    }

    public final void j(int i, boolean z) {
        this.G.removeCallbacks(this.K);
        Uy1 uy1 = this.f8652J;
        if (uy1 != null && uy1.f9058a.getAttributes().softInputMode != i) {
            if (z) {
                Mq1 mq1 = new Mq1(this, i);
                this.K = mq1;
                this.G.postDelayed(mq1, 300);
                return;
            }
            this.f8652J.f9058a.setSoftInputMode(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        if (defpackage.Pq1.f8716a.contains(r1) != false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        if (r5 == null) goto L_0x00ba;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean k(defpackage.Pq1 r13, int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 207
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Oq1.k(Pq1, int, int):boolean");
    }

    public boolean m() {
        AbstractC1286Vc vc = this.G.K;
        if (vc == null) {
            return false;
        }
        return vc.h();
    }
}
