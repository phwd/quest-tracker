package defpackage;

import android.view.View;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataCheckBoxPreference;

/* renamed from: Fu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0354Fu extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ClearBrowsingDataCheckBoxPreference f8048a;

    public C0354Fu(ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference) {
        this.f8048a = clearBrowsingDataCheckBoxPreference;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f8048a.k0();
    }
}
