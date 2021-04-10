package defpackage;

/* renamed from: C11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C11 implements Runnable {
    public final /* synthetic */ D11 F;

    public C11(D11 d11, C5695y11 y11) {
        this.F = d11;
    }

    public void run() {
        D11 d11 = this.F;
        d11.e0 = false;
        C4316pw pwVar = d11.l0;
        UH0 uh0 = d11.Z;
        RH0 rh0 = J70.u;
        C5677xw.f(pwVar, uh0, rh0, uh0.e(rh0), 1.0f, 500).start();
        D11 d112 = this.F;
        C4316pw pwVar2 = d112.l0;
        UH0 uh02 = d112.Z;
        RH0 rh02 = J70.C;
        C5677xw.f(pwVar2, uh02, rh02, uh02.e(rh02), 0.0f, 500).start();
        this.F.Z.j(J70.F, false);
    }
}
