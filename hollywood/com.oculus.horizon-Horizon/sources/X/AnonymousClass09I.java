package X;

import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.Fragment;

/* renamed from: X.09I  reason: invalid class name */
public class AnonymousClass09I implements Animation.AnimationListener {
    public final /* synthetic */ ViewGroup A00;
    public final /* synthetic */ AnonymousClass05d A01;
    public final /* synthetic */ Fragment A02;
    public final /* synthetic */ AbstractC004709v A03;

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
    }

    public AnonymousClass09I(ViewGroup viewGroup, Fragment fragment, AbstractC004709v r3, AnonymousClass05d r4) {
        this.A00 = viewGroup;
        this.A02 = fragment;
        this.A03 = r3;
        this.A01 = r4;
    }

    public final void onAnimationEnd(Animation animation) {
        this.A00.post(new AnonymousClass09H(this));
    }
}
