package defpackage;

import java.util.Objects;

/* renamed from: rs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4646rs0 implements Runnable {
    public final C5836ys0 F;
    public final int G;

    public RunnableC4646rs0(C5836ys0 ys0, int i) {
        this.F = ys0;
        this.G = i;
    }

    public void run() {
        AbstractC5496ws0 ws0;
        C5836ys0 ys0 = this.F;
        int i = this.G;
        if (ys0.w1 == i && ys0.x1 != i && (ws0 = ys0.r1) != null) {
            C2379ed edVar = (C2379ed) ws0;
            C3909na0 na0 = (C3909na0) edVar.G;
            Objects.requireNonNull(na0);
            if (C3493l60.F.f(na0.F.getContext(), na0.O) || na0.F.getContext().getResources().getConfiguration().keyboard == 2) {
                edVar.g0.i = i;
            }
            ys0.x1 = i;
        }
    }
}
