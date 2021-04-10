package defpackage;

import java.util.List;

/* renamed from: Rq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1077Rq implements Runnable {
    public final C1321Vq F;

    public RunnableC1077Rq(C1321Vq vq) {
        this.F = vq;
    }

    public void run() {
        C1321Vq vq = this.F;
        vq.h = true;
        List<Runnable> list = vq.d;
        if (list != null) {
            for (Runnable runnable : list) {
                runnable.run();
            }
            vq.d = null;
        }
    }
}
