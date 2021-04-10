package defpackage;

import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: yV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5772yV0 implements AbstractC2534fW0, AbstractC2193dW0, VG0, W1 {
    public final SigninManager F;
    public final AccountManagerFacade G;
    public boolean H;
    public final /* synthetic */ C2690gP I;

    public C5772yV0(C2690gP gPVar, SigninManager signinManager, AbstractC5602xV0 xv0) {
        this.I = gPVar;
        this.F = signinManager;
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        this.G = instance;
        signinManager.q(this);
        signinManager.m(this);
        gPVar.N.U(this);
        instance.a(this);
    }

    @Override // defpackage.VG0
    public void D(String str) {
        this.I.w();
    }

    @Override // defpackage.AbstractC2534fW0
    public void b() {
        C2690gP gPVar = this.I;
        gPVar.I = false;
        gPVar.x();
        this.I.w();
    }

    @Override // defpackage.AbstractC2193dW0
    public void c() {
        this.I.I = this.F.i();
        this.I.x();
        this.I.w();
    }

    @Override // defpackage.W1
    public void e() {
        this.I.f9995J = this.G.d();
        this.I.w();
    }

    @Override // defpackage.AbstractC2534fW0
    public void o() {
        this.I.I = this.F.i();
        this.I.x();
        this.I.w();
    }
}
