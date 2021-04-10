package defpackage;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/* renamed from: GF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GF extends AnimatorListenerAdapter implements View.OnLayoutChangeListener {
    public final /* synthetic */ IF F;

    public GF(IF r1, EF ef) {
        this.F = r1;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.b.getChildAt(0).removeOnLayoutChangeListener(this);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this.F.b.getBackground(), R6.f8811a, 0, 255);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.F.b, View.ALPHA, 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofInt, ofFloat);
        animatorSet.setDuration(225L);
        animatorSet.setInterpolator(G30.e);
        animatorSet.start();
    }
}
