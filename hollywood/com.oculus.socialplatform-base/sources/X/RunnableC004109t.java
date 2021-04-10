package X;

import androidx.fragment.app.Fragment;

/* renamed from: X.09t  reason: invalid class name and case insensitive filesystem */
public class RunnableC004109t implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$3";
    public final /* synthetic */ AnonymousClass05j A00;
    public final /* synthetic */ Fragment A01;
    public final /* synthetic */ AbstractC004309w A02;

    public RunnableC004109t(AbstractC004309w r1, Fragment fragment, AnonymousClass05j r3) {
        this.A02 = r1;
        this.A01 = fragment;
        this.A00 = r3;
    }

    public final void run() {
        this.A02.A6s(this.A01, this.A00);
    }
}
