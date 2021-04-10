package defpackage;

import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X7 extends AbstractC2264dv1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 f9195a;

    public X7(LayoutInflater$Factory2C3156j8 j8Var) {
        this.f9195a = j8Var;
    }

    @Override // defpackage.AbstractC2094cv1
    public void b(View view) {
        this.f9195a.Y.setAlpha(1.0f);
        this.f9195a.b0.d(null);
        this.f9195a.b0 = null;
    }

    @Override // defpackage.AbstractC2264dv1, defpackage.AbstractC2094cv1
    public void c(View view) {
        this.f9195a.Y.setVisibility(0);
        this.f9195a.Y.sendAccessibilityEvent(32);
        if (this.f9195a.Y.getParent() instanceof View) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            ((View) this.f9195a.Y.getParent()).requestApplyInsets();
        }
    }
}
