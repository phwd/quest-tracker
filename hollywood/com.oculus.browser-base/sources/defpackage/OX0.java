package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: OX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OX0 {
    public void a(BrowserContextHandle browserContextHandle, C3469ky1 ky1, Runnable runnable) {
        String d = ky1.F.d();
        N.Mks53EZS(browserContextHandle, d);
        N.MyQGLOqU(browserContextHandle, d);
        N.MSoF8bn2(browserContextHandle, d);
        runnable.getClass();
        ky1.a(browserContextHandle, new NX0(runnable));
    }

    public void b(BrowserContextHandle browserContextHandle, C3469ky1 ky1) {
        for (C1032Qy qy : ky1.H.values()) {
            ky1.l(browserContextHandle, qy.F, 0);
        }
        for (OB0 ob0 : ky1.I.values()) {
            ky1.l(browserContextHandle, ob0.I, 0);
        }
        Iterator it = ((ArrayList) ky1.d()).iterator();
        while (it.hasNext()) {
            ((C5316vp) it.next()).a(browserContextHandle);
        }
    }
}
