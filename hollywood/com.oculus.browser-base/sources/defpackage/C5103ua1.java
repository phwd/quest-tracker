package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ua1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5103ua1 implements AbstractC2982i61 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5613xa1 f11419a;

    public C5103ua1(C5613xa1 xa1) {
        this.f11419a = xa1;
    }

    @Override // defpackage.AbstractC2982i61
    public Tab a(int i) {
        AbstractC0246Ea1 ea1;
        C5613xa1 xa1 = this.f11419a;
        if (xa1.Q) {
            ea1 = xa1.N;
        } else {
            ea1 = xa1.M;
        }
        return ea1.o(i);
    }
}
