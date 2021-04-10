package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: U21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U21 extends AnimatorListenerAdapter {
    public final /* synthetic */ C3998o31 F;
    public final /* synthetic */ X21 G;

    public U21(X21 x21, C3998o31 o31) {
        this.G = x21;
        this.F = o31;
    }

    public void onAnimationEnd(Animator animator) {
        X21 x21 = this.G;
        C3998o31[] o31Arr = x21.i;
        if (!(o31Arr.length == 0 || o31Arr[o31Arr.length - 1].d == this.F.d)) {
            x21.l(true);
        } else {
            x21.d(true);
        }
    }
}
