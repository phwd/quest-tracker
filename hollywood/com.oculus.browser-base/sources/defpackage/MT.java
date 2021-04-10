package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: MT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MT extends Y2 {
    public final /* synthetic */ ST d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MT(ST st, C1595a3 a3Var) {
        super(a3Var);
        this.d = st;
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        ST st = this.d;
        st.U = tab;
        st.j(tab != null ? tab.u() : null);
        if (tab != null) {
            ST st2 = this.d;
            st2.l(!st2.a());
        }
    }
}
