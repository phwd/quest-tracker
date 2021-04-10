package defpackage;

import android.content.Context;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* renamed from: Eo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0281Eo {
    public static void a(Context context, boolean z) {
        boolean z2 = ChildProcessLauncherHelperImpl.f10910a;
        LauncherThread.c.post(new RunnableC0829No(context, z));
    }
}
