package X;

import androidx.fragment.app.Fragment;

/* renamed from: X.09B  reason: invalid class name */
public class AnonymousClass09B implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.Fragment$3";
    public final /* synthetic */ Fragment A00;

    public AnonymousClass09B(Fragment fragment) {
        this.A00 = fragment;
    }

    public final void run() {
        this.A00.callStartTransitionListener();
    }
}
