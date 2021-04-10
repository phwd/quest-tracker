package defpackage;

import J.N;
import org.chromium.content.browser.TtsPlatformImpl;

/* renamed from: to1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4975to1 implements Runnable {
    public final TtsPlatformImpl F;
    public final String G;

    public RunnableC4975to1(TtsPlatformImpl ttsPlatformImpl, String str) {
        this.F = ttsPlatformImpl;
        this.G = str;
    }

    public void run() {
        TtsPlatformImpl ttsPlatformImpl = this.F;
        String str = this.G;
        long j = ttsPlatformImpl.f10920a;
        if (j != 0) {
            N.M1Kw17GB(j, Integer.parseInt(str));
        }
    }
}
