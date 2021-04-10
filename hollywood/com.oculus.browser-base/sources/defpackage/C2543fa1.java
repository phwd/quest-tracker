package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: fa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2543fa1 extends AnimatorListenerAdapter {
    public final /* synthetic */ View F;
    public final /* synthetic */ AbstractC3056ia1 G;

    public C2543fa1(AbstractC3056ia1 ia1, View view) {
        this.G = ia1;
        this.F = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.G.I.setVisibility(8);
        this.G.I.removeView(this.F);
    }
}
