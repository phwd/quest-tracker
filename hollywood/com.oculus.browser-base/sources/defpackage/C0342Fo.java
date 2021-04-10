package defpackage;

import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* renamed from: Fo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0342Fo implements AbstractC1678aa {
    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        boolean z = ChildProcessLauncherHelperImpl.f10910a;
        if (i == 0) {
            return;
        }
        if (i == 1 || i == 2) {
            if (!ChildProcessLauncherHelperImpl.j) {
                ChildProcessLauncherHelperImpl.j = true;
                LauncherThread.c.post(new RunnableC0464Ho());
            }
        } else if (ChildProcessLauncherHelperImpl.j) {
            ChildProcessLauncherHelperImpl.j = false;
            LauncherThread.c.post(new RunnableC0403Go());
        }
    }
}
