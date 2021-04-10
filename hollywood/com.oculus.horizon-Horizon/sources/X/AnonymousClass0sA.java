package X;

import android.view.View;
import androidx.fragment.app.Fragment;

/* renamed from: X.0sA  reason: invalid class name */
public class AnonymousClass0sA implements AnonymousClass05c {
    public final /* synthetic */ Fragment A00;

    public AnonymousClass0sA(Fragment fragment) {
        this.A00 = fragment;
    }

    @Override // X.AnonymousClass05c
    public final void A5m() {
        Fragment fragment = this.A00;
        View animatingAway = fragment.getAnimatingAway();
        if (animatingAway != null) {
            fragment.setAnimatingAway(null);
            animatingAway.clearAnimation();
        }
        fragment.setAnimator(null);
    }
}
