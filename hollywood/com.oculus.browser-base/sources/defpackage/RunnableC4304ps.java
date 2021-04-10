package defpackage;

import java.util.Objects;

/* renamed from: ps  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4304ps implements Runnable {
    public final C4985ts F;
    public final AbstractC3467ky G;

    public RunnableC4304ps(C4985ts tsVar, AbstractC3467ky kyVar) {
        this.F = tsVar;
        this.G = kyVar;
    }

    public void run() {
        C4985ts tsVar = this.F;
        AbstractC3467ky kyVar = this.G;
        Objects.requireNonNull(tsVar);
        kyVar.a(new RunnableC4645rs(tsVar));
    }
}
