package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: Xu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xu1 extends AnimatorListenerAdapter {
    public final /* synthetic */ AbstractC2094cv1 F;
    public final /* synthetic */ View G;

    public Xu1(Zu1 zu1, AbstractC2094cv1 cv1, View view) {
        this.F = cv1;
        this.G = view;
    }

    public void onAnimationCancel(Animator animator) {
        this.F.a(this.G);
    }

    public void onAnimationEnd(Animator animator) {
        this.F.b(this.G);
    }

    public void onAnimationStart(Animator animator) {
        this.F.c(this.G);
    }
}
