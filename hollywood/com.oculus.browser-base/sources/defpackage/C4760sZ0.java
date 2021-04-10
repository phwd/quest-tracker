package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: sZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4760sZ0 extends AnimatorListenerAdapter {
    public final /* synthetic */ long F;
    public final /* synthetic */ int G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ boolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ AbstractC5780yZ0 f11281J;

    public C4760sZ0(AbstractC5780yZ0 yz0, long j, int i, boolean z, boolean z2) {
        this.f11281J = yz0;
        this.F = j;
        this.G = i;
        this.H = z;
        this.I = z2;
    }

    public void onAnimationEnd(Animator animator) {
        this.f11281J.w0(this.F, this.G, this.H, this.I);
        animator.removeListener(this);
    }
}
