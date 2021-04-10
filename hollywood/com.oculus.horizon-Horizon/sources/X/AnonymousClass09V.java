package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* renamed from: X.09V  reason: invalid class name */
public class AnonymousClass09V extends AnimatorListenerAdapter {
    public final /* synthetic */ View A00;
    public final /* synthetic */ ViewGroup A01;
    public final /* synthetic */ Fragment A02;
    public final /* synthetic */ AbstractC003209a A03;

    public AnonymousClass09V(AbstractC003209a r1, ViewGroup viewGroup, View view, Fragment fragment) {
        this.A03 = r1;
        this.A01 = viewGroup;
        this.A00 = view;
        this.A02 = fragment;
    }

    public final void onAnimationEnd(Animator animator) {
        this.A01.endViewTransition(this.A00);
        animator.removeListener(this);
        Fragment fragment = this.A02;
        View view = fragment.mView;
        if (view != null && fragment.mHidden) {
            view.setVisibility(8);
        }
    }
}
