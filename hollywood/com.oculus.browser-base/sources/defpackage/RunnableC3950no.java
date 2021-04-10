package defpackage;

import android.os.RemoteException;

/* renamed from: no  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3950no implements Runnable {
    public final C5653xo F;
    public final int G;

    public RunnableC3950no(C5653xo xoVar, int i) {
        this.F = xoVar;
        this.G = i;
    }

    public void run() {
        C5653xo xoVar = this.F;
        int i = this.G;
        AbstractC5267vY vYVar = xoVar.n;
        if (vYVar != null) {
            try {
                vYVar.p0(i);
            } catch (RemoteException unused) {
            }
        }
    }
}
