package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: J71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J71 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5039u91 f8271a;

    public J71(C5039u91 u91) {
        this.f8271a = u91;
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        int i = C5383wB.q(tab).R;
        if (((I71) ((AbstractC0246Ea1) this.f8271a.f11393a).c.d()).U(i).size() == 1) {
            this.f8271a.a(i);
        }
    }
}
