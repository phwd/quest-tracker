package defpackage;

import android.content.Context;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Oo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0890Oo implements Runnable {
    public final /* synthetic */ Context F;

    public RunnableC0890Oo(Context context) {
        this.F = context;
    }

    public void run() {
        AbstractC2412eo b = ChildProcessLauncherHelperImpl.b(this.F, true);
        Object obj = C5653xo.f11634a;
        if (AbstractC4952th.c()) {
            ChildProcessLauncherHelperImpl.i = new ComponentCallbacks2C5632xh(-1, ChildProcessLauncherHelperImpl.e, this.F);
            return;
        }
        ChildProcessLauncherHelperImpl.i = new ComponentCallbacks2C5632xh(b.e(), ChildProcessLauncherHelperImpl.e, this.F);
    }
}
