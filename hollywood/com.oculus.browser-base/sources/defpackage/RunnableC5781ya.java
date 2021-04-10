package defpackage;

import java.util.Objects;
import java.util.concurrent.Executor;

/* renamed from: ya  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5781ya implements Runnable {
    public final C0122Ca F;
    public final String G;

    public RunnableC5781ya(C0122Ca ca, String str) {
        this.F = ca;
        this.G = str;
    }

    public void run() {
        C0122Ca ca = this.F;
        String str = this.G;
        Objects.requireNonNull(ca);
        if (C0122Ca.F == null) {
            C0122Ca.F = Boolean.FALSE;
            C5951za zaVar = new C5951za(ca, str);
            Executor executor = AbstractC2032cb.f9616a;
            zaVar.f();
            ((ExecutorC1463Ya) executor).execute(zaVar.e);
        }
    }
}
