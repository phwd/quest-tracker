package X;

import androidx.fragment.app.Fragment;

public class B9 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.FragmentTransition$3";
    public final /* synthetic */ AnonymousClass6N A00;
    public final /* synthetic */ Fragment A01;
    public final /* synthetic */ BC A02;

    public B9(BC bc, Fragment fragment, AnonymousClass6N r3) {
        this.A02 = bc;
        this.A01 = fragment;
        this.A00 = r3;
    }

    public final void run() {
        this.A02.A2Q(this.A01, this.A00);
    }
}
