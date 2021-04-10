package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: aV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1669aV0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2361eV0 f9433a;

    public C1669aV0(C2361eV0 ev0) {
        this.f9433a = ev0;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        this.f9433a.q();
        if (((TabImpl) tab).j0.c(C2361eV0.class) != null) {
            ((TabImpl) tab).j0.d(C2361eV0.class);
        }
    }
}
