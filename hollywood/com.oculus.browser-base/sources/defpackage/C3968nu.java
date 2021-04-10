package defpackage;

import android.app.Activity;
import android.view.View;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: nu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3968nu extends C2971i3 {
    public final C1595a3 f0;
    public final Q31 g0;
    public final Q31 h0;

    public C3968nu(Activity activity, C1595a3 a3Var, Q31 q31, Q31 q312) {
        super(activity);
        this.f0 = a3Var;
        this.g0 = q31;
        this.h0 = q312;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public void D0(String str) {
        Tab tab = this.f0.H;
        if (tab != null) {
            SimpleConfirmInfoBarBuilder.a(tab.l(), null, 60, null, 0, str, null, null, null, false);
        } else {
            C1184Ti1.b(ContextUtils.getApplicationContext(), str, 0).b.show();
        }
    }

    @Override // defpackage.C2971i3
    public L2 J0() {
        return new C0653Kr(s0());
    }

    @Override // defpackage.C2971i3, org.chromium.ui.base.WindowAndroid
    public C3493l60 u0() {
        return (L2) this.G;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public C2746gl0 v0() {
        return (C2746gl0) this.h0.get();
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public View w0() {
        C0663Kw kw;
        if (this.g0.get() == null || (kw = ((SurfaceHolder$Callback2C0723Lw) ((CompositorViewHolder) this.g0.get()).M.G).H) == null) {
            return null;
        }
        return kw.f8395a;
    }
}
