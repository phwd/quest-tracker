package X;

import android.os.Looper;
import androidx.fragment.app.Fragment;

public class AV implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.Fragment$1";
    public final /* synthetic */ Fragment A00;

    public AV(Fragment fragment) {
        this.A00 = fragment;
    }

    public final void run() {
        Fragment fragment = this.A00;
        Au au = fragment.A0H;
        if (au == null || au.A05 == null) {
            Fragment.A00(fragment);
        } else if (Looper.myLooper() != fragment.A0H.A05.A02.getLooper()) {
            fragment.A0H.A05.A02.postAtFrontOfQueue(new AW(fragment));
        }
    }
}
