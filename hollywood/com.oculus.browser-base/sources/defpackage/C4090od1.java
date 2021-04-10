package defpackage;

import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;

/* renamed from: od1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4090od1 extends P00 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4261pd1 f10564a;

    public C4090od1(C4261pd1 pd1) {
        this.f10564a = pd1;
    }

    @Override // defpackage.P00
    public void a() {
        TabSwitcherModeTTPhone tabSwitcherModeTTPhone = this.f10564a.i;
        if (tabSwitcherModeTTPhone != null) {
            tabSwitcherModeTTPhone.e(false);
        }
    }

    @Override // defpackage.P00
    public void b() {
        TabSwitcherModeTTPhone tabSwitcherModeTTPhone = this.f10564a.i;
        if (tabSwitcherModeTTPhone != null) {
            tabSwitcherModeTTPhone.e(true);
        }
    }
}
