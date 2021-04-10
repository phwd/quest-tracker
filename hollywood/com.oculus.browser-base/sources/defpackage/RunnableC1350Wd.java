package defpackage;

import java.util.Objects;

/* renamed from: Wd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1350Wd implements Runnable {
    public final C1690ae F;
    public final C1533Zd G;
    public final AbstractC1460Xy0 H;

    public RunnableC1350Wd(C1690ae aeVar, C1533Zd zd, AbstractC1460Xy0 xy0) {
        this.F = aeVar;
        this.G = zd;
        this.H = xy0;
    }

    public void run() {
        C1690ae aeVar = this.F;
        C1533Zd zd = this.G;
        AbstractC1460Xy0 xy0 = this.H;
        Objects.requireNonNull(aeVar);
        xy0.i(C1533Zd.a(zd));
        xy0.a(aeVar);
    }
}
