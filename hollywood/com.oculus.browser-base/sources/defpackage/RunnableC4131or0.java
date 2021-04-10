package defpackage;

/* renamed from: or0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4131or0 implements Runnable {
    public final C4302pr0 F;

    public RunnableC4131or0(C4302pr0 pr0) {
        this.F = pr0;
    }

    public void run() {
        boolean z;
        C4302pr0 pr0 = this.F;
        if (pr0.h == 1 && (z = pr0.d) != pr0.c) {
            pr0.c = z;
            pr0.g.onResult(Boolean.valueOf(z));
        }
    }
}
