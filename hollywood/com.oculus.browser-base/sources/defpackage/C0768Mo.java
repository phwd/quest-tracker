package defpackage;

import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* renamed from: Mo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0768Mo extends AbstractC0159Co {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChildProcessLauncherHelperImpl f8502a;

    public C0768Mo(ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl) {
        this.f8502a = childProcessLauncherHelperImpl;
    }

    @Override // defpackage.AbstractC0159Co
    public C5653xo a(AbstractC2412eo eoVar, AbstractC5483wo woVar) {
        LY0 ly0;
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = this.f8502a;
        if (!childProcessLauncherHelperImpl.n) {
            return null;
        }
        if (childProcessLauncherHelperImpl.m) {
            ly0 = ChildProcessLauncherHelperImpl.b;
        } else {
            ly0 = ChildProcessLauncherHelperImpl.c;
        }
        if (ly0 == null) {
            return null;
        }
        C5653xo xoVar = ly0.b;
        if ((xoVar == null || ly0.d != null) || ly0.f8423a != eoVar || ly0.d != null) {
            return null;
        }
        ly0.d = woVar;
        if (ly0.c) {
            LauncherThread.c.post(new KY0(ly0, woVar));
            ly0.b = null;
            ly0.c = false;
        }
        return xoVar;
    }
}
