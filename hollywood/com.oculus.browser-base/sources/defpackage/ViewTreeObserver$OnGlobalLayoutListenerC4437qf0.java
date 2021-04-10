package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import java.util.Set;

/* renamed from: qf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC4437qf0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ DialogC0504If0 F;

    public ViewTreeObserver$OnGlobalLayoutListenerC4437qf0(DialogC0504If0 if0) {
        this.F = if0;
    }

    public void onGlobalLayout() {
        this.F.i0.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        DialogC0504If0 if0 = this.F;
        Set set = if0.l0;
        if (set == null || set.size() == 0) {
            if0.h(true);
            return;
        }
        animation.Animation$AnimationListenerC4607rf0 rf0 = new animation.Animation$AnimationListenerC4607rf0(if0);
        int firstVisiblePosition = if0.i0.getFirstVisiblePosition();
        boolean z = false;
        for (int i = 0; i < if0.i0.getChildCount(); i++) {
            View childAt = if0.i0.getChildAt(i);
            if (if0.l0.contains((C2392eh0) if0.j0.getItem(firstVisiblePosition + i))) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration((long) if0.M0);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (!z) {
                    alphaAnimation.setAnimationListener(rf0);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }
}
