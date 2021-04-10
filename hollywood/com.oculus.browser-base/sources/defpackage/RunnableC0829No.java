package defpackage;

import android.content.Context;
import android.os.Bundle;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: No  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0829No implements Runnable {
    public final /* synthetic */ Context F;
    public final /* synthetic */ boolean G;

    public RunnableC0829No(Context context, boolean z) {
        this.F = context;
        this.G = z;
    }

    public void run() {
        Context context = this.F;
        boolean z = this.G;
        LY0 ly0 = z ? ChildProcessLauncherHelperImpl.b : ChildProcessLauncherHelperImpl.c;
        if (ly0 != null) {
            if (!(ly0.b == null || ly0.d != null)) {
                return;
            }
        }
        Bundle bundle = new Bundle();
        ChildProcessLauncherHelperImpl.c(bundle);
        AbstractC2412eo b = ChildProcessLauncherHelperImpl.b(context, z);
        if (z) {
            ChildProcessLauncherHelperImpl.b = new LY0(context, b, bundle);
        } else {
            ChildProcessLauncherHelperImpl.c = new LY0(context, b, bundle);
        }
    }
}
