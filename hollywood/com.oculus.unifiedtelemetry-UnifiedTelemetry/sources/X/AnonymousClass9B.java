package X;

import android.os.Looper;
import androidx.fragment.app.Fragment;

/* renamed from: X.9B  reason: invalid class name */
public class AnonymousClass9B implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.Fragment$1";
    public final /* synthetic */ Fragment A00;

    public AnonymousClass9B(Fragment fragment) {
        this.A00 = fragment;
    }

    public final void run() {
        Fragment fragment = this.A00;
        AbstractC00279a r0 = fragment.A0H;
        if (r0 == null || r0.A05 == null) {
            Fragment.A00(fragment);
        } else if (Looper.myLooper() != fragment.A0H.A05.A02.getLooper()) {
            fragment.A0H.A05.A02.postAtFrontOfQueue(new AnonymousClass9C(fragment));
        }
    }
}
