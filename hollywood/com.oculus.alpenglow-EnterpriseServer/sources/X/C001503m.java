package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: X.03m  reason: invalid class name and case insensitive filesystem */
public class C001503m extends AnimatorListenerAdapter {
    public final /* synthetic */ ActionBarOverlayLayout A00;

    public C001503m(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.A00 = actionBarOverlayLayout;
    }

    public final void onAnimationCancel(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.A00;
        actionBarOverlayLayout.A00 = null;
        actionBarOverlayLayout.A01 = false;
    }

    public final void onAnimationEnd(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.A00;
        actionBarOverlayLayout.A00 = null;
        actionBarOverlayLayout.A01 = false;
    }
}
