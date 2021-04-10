package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* renamed from: cQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2010cQ0 extends C2971i3 {
    public final /* synthetic */ SearchActivity f0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2010cQ0(SearchActivity searchActivity, Context context) {
        super(context);
        this.f0 = searchActivity;
    }

    @Override // defpackage.C2971i3
    public L2 J0() {
        return new JX0(s0());
    }

    @Override // defpackage.C2971i3, org.chromium.ui.base.WindowAndroid
    public C3493l60 u0() {
        return (L2) this.G;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public C2746gl0 v0() {
        return this.f0.l0();
    }
}
