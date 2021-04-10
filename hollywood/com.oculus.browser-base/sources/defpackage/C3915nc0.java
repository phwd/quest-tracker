package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: nc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3915nc0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View$OnLayoutChangeListenerC4598rc0 f10502a;

    public C3915nc0(View$OnLayoutChangeListenerC4598rc0 rc0) {
        this.f10502a = rc0;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.f10502a.f0();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        C0007Ac0 ac0 = this.f10502a.f11207J;
        Objects.requireNonNull(ac0);
        WebContents l = tab.l();
        if (l != null) {
            ac0.a(l).a();
            ac0.f7681a.remove(l);
        }
        this.f10502a.f0();
        this.f10502a.g0();
    }
}
