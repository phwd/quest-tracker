package defpackage;

import java.util.Objects;
import java.util.TimerTask;

/* renamed from: xH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5573xH1 extends TimerTask {
    public final /* synthetic */ C0684Le0 F;

    public C5573xH1(C0684Le0 le0) {
        this.F = le0;
    }

    public final void run() {
        DB0 db0;
        C0684Le0 le0 = this.F;
        if (!le0.i.isEmpty() && le0.m == null && le0.d && le0.b != 0) {
            ML0 ml0 = le0.c;
            int[] b = GF1.b(le0.i);
            Objects.requireNonNull(ml0);
            SE0.e("Must be called from the main thread.");
            if (!ml0.w()) {
                db0 = ML0.s(17, null);
            } else {
                C5051uD1 ud1 = new C5051uD1(ml0, ml0.g, b);
                ml0.t(ud1);
                db0 = ud1;
            }
            le0.m = db0;
            db0.b(le0.o);
            le0.i.clear();
        }
    }
}
