package defpackage;

import android.os.RemoteException;
import java.util.List;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Io  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0525Io implements Runnable {
    public void run() {
        C5653xo xoVar;
        C1317Vo vo = ChildProcessLauncherHelperImpl.e;
        if (vo.I.isEmpty()) {
            xoVar = null;
        } else {
            List list = vo.I;
            xoVar = ((C1134So) list.get(list.size() - 1)).f8918a;
        }
        if (xoVar != null) {
            AbstractC5267vY vYVar = xoVar.n;
            xoVar.n();
            if (vYVar != null) {
                try {
                    vYVar.q0();
                } catch (RemoteException unused) {
                }
            }
            synchronized (C5653xo.f11634a) {
                xoVar.G = true;
            }
            xoVar.i();
        }
    }
}
