package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: X.1Ec  reason: invalid class name and case insensitive filesystem */
public class C05961Ec extends AnimatorListenerAdapter {
    public final /* synthetic */ View A00;
    public final /* synthetic */ ViewPropertyAnimator A01;
    public final /* synthetic */ AnonymousClass1Ef A02;
    public final /* synthetic */ AnonymousClass1Ah A03;

    public C05961Ec(AnonymousClass1Ef r1, AnonymousClass1Ah r2, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.A02 = r1;
        this.A03 = r2;
        this.A00 = view;
        this.A01 = viewPropertyAnimator;
    }

    public final void onAnimationCancel(Animator animator) {
        this.A00.setAlpha(1.0f);
    }

    public final void onAnimationEnd(Animator animator) {
        this.A01.setListener(null);
        AnonymousClass1Ef r2 = this.A02;
        AnonymousClass1Ah r1 = this.A03;
        AnonymousClass1BQ r0 = ((AnonymousClass1Al) r2).A04;
        if (r0 != null) {
            r0.A6j(r1);
        }
        r2.A00.remove(r1);
        r2.A0C();
    }

    public final void onAnimationStart(Animator animator) {
    }
}
