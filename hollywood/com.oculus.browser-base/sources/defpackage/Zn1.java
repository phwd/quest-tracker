package defpackage;

import J.N;
import android.os.Bundle;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;

/* renamed from: Zn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Zn1 extends AbstractC2073co1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC2243do1 f9373a;

    public Zn1(C2414eo1 eo1, AbstractC2243do1 do1) {
        this.f9373a = do1;
    }

    @Override // defpackage.AbstractC2073co1
    public void a(C4649rt0 rt0, C3268jo1 jo1) {
        Bundle a2 = jo1.a("checkAndroidLocationPermission", Bundle.EMPTY, new Yn1(this, jo1));
        if (a2 == null || !a2.getBoolean("success")) {
            this.f9373a.a(jo1.b, false);
        }
    }

    @Override // defpackage.AbstractC2073co1
    public void b() {
        C0611Ka0 ka0 = (C0611Ka0) this.f9373a;
        if (!ka0.f8373a) {
            ka0.f8373a = true;
            C2585fo1 fo1 = ka0.d.f8425a;
            C4649rt0 rt0 = ka0.b;
            C2756go1 go1 = fo1.f9955a;
            go1.f10023a.edit().remove(go1.d(5, rt0)).apply();
            long j = InstalledWebappBridge.f10621a;
            if (j != 0) {
                N.MPWzS9sk(j, 5);
            }
            InstalledWebappBridge.a(ka0.c, false);
        }
    }
}
