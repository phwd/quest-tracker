package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: we  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5453we extends Y2 {
    public final /* synthetic */ M2 d;
    public final /* synthetic */ C5623xe e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5453we(C5623xe xeVar, C1595a3 a3Var, M2 m2) {
        super(a3Var);
        this.e = xeVar;
        this.d = m2;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void C(Tab tab, boolean z) {
        boolean z2 = this.d.k == 5;
        if (!z && !z2) {
            C3837n7.a(this.e.F);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !navigationHandle.b) {
            C3837n7.a(this.e.F);
        }
    }
}
