package X;

import androidx.fragment.app.Fragment;

/* renamed from: X.9s  reason: invalid class name and case insensitive filesystem */
public class RunnableC00389s implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$3";
    public final /* synthetic */ AnonymousClass5d A00;
    public final /* synthetic */ Fragment A01;
    public final /* synthetic */ AbstractC00409v A02;

    public RunnableC00389s(AbstractC00409v r1, Fragment fragment, AnonymousClass5d r3) {
        this.A02 = r1;
        this.A01 = fragment;
        this.A00 = r3;
    }

    public final void run() {
        this.A02.A3e(this.A01, this.A00);
    }
}
