package defpackage;

import org.chromium.base.Callback;

/* renamed from: Ol  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0884Ol implements Runnable {
    public final Callback F;
    public final Object G;

    public RunnableC0884Ol(Callback callback, Object obj) {
        this.F = callback;
        this.G = obj;
    }

    public void run() {
        this.F.onResult(this.G);
    }
}
