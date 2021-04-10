package defpackage;

import android.animation.ValueAnimator;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: Xk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xk1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ int G;
    public final /* synthetic */ int H;
    public final /* synthetic */ int I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ int f9231J;
    public final /* synthetic */ int K;
    public final /* synthetic */ int L;
    public final /* synthetic */ ToolbarPhone M;

    public Xk1(ToolbarPhone toolbarPhone, boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
        this.M = toolbarPhone;
        this.F = z;
        this.G = i;
        this.H = i2;
        this.I = i3;
        this.f9231J = i4;
        this.K = i5;
        this.L = i6;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (this.F) {
            this.M.E0 = (int) AbstractC4089od0.e((float) this.G, (float) this.H, animatedFraction);
        }
        ToolbarPhone toolbarPhone = this.M;
        int a2 = AbstractC1270Uv.a(this.I, this.f9231J, animatedFraction);
        int i = ToolbarPhone.U;
        toolbarPhone.x0(a2);
        this.M.t0(AbstractC1270Uv.a(this.K, this.L, animatedFraction));
    }
}
