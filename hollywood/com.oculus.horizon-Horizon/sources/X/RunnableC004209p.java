package X;

import androidx.fragment.app.Fragment;

/* renamed from: X.09p  reason: invalid class name and case insensitive filesystem */
public class RunnableC004209p implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$1";
    public final /* synthetic */ AnonymousClass05d A00;
    public final /* synthetic */ Fragment A01;
    public final /* synthetic */ AbstractC004709v A02;

    public RunnableC004209p(AbstractC004709v r1, Fragment fragment, AnonymousClass05d r3) {
        this.A02 = r1;
        this.A01 = fragment;
        this.A00 = r3;
    }

    public final void run() {
        this.A02.A5r(this.A01, this.A00);
    }
}
