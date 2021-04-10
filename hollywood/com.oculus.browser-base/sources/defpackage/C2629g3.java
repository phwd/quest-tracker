package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: g3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2629g3 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2800h3 f9972a;

    public C2629g3(C2800h3 h3Var) {
        this.f9972a = h3Var;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            this.f9972a.c = null;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }
}
