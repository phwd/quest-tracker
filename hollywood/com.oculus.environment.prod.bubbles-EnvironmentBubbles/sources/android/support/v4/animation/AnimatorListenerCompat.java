package android.support.v4.animation;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.GROUP_ID})
public interface AnimatorListenerCompat {
    void onAnimationCancel(ValueAnimatorCompat valueAnimatorCompat);

    void onAnimationEnd(ValueAnimatorCompat valueAnimatorCompat);

    void onAnimationRepeat(ValueAnimatorCompat valueAnimatorCompat);

    void onAnimationStart(ValueAnimatorCompat valueAnimatorCompat);
}
