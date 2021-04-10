package defpackage;

import J.N;
import org.chromium.chrome.browser.DevToolsServer;

/* renamed from: zG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5909zG0 implements Runnable {
    public final /* synthetic */ OG0 F;

    public RunnableC5909zG0(OG0 og0) {
        this.F = og0;
    }

    public void run() {
        this.F.f = new DevToolsServer("chrome");
        DevToolsServer devToolsServer = this.F.f;
        N.M0ZKpN7w(devToolsServer, devToolsServer.f10602a, true, true);
    }
}
