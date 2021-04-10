package defpackage;

import java.util.Objects;

/* renamed from: Su  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1146Su implements Runnable {
    public final C1207Tu F;
    public final boolean G;
    public final boolean H;

    public RunnableC1146Su(C1207Tu tu, boolean z, boolean z2) {
        this.F = tu;
        this.G = z;
        this.H = z2;
    }

    public void run() {
        C1207Tu tu = this.F;
        boolean z = this.G;
        boolean z2 = this.H;
        Objects.requireNonNull(tu.c);
        AbstractC3100ip1.f10165a.a(z2 ? "TrustedWebActivity.ClearDataDialogOnUninstallAccepted" : "TrustedWebActivity.ClearDataDialogOnClearAppDataAccepted", z);
    }
}
