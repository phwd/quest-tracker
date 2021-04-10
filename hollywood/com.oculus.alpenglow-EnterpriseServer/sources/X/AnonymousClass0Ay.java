package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: X.0Ay  reason: invalid class name */
public class AnonymousClass0Ay extends AnimatorListenerAdapter {
    public final /* synthetic */ View A00;
    public final /* synthetic */ AnonymousClass0B0 A01;
    public final /* synthetic */ AnonymousClass0B1 A02;

    public AnonymousClass0Ay(AnonymousClass0B0 r1, AnonymousClass0B1 r2, View view) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = view;
    }

    public final void onAnimationCancel(Animator animator) {
        this.A02.A5p(this.A00);
    }

    public final void onAnimationEnd(Animator animator) {
        this.A02.A5q(this.A00);
    }

    public final void onAnimationStart(Animator animator) {
        this.A02.A5r(this.A00);
    }
}
