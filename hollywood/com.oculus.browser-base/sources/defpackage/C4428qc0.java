package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: qc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4428qc0 extends AbstractC0855Oa1 {
    public final /* synthetic */ View$OnLayoutChangeListenerC4598rc0 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4428qc0(View$OnLayoutChangeListenerC4598rc0 rc0, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = rc0;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        View$OnLayoutChangeListenerC4598rc0 rc0 = this.c;
        Objects.requireNonNull(rc0);
        if (tab != null && rc0.K.add(tab)) {
            tab.A(rc0.R);
        }
        this.c.g0();
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        this.c.K.remove(tab);
        tab.I(this.c.R);
        C0007Ac0 ac0 = this.c.f11207J;
        Objects.requireNonNull(ac0);
        WebContents l = tab.l();
        if (l != null) {
            ac0.a(l).a();
            ac0.f7681a.remove(l);
        }
    }
}
