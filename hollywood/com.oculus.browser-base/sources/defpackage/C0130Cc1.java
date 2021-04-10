package defpackage;

import com.oculus.browser.R;
import org.chromium.base.Callback;

/* renamed from: Cc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0130Cc1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f7823a;

    public C0130Cc1(Callback callback) {
        this.f7823a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f7823a;
        Integer num = (Integer) obj;
        int intValue = num.intValue();
        if (intValue == R.id.close_tab) {
            AbstractC3535lK0.a("MobileMenuCloseTab.LongTapMenu");
        } else if (intValue == R.id.new_tab_menu_id) {
            AbstractC3535lK0.a("MobileMenuNewTab.LongTapMenu");
        } else if (intValue == R.id.new_incognito_tab_menu_id) {
            AbstractC3535lK0.a("MobileMenuNewIncognitoTab.LongTapMenu");
        }
        callback.onResult(num);
    }
}
