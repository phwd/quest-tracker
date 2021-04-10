package defpackage;

import java.util.Objects;

/* renamed from: dk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2231dk0 implements Runnable {
    public final C3084ik0 F;
    public final AbstractC4452qk0 G;
    public final int H;

    public RunnableC2231dk0(C3084ik0 ik0, AbstractC4452qk0 qk0, int i) {
        this.F = ik0;
        this.G = qk0;
        this.H = i;
    }

    public void run() {
        C3084ik0 ik0 = this.F;
        AbstractC4452qk0 qk0 = this.G;
        int i = this.H;
        Objects.requireNonNull(ik0);
        ((WW0) qk0).a(i);
        ik0.a();
    }
}
