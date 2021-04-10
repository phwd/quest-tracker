package X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: X.1Em  reason: invalid class name and case insensitive filesystem */
public class C05991Em extends AnimatorListenerAdapter {
    public final /* synthetic */ ActionBarOverlayLayout A00;

    public C05991Em(ActionBarOverlayLayout actionBarOverlayLayout) {
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
