package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: LT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LT extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ST f8418a;

    public LT(ST st) {
        this.f8418a = st;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tab tab;
        VT vt;
        ST st = this.f8418a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Objects.requireNonNull(st);
        if (booleanValue && (tab = st.U) != null && (vt = st.R) != null) {
            st.i(tab, vt);
            st.R = null;
        }
    }
}
