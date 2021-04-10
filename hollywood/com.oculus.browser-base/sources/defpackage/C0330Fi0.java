package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Fi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0330Fi0 implements A9 {

    /* renamed from: a  reason: collision with root package name */
    public Callback f8035a;
    public B9 b;
    public AbstractView$OnTouchListenerC4526r9 c;
    public C1078Rq0 d;
    public AbstractC5717y9 e;
    public final C0090Bk f;
    public final AbstractC5806yi0 g;
    public final UH0 h;
    public final Runnable i;
    public final AbstractC1117Sg1 j;
    public final Activity k;
    public final C3493l60 l;
    public boolean m;
    public Runnable n;
    public Q31 o;
    public int p = -1;
    public int q = -1;
    public Q31 r;
    public boolean s;
    public Resources t;
    public AbstractC1509Ys0 u;
    public final int v;

    public C0330Fi0(UH0 uh0, boolean z, Q31 q31, Runnable runnable, AbstractC1117Sg1 sg1, Q31 q312, C0090Bk bk, AbstractC5806yi0 yi0, AbstractC1509Ys0 ys0, WindowAndroid windowAndroid) {
        this.h = uh0;
        this.m = z;
        this.o = q31;
        this.i = runnable;
        this.j = sg1;
        this.r = q312;
        this.f = bk;
        this.g = yi0;
        sg1.K.b(new C0086Bi0(this));
        C0147Ci0 ci0 = new C0147Ci0(this);
        this.f8035a = ci0;
        this.u = ys0;
        ys0.g(ci0);
        Activity activity = (Activity) windowAndroid.s0().get();
        this.k = activity;
        this.t = activity.getResources();
        this.d = new C1078Rq0();
        this.l = windowAndroid.u0();
        this.v = this.t.getDimensionPixelSize(R.dimen.f26450_resource_name_obfuscated_RES_2131166264);
    }

    public final void a(boolean z) {
        this.h.m(AbstractC0513Ii0.g, new C0391Gi0(false, z));
        c(false, 0);
    }

    public final void b(boolean z) {
        if (!this.s) {
            c(true, C2249dq1.a().f.b.f9494a);
            this.h.m(AbstractC0513Ii0.g, new C0391Gi0(true, z));
        }
    }

    public final void c(boolean z, int i2) {
        if (z) {
            this.h.m(AbstractC0513Ii0.c, this.t.getString(i2));
        } else {
            this.h.m(AbstractC0513Ii0.c, this.t.getString(R.string.f46230_resource_name_obfuscated_RES_2131951940));
        }
    }
}
