package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

/* renamed from: l9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3501l9 extends AnimatorListenerAdapter {
    public final /* synthetic */ int F;
    public final /* synthetic */ ImageView[] G;

    public C3501l9(C4185p9 p9Var, int i, ImageView[] imageViewArr) {
        this.F = i;
        this.G = imageViewArr;
    }

    public void onAnimationStart(Animator animator) {
        for (int i = 0; i < this.F; i++) {
            this.G[i].setAlpha(0.0f);
        }
    }
}
