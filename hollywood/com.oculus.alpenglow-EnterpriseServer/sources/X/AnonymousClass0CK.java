package X;

import android.os.Looper;

/* renamed from: X.0CK  reason: invalid class name */
public class AnonymousClass0CK implements Runnable {
    public static final String __redex_internal_original_name = "androidx.fragment.app.Fragment$1";
    public final /* synthetic */ AnonymousClass0MN A00;

    public AnonymousClass0CK(AnonymousClass0MN r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass0MN r2 = this.A00;
        AnonymousClass0Cj r0 = r2.A0H;
        if (r0 == null || r0.A05 == null) {
            AnonymousClass0MN.A00(r2);
        } else if (Looper.myLooper() != r2.A0H.A05.A02.getLooper()) {
            r2.A0H.A05.A02.postAtFrontOfQueue(new AnonymousClass0CL(r2));
        }
    }
}
