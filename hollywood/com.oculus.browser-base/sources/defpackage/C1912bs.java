package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: bs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1912bs extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2253ds f9566a;

    public C1912bs(C2253ds dsVar) {
        this.f9566a = dsVar;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        AbstractC1406Xb0 xb0 = this.f9566a.f9813a;
        if (xb0 != null) {
            ((C0820Nj0) xb0).j(5);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        AbstractC1406Xb0 xb0;
        if (tab == ((AbstractC0246Ea1) this.f9566a.h).j() && (xb0 = this.f9566a.f9813a) != null) {
            ((C0820Nj0) xb0).j(6);
        }
    }
}
