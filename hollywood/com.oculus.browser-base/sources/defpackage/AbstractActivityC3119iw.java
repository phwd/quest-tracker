package defpackage;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/* renamed from: iw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractActivityC3119iw extends Activity implements AbstractC4823su1, JO0, AbstractC0534Is0, AbstractC4524r80, P40 {
    public C4865t80 F = new C4865t80(this);
    public final C4865t80 G = new C4865t80(this);
    public final IO0 H = new IO0(this);
    public C4653ru1 I;

    /* renamed from: J  reason: collision with root package name */
    public final C0473Hs0 f10174J = new C0473Hs0(new RunnableC2436ew(this));

    public AbstractActivityC3119iw() {
        if (Q() != null) {
            Q().a(new C2607fw(this));
            Q().a(new C2778gw(this));
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    @Override // defpackage.AbstractC4823su1
    public C4653ru1 L() {
        if (getApplication() != null) {
            if (this.I == null) {
                C2949hw hwVar = (C2949hw) getLastNonConfigurationInstance();
                if (hwVar != null) {
                    this.I = hwVar.f10110a;
                }
                if (this.I == null) {
                    this.I = new C4653ru1();
                }
            }
            return this.I;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // defpackage.AbstractC4524r80
    public AbstractC3499l80 Q() {
        return this.G;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !Q40.a(decorView, keyEvent)) {
            return Q40.b(this, decorView, this, keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !Q40.a(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // defpackage.JO0
    public final HO0 g() {
        return this.H.b;
    }

    @Override // defpackage.P40
    public boolean o(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onBackPressed() {
        this.f10174J.a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ZL0.c(this);
        this.H.a(bundle);
        ZL0.c(this);
    }

    public final Object onRetainNonConfigurationInstance() {
        C2949hw hwVar;
        C4653ru1 ru1 = this.I;
        if (ru1 == null && (hwVar = (C2949hw) getLastNonConfigurationInstance()) != null) {
            ru1 = hwVar.f10110a;
        }
        if (ru1 == null) {
            return null;
        }
        C2949hw hwVar2 = new C2949hw();
        hwVar2.f10110a = ru1;
        return hwVar2;
    }

    public void onSaveInstanceState(Bundle bundle) {
        EnumC3328k80 k80 = EnumC3328k80.CREATED;
        AbstractC3499l80 Q = Q();
        if (Q instanceof C4865t80) {
            ((C4865t80) Q).g(k80);
        }
        this.F.g(k80);
        super.onSaveInstanceState(bundle);
        this.H.b(bundle);
    }
}
