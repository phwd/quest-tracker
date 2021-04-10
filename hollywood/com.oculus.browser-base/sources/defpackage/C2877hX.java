package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: hX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2877hX extends WK {

    /* renamed from: a  reason: collision with root package name */
    public WindowAndroid f10074a;
    public final /* synthetic */ C3218jX b;

    public C2877hX(C3218jX jXVar, WindowAndroid windowAndroid) {
        this.b = jXVar;
        this.f10074a = windowAndroid;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        this.f10074a.destroy();
        this.f10074a = null;
        tab.I(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.b.a(null);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        this.f10074a.destroy();
        this.f10074a = null;
        tab.I(this);
    }
}
