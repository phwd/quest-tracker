package defpackage;

import J.N;
import org.chromium.content.browser.TtsPlatformImpl;

/* renamed from: uo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5145uo1 implements Runnable {
    public final TtsPlatformImpl F;
    public final String G;

    public RunnableC5145uo1(TtsPlatformImpl ttsPlatformImpl, String str) {
        this.F = ttsPlatformImpl;
        this.G = str;
    }

    public void run() {
        TtsPlatformImpl ttsPlatformImpl = this.F;
        String str = this.G;
        long j = ttsPlatformImpl.f10920a;
        if (j != 0) {
            N.M2$X0reE(j, Integer.parseInt(str));
        }
    }
}
