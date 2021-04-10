package defpackage;

import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Lo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0708Lo implements Runnable {
    public final int F;
    public final C5653xo G;

    public RunnableC0708Lo(int i, C5653xo xoVar) {
        this.F = i;
        this.G = xoVar;
    }

    public void run() {
        int i = this.F;
        C5653xo xoVar = this.G;
        boolean z = ChildProcessLauncherHelperImpl.f10910a;
        if (i == 0) {
            return;
        }
        if (i == 1) {
            xoVar.l();
        } else if (i == 2 && xoVar.g()) {
            int i2 = xoVar.z - 1;
            xoVar.z = i2;
            if (i2 == 0) {
                ((ServiceConnectionC2244dp) xoVar.w).c();
                xoVar.o();
            }
        }
    }
}
