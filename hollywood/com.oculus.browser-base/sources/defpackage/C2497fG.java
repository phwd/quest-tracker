package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: fG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2497fG extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2839hG f9909a;

    public C2497fG(C2839hG hGVar) {
        this.f9909a = hGVar;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void C(Tab tab, boolean z) {
        this.f9909a.G.c();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        this.f9909a.G.c();
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        C2326eG eGVar = this.f9909a.G;
        if (windowAndroid == null) {
            W10 w10 = eGVar.H;
            if (w10 != null) {
                w10.H.c(eGVar);
                eGVar.H = null;
                eGVar.F = null;
                return;
            }
            return;
        }
        eGVar.b();
    }
}
