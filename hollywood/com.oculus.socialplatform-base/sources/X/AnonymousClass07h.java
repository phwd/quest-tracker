package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: X.07h  reason: invalid class name */
public class AnonymousClass07h extends AnimatorListenerAdapter {
    public final /* synthetic */ View A00;
    public final /* synthetic */ C003007j A01;
    public final /* synthetic */ AbstractC003107k A02;

    public AnonymousClass07h(C003007j r1, AbstractC003107k r2, View view) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = view;
    }

    public final void onAnimationCancel(Animator animator) {
        this.A02.A6h(this.A00);
    }

    public final void onAnimationEnd(Animator animator) {
        this.A02.A6i(this.A00);
    }

    public final void onAnimationStart(Animator animator) {
        this.A02.A6k(this.A00);
    }
}
