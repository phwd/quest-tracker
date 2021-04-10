package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: I01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I01 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ M01 f8194a;

    public I01(M01 m01) {
        this.f8194a = m01;
    }

    @Override // defpackage.AbstractC5783ya1
    public void E() {
        if (this.f8194a.I.h(N01.g)) {
            M01 m01 = this.f8194a;
            boolean z = true;
            if (m01.T == 1) {
                if (((AbstractC0246Ea1) m01.H).l(false).getCount() <= 0 || this.f8194a.P) {
                    z = false;
                }
                m01.o(z);
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        M01 m01 = this.f8194a;
        if (m01.T == 1) {
            m01.o(true);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        M01 m01 = this.f8194a;
        if (m01.T == 1 && ((AbstractC0246Ea1) m01.H).l(false).getCount() <= 1) {
            this.f8194a.o(false);
        }
    }
}
