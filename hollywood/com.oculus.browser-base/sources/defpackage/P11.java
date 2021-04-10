package defpackage;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.Window;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: P11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P11 extends Y11 implements AbstractC4371qE, Ul1 {
    public static final int F = Color.argb(1, 0, 0, 0);
    public final Window G;
    public final boolean H;
    public AbstractC2260du0 I;

    /* renamed from: J  reason: collision with root package name */
    public final O11 f8662J;
    public final Y2 K;
    public final AbstractC0612Ka1 L;
    public final C2921hm1 M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public AbstractC0124Ca1 R;
    public C1128Sl S = new C1128Sl();
    public QK T;
    public Tab U;
    public boolean V;
    public boolean W;
    public int X;
    public float Y;
    public float Z;
    public boolean a0;
    public int b0;
    public int c0;

    public P11(Window window, boolean z, Resources resources, O11 o11, AbstractC1509Ys0 ys0, M2 m2, C1595a3 a3Var, C2921hm1 hm1) {
        this.G = window;
        this.H = z;
        this.f8662J = o11;
        this.N = AbstractC2934hr.b(resources, false);
        this.O = AbstractC2934hr.b(resources, true);
        this.P = AbstractC2934hr.a(resources, false);
        this.Q = AbstractC2934hr.a(resources, true);
        this.b0 = 0;
        this.K = new L11(this, a3Var);
        this.L = new M11(this);
        if (ys0 != null) {
            ys0.g(this.S.b(new K11(this)));
        }
        m2.a(this);
        this.M = hm1;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.K.destroy();
        AbstractC2260du0 du0 = this.I;
        if (du0 != null) {
            ((AbstractC3838n70) du0).y0.c(this.T);
        }
        AbstractC0124Ca1 ca1 = this.R;
        if (ca1 != null) {
            ((AbstractC0246Ea1) ca1).f.c(this.L);
        }
        C1128Sl sl = this.S;
        if (sl != null) {
            sl.a();
            this.S = null;
        }
    }

    @Override // defpackage.Y11
    public void f(int i) {
        this.b0 = i;
        i();
    }

    public void i() {
        int i;
        Objects.requireNonNull(this.f8662J);
        if (F == 0) {
            i = this.W ? this.Q : this.P;
        } else if (this.H) {
            i = -16777216;
        } else if (this.V) {
            i = (!this.W || !Pj1.a()) ? this.N : this.O;
        } else {
            Tab tab = this.U;
            if (tab != null) {
                tab.Q();
            }
            C2921hm1 hm1 = this.M;
            Tab tab2 = this.U;
            int i2 = this.W ? this.Q : this.P;
            Objects.requireNonNull(hm1);
            i = (tab2 == null || hm1.P) ? i2 : hm1.H;
        }
        this.c0 = i;
        int i3 = this.b0;
        if (i3 != 0) {
            i = i3;
        }
        if (this.X == 0) {
            this.X = this.G.getDecorView().getRootView().getResources().getColor(R.color.f10000_resource_name_obfuscated_RES_2131099690);
        }
        int i4 = this.X;
        int a2 = AbstractC1270Uv.a(i, -16777216 & i4, this.Y * (((float) (i4 >>> 24)) / 255.0f));
        if (!AbstractC2417ep1.i()) {
            AbstractC3153j7.h(this.G.getDecorView().getRootView(), !AbstractC1270Uv.g(a2));
            AbstractC3153j7.g(this.G, a2);
        }
    }
}
