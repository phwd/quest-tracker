package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ft0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2599ft0 implements AbstractC0578Jk {

    /* renamed from: a  reason: collision with root package name */
    public final C2770gt0 f9963a;
    public final AbstractC0639Kk b;

    public C2599ft0(C2770gt0 gt0, AbstractC0639Kk kk) {
        this.f9963a = gt0;
        this.b = kk;
    }

    @Override // defpackage.AbstractC0578Jk
    public void a(boolean z) {
        C2770gt0 gt0 = this.f9963a;
        AbstractC0639Kk kk = this.b;
        if (kk == gt0.c) {
            C0517Ik r = kk.r((Tab) gt0.f.get());
            if (r.f8247a) {
                gt0.a(kk, r);
                return;
            }
            gt0.e.n();
            gt0.c = null;
            gt0.b();
        } else if (z) {
            gt0.b();
        }
    }
}
