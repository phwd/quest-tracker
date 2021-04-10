package defpackage;

import android.view.View;
import org.chromium.components.page_info.PageInfoCookiesPreference;

/* renamed from: jv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3288jv0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4143ov0 f10248a;

    public C3288jv0(C4143ov0 ov0) {
        this.f10248a = ov0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4143ov0 ov0 = this.f10248a;
        View view = (View) obj;
        int i = PageInfoCookiesPreference.H0;
        ov0.d.run();
    }
}
