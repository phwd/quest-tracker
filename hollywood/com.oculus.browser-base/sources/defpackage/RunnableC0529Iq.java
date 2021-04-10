package defpackage;

import java.util.Objects;

/* renamed from: Iq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0529Iq implements Runnable {
    public final int F;

    public RunnableC0529Iq(int i) {
        this.F = i;
    }

    public void run() {
        int i = this.F;
        C5116uf f = C5116uf.f();
        Objects.requireNonNull(f);
        if (i >= 0) {
            f.c("Servicification.Startup3", i);
        }
    }
}
