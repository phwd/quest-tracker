package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* renamed from: X.09J  reason: invalid class name */
public class AnonymousClass09J extends AnimatorListenerAdapter {
    public final /* synthetic */ View A00;
    public final /* synthetic */ ViewGroup A01;
    public final /* synthetic */ AnonymousClass05d A02;
    public final /* synthetic */ Fragment A03;
    public final /* synthetic */ AbstractC004709v A04;

    public AnonymousClass09J(ViewGroup viewGroup, View view, Fragment fragment, AbstractC004709v r4, AnonymousClass05d r5) {
        this.A01 = viewGroup;
        this.A00 = view;
        this.A03 = fragment;
        this.A04 = r4;
        this.A02 = r5;
    }

    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.A01;
        View view = this.A00;
        viewGroup.endViewTransition(view);
        Fragment fragment = this.A03;
        Animator animator2 = fragment.getAnimator();
        fragment.setAnimator(null);
        if (animator2 != null && viewGroup.indexOfChild(view) < 0) {
            this.A04.A5r(fragment, this.A02);
        }
    }
}
