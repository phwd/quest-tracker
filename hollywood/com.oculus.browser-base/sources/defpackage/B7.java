package defpackage;

import android.animation.ValueAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: B7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B7 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ CoordinatorLayout F;
    public final /* synthetic */ AppBarLayout G;
    public final /* synthetic */ AppBarLayout.Behavior H;

    public B7(AppBarLayout.Behavior behavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        this.H = behavior;
        this.F = coordinatorLayout;
        this.G = appBarLayout;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.H.t(this.F, this.G, ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
