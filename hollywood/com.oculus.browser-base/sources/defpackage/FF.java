package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: FF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FF extends AnimatorListenerAdapter {
    public final boolean F;
    public final /* synthetic */ IF G;

    public FF(IF r9, boolean z) {
        this.G = r9;
        this.F = z;
        ArrayList arrayList = new ArrayList();
        View childAt = r9.b.getChildAt(0);
        if (childAt != null) {
            arrayList.add(ObjectAnimator.ofFloat(childAt, View.ALPHA, childAt.getAlpha(), 0.0f));
            arrayList.add(ObjectAnimator.ofFloat(childAt, View.TRANSLATION_Y, 0.0f, (float) r9.c));
        }
        if (z) {
            arrayList.add(ObjectAnimator.ofInt(r9.b.getBackground(), R6.f8811a, 127, 0));
        }
        if (!arrayList.isEmpty()) {
            r9.e = true;
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(195L);
            animatorSet.setInterpolator(G30.b);
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(this);
            animatorSet.start();
        }
    }

    public void onAnimationEnd(Animator animator) {
        IF r2 = this.G;
        r2.e = false;
        ViewGroup viewGroup = r2.b;
        viewGroup.removeView(viewGroup.getChildAt(0));
        if (this.F) {
            if (this.G.f8211a.isShowing()) {
                this.G.f8211a.dismiss();
            }
            this.G.a();
        }
    }
}
