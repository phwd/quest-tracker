package android.support.v4.animation;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.GROUP_ID})
public interface AnimatorUpdateListenerCompat {
    void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat);
}
