package defpackage;

import org.chromium.base.Callback;

/* renamed from: ns0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3963ns0 implements Runnable {
    public final Callback F;

    public RunnableC3963ns0(Callback callback) {
        this.F = callback;
    }

    public void run() {
        this.F.onResult(null);
    }
}
