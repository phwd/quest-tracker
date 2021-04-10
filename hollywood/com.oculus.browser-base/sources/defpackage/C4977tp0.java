package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: tp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4977tp0 extends AnimatorListenerAdapter {
    public final /* synthetic */ C5147up0 F;

    public C4977tp0(C5147up0 up0) {
        this.F = up0;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.F.f != null) {
            int i = 0;
            while (true) {
                C5147up0 up0 = this.F;
                IZ0[] iz0Arr = up0.f;
                if (i >= iz0Arr.length) {
                    break;
                }
                iz0Arr[i].k = (float) (up0.e * i);
                i++;
            }
        }
        C5147up0 up02 = this.F;
        up02.M = false;
        ((C3565lZ0) up02.E).O0 = false;
    }
}
