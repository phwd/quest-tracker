package X;

import androidx.fragment.app.Fragment;

/* renamed from: X.09H  reason: invalid class name */
public class AnonymousClass09H implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentAnim$2$1";
    public final /* synthetic */ AnonymousClass09I A00;

    public AnonymousClass09H(AnonymousClass09I r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass09I r3 = this.A00;
        Fragment fragment = r3.A02;
        if (fragment.getAnimatingAway() != null) {
            fragment.setAnimatingAway(null);
            r3.A03.A5r(fragment, r3.A01);
        }
    }
}
