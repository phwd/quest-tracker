package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.tabs.TabLayout;

/* renamed from: v81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5206v81 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ TabLayout F;

    public C5206v81(TabLayout tabLayout) {
        this.F = tabLayout;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
    }
}
