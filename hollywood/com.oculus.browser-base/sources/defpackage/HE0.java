package defpackage;

import org.chromium.content_public.browser.MessagePort;

/* renamed from: HE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HE0 implements Runnable {
    public final /* synthetic */ String F;
    public final /* synthetic */ IE0 G;

    public HE0(IE0 ie0, String str) {
        this.G = ie0;
        this.F = str;
    }

    public void run() {
        MessagePort[] messagePortArr = this.G.d;
        if (messagePortArr != null && !messagePortArr[0].isClosed()) {
            this.G.d[0].d(this.F, null);
        }
    }
}
