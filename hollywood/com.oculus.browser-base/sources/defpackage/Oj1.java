package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Oj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Oj1 implements AbstractC3374kP0, AbstractC0850Ny0 {
    public final AB F;
    public final Activity G;
    public final WindowAndroid H;
    public final M2 I;

    /* renamed from: J  reason: collision with root package name */
    public final C3203jP0 f8644J = new C3203jP0(this);
    public final Handler K;
    public final View L;
    public final View M;
    public final AbstractC5717y9 N;
    public final Vr1 O;
    public final Q31 P;
    public final Q31 Q;

    public Oj1(Activity activity, WindowAndroid windowAndroid, C5037u9 u9Var, M2 m2, AbstractC0956Pq0 pq0, Q31 q31, View view, View view2) {
        Handler handler = new Handler();
        this.K = handler;
        this.G = activity;
        this.H = windowAndroid;
        this.N = u9Var.e;
        this.L = view;
        this.M = view2;
        this.Q = q31;
        this.O = new Vr1(activity, handler, new C5470wj1());
        this.I = m2;
        m2.a(this);
        this.P = pq0;
        this.F = new AB(pq0, new Nj1(this));
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
        if (!DeviceFormFactor.b(this.H)) {
            C3203jP0 jp0 = this.f8644J;
            Objects.requireNonNull(jp0);
            Object obj = ThreadUtils.f10596a;
            ContextUtils.getApplicationContext().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, jp0.b);
            jp0.c = true;
            this.K.post(new Gj1(this));
        }
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
        C3203jP0 jp0 = this.f8644J;
        Objects.requireNonNull(jp0);
        Object obj = ThreadUtils.f10596a;
        jp0.c = false;
        if (jp0.b != null) {
            ContextUtils.getApplicationContext().getContentResolver().unregisterContentObserver(jp0.b);
        }
    }

    public final void f() {
        AbstractC5717y9 y9Var = this.N;
        if (y9Var != null) {
            ((C5887z9) y9Var).i(null);
        }
    }

    public final void g(Tab tab, String str) {
        if (tab == null || DeviceFormFactor.b(this.H)) {
            return;
        }
        if ((this.Q.get() == null || !((Boolean) this.Q.get()).booleanValue()) && DownloadUtils.c(tab)) {
            Vr1 vr1 = this.O;
            Resources resources = this.G.getResources();
            RunnableC5810yj1 yj1 = new RunnableC5810yj1(this);
            RunnableC5980zj1 zj1 = new RunnableC5980zj1(this);
            vr1.a(new XY(str, resources.getString(R.string.f53280_resource_name_obfuscated_RES_2131952645), resources.getString(R.string.f53270_resource_name_obfuscated_RES_2131952644), true, false, true, this.L, zj1, yj1, new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
            C5760yP0 V = C5760yP0.V(tab);
            if (V != null && V.F > 0) {
                V.G = 2;
            }
        }
    }

    public final void h(Integer num) {
        AbstractC5717y9 y9Var = this.N;
        if (y9Var != null) {
            ((C5887z9) y9Var).i(num);
        }
    }
}
