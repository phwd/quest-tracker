package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: X2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class X2 implements W2 {

    /* renamed from: a  reason: collision with root package name */
    public final Y2 f9187a;

    public X2(Y2 y2) {
        this.f9187a = y2;
    }

    @Override // defpackage.W2
    public void a(Tab tab, boolean z) {
        Y2 y2 = this.f9187a;
        Tab tab2 = y2.c;
        if (tab2 != null) {
            tab2.I(y2);
        }
        y2.c = tab;
        if (tab != null) {
            tab.A(y2);
        }
        y2.V(tab, z);
    }
}
