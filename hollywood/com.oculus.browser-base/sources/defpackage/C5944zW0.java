package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: zW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5944zW0 extends AnimatorListenerAdapter {
    public final /* synthetic */ int F;
    public final /* synthetic */ AW0 G;

    public C5944zW0(AW0 aw0, int i) {
        this.G = aw0;
        this.F = i;
    }

    public void onAnimationEnd(Animator animator) {
        this.G.Q(this.F, false);
    }
}
