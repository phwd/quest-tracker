package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageButton;

/* renamed from: KR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KR0 {

    /* renamed from: a  reason: collision with root package name */
    public final ImageButton f8364a;
    public final View b;
    public final AnimatorSet c;
    public final AnimatorSet d;
    public final int e;

    public KR0(ImageButton imageButton, View view, int i) {
        this.f8364a = imageButton;
        this.b = view;
        int dimensionPixelSize = imageButton.getResources().getDimensionPixelSize(i);
        this.e = dimensionPixelSize;
        AnimatorSet animatorSet = new AnimatorSet();
        this.c = animatorSet;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0.0f);
        animation.InterpolatorC5286vf vfVar = animation.InterpolatorC5286vf.e;
        ofFloat.setInterpolator(vfVar);
        ofFloat.setDuration(200L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageButton, View.ALPHA, 1.0f);
        ofFloat2.setInterpolator(animation.InterpolatorC5286vf.g);
        ofFloat2.setDuration(150L);
        ofFloat2.addListener(new IR0(this));
        animatorSet.playSequentially(ofFloat, ofFloat2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.d = animatorSet2;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageButton, View.ALPHA, 0.0f);
        ofFloat3.setInterpolator(animation.InterpolatorC5286vf.f);
        ofFloat3.setDuration(150L);
        ofFloat3.addListener(new JR0(this));
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, (float) (-dimensionPixelSize));
        ofFloat4.setInterpolator(vfVar);
        ofFloat4.setDuration(200L);
        animatorSet2.playSequentially(ofFloat3, ofFloat4);
    }
}
