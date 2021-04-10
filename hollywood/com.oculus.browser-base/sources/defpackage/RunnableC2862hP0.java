package defpackage;

import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: hP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2862hP0 implements Runnable {
    public final /* synthetic */ String F;
    public final /* synthetic */ C3033iP0 G;

    public RunnableC2862hP0(C3033iP0 ip0, String str) {
        this.G = ip0;
        this.F = str;
    }

    public void run() {
        C3203jP0 jp0 = this.G.f10135a;
        if (jp0 != null) {
            String str = this.F;
            if (jp0.c && str != null) {
                Oj1 oj1 = (Oj1) jp0.f10203a;
                Um1.a(Z00.c(oj1.H, oj1.P.get() != null && ((Tab) oj1.P.get()).a())).notifyEvent("screenshot_taken_chrome_in_foreground");
                PostTask.b(Zo1.f9374a, new Hj1(oj1), 0);
            }
        }
    }
}
