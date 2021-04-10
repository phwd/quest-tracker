package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

/* renamed from: mS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3721mS extends AbstractC5255vS implements AbstractC4823su1, AbstractC0534Is0 {
    public final Activity F;
    public final Context G;
    public final Handler H;
    public final KS I = new KS();

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC3892nS f10420J;

    public C3721mS(AbstractActivityC3892nS nSVar) {
        this.f10420J = nSVar;
        Handler handler = new Handler();
        this.F = nSVar;
        TE0.b(nSVar, "context == null");
        this.G = nSVar;
        TE0.b(handler, "handler == null");
        this.H = handler;
    }

    @Override // defpackage.AbstractC4823su1
    public C4653ru1 L() {
        return this.f10420J.L();
    }

    @Override // defpackage.AbstractC4524r80
    public AbstractC3499l80 Q() {
        return this.f10420J.L;
    }

    @Override // defpackage.AbstractC5255vS
    public View a(int i) {
        return this.f10420J.findViewById(i);
    }

    @Override // defpackage.AbstractC5255vS
    public boolean b() {
        Window window = this.f10420J.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    public C0473Hs0 c() {
        return this.f10420J.f10174J;
    }

    public void d(AbstractComponentCallbacksC3550lS lSVar, Intent intent, int i, Bundle bundle) {
        AbstractActivityC3892nS nSVar = this.f10420J;
        nSVar.Q = true;
        if (i == -1) {
            try {
                Object obj = K2.f8337a;
                nSVar.startActivityForResult(intent, -1, bundle);
            } catch (Throwable th) {
                nSVar.Q = false;
                throw th;
            }
        } else {
            AbstractActivityC3892nS.X(i);
            int W = ((nSVar.W(lSVar) + 1) << 16) + (i & 65535);
            Object obj2 = K2.f8337a;
            nSVar.startActivityForResult(intent, W, bundle);
        }
        nSVar.Q = false;
    }

    public void e() {
        ((I7) this.f10420J).b0().f();
    }
}
