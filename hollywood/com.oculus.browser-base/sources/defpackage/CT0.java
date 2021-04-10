package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: CT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class CT0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final GT0 f7811a;
    public final int b;
    public final Tab c;
    public final boolean d;
    public final boolean e;

    public CT0(GT0 gt0, int i, Tab tab, boolean z, boolean z2) {
        this.f7811a = gt0;
        this.b = i;
        this.c = tab;
        this.d = z;
        this.e = z2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        GT0 gt0 = this.f7811a;
        int i = this.b;
        Tab tab = this.c;
        boolean z = this.d;
        boolean z2 = this.e;
        C2189dU0 du0 = (C2189dU0) obj;
        Objects.requireNonNull(gt0);
        if (du0 != null) {
            gt0.b(du0, new C1915bt(false, false, true, null, false, false, null), i);
            return;
        }
        WindowAndroid i2 = tab.i();
        WebContents l = tab.l();
        boolean z3 = false;
        if (l != null && l.N() != null && !TextUtils.isEmpty(tab.s()) && !tab.p() && !C3372kO0.W(tab)) {
            z3 = true;
        }
        if (z3) {
            WebContents l2 = tab.l();
            String title = tab.getTitle();
            l2.N().j(new ET0(gt0, tab.s(), i2, l2, title, i, z, z2));
            return;
        }
        gt0.e(i2, tab.l(), tab.getTitle(), tab.s(), null, i, z, z2);
    }
}
