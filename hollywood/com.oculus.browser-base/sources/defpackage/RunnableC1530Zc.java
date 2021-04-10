package defpackage;

import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Zc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1530Zc implements Runnable {
    public final C2379ed F;
    public final Profile G;
    public final String H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final String f9353J;
    public final int K;
    public final boolean L;
    public final boolean M;

    public RunnableC1530Zc(C2379ed edVar, Profile profile, String str, int i, String str2, int i2, boolean z, boolean z2) {
        this.F = edVar;
        this.G = profile;
        this.H = str;
        this.I = i;
        this.f9353J = str2;
        this.K = i2;
        this.L = z;
        this.M = z2;
    }

    public void run() {
        C2379ed edVar = this.F;
        Profile profile = this.G;
        String str = this.H;
        int i = this.I;
        String str2 = this.f9353J;
        int i2 = this.K;
        boolean z = this.L;
        boolean z2 = this.M;
        edVar.X = null;
        edVar.P.a(profile, str, i, str2, i2, z, null, z2);
    }
}
