package defpackage;

import J.N;
import org.chromium.base.JavaHandlerThread;

/* renamed from: z40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5873z40 implements Runnable {
    public final /* synthetic */ long F;
    public final /* synthetic */ JavaHandlerThread G;

    public RunnableC5873z40(JavaHandlerThread javaHandlerThread, long j) {
        this.G = javaHandlerThread;
        this.F = j;
    }

    public void run() {
        this.G.f10589a.quit();
        N.MYwg$x8E(this.F);
    }
}
