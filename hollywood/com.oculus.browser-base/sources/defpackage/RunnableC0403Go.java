package defpackage;

import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* renamed from: Go  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0403Go implements Runnable {
    public void run() {
        ComponentCallbacks2C5632xh xhVar = ChildProcessLauncherHelperImpl.i;
        if (xhVar != null && !xhVar.F.isEmpty()) {
            LauncherThread.c.postDelayed(xhVar.I, 10000);
        }
    }
}
