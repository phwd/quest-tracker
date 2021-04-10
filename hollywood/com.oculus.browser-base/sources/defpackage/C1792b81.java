package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: b81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1792b81 extends RK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2475f81 f9516a;

    public C1792b81(C2475f81 f81) {
        this.f9516a = f81;
    }

    @Override // defpackage.RK
    public void d(Tab tab, int i) {
        C2475f81 f81 = this.f9516a;
        if (f81.x && tab == ((AbstractC0246Ea1) f81.e).j()) {
            this.f9516a.g(tab.getId());
        }
    }
}
