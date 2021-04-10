package defpackage;

import org.chromium.base.Callback;

/* renamed from: Qq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1017Qq0 implements Runnable {
    public final C1078Rq0 F;
    public final Object G;
    public final Callback H;

    public RunnableC1017Qq0(C1078Rq0 rq0, Object obj, Callback callback) {
        this.F = rq0;
        this.G = obj;
        this.H = callback;
    }

    public void run() {
        C1078Rq0 rq0 = this.F;
        Object obj = this.G;
        Callback callback = this.H;
        if (rq0.H == obj && rq0.I.F.contains(callback)) {
            callback.onResult(rq0.H);
        }
    }
}
