package defpackage;

import android.app.Activity;
import org.chromium.base.Callback;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: Z11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z11 {

    /* renamed from: a  reason: collision with root package name */
    public View$OnLayoutChangeListenerC4337q21 f9314a;
    public C4678s21 b;
    public boolean c;
    public Runnable d;
    public int e;
    public View$OnLayoutChangeListenerC2948hv1 f;
    public ResourceManager g;
    public boolean h;
    public Activity i;
    public Callback j;
    public boolean k;

    public Z11(Activity activity, ResourceManager resourceManager, AbstractC2400ek ekVar, Q31 q31, Q31 q312, Callback callback) {
        this.i = activity;
        this.g = resourceManager;
        this.j = callback;
        this.b = new C4678s21(ekVar);
        this.f9314a = new View$OnLayoutChangeListenerC4337q21(ekVar, q31, q312);
    }

    public void a(Y11 y11) {
        this.f9314a.H.add(y11);
    }

    public void b(Y11 y11) {
        this.f9314a.H.remove(y11);
    }

    public final void c() {
        if (this.h) {
            this.f.H = null;
            this.g.a().d(this.e);
            this.h = false;
        }
    }
}
