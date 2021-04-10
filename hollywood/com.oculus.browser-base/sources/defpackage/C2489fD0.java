package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: fD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2489fD0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f9905a;
    public final /* synthetic */ C2831hD0 b;

    public C2489fD0(C2831hD0 hd0, Activity activity) {
        this.b = hd0;
        this.f9905a = activity;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void U(Tab tab) {
        this.b.f(this.f9905a, 7);
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.b.f(this.f9905a, 5);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void m(Tab tab, boolean z) {
        this.b.f(this.f9905a, 2);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.b.f(this.f9905a, 3);
    }
}
