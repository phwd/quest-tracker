package X;

import android.os.Looper;
import androidx.fragment.app.Fragment;

/* renamed from: X.09C  reason: invalid class name */
public class AnonymousClass09C implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.Fragment$1";
    public final /* synthetic */ Fragment A00;

    public AnonymousClass09C(Fragment fragment) {
        this.A00 = fragment;
    }

    public final void run() {
        Fragment fragment = this.A00;
        AnonymousClass09b r0 = fragment.A0H;
        if (r0 == null || r0.A05 == null) {
            Fragment.A00(fragment);
        } else if (Looper.myLooper() != fragment.A0H.A05.A02.getLooper()) {
            fragment.A0H.A05.A02.postAtFrontOfQueue(new AnonymousClass09D(fragment));
        }
    }
}
