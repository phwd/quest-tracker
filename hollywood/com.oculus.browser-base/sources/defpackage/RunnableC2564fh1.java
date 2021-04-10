package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* renamed from: fh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2564fh1 implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ C5464wh1 G;

    public RunnableC2564fh1(C5464wh1 wh1, int i) {
        this.G = wh1;
        this.F = i;
    }

    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.G.f;
        int i = this.F;
        Objects.requireNonNull(imeAdapterImpl);
        switch (i) {
            case 16908319:
                WebContentsImpl webContentsImpl = imeAdapterImpl.K;
                webContentsImpl.r0();
                N.MNvj1u1S(webContentsImpl.H, webContentsImpl);
                return;
            case 16908320:
                imeAdapterImpl.K.t0();
                return;
            case 16908321:
                imeAdapterImpl.K.s0();
                return;
            case 16908322:
                imeAdapterImpl.K.x0();
                return;
            default:
                return;
        }
    }
}
