package defpackage;

import android.text.format.Formatter;
import com.oculus.browser.R;
import java.util.Collection;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.page_info.PageInfoCookiesPreference;

/* renamed from: gv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2776gv0 implements Cy1 {

    /* renamed from: a  reason: collision with root package name */
    public final C3117iv0 f10032a;

    public C2776gv0(C3117iv0 iv0) {
        this.f10032a = iv0;
    }

    @Override // defpackage.Cy1
    public void a(Collection collection) {
        String str;
        C3117iv0 iv0 = this.f10032a;
        C3469ky1 A1 = SingleWebsiteSettings.A1(C3640ly1.b(C4649rt0.c(iv0.f10173J).toString()), collection);
        iv0.Q = A1;
        PageInfoCookiesPreference pageInfoCookiesPreference = iv0.L;
        if (pageInfoCookiesPreference != null) {
            long j = A1.j();
            ChromeImageViewPreference chromeImageViewPreference = pageInfoCookiesPreference.J0;
            int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            boolean z = false;
            if (i > 0) {
                str = String.format(pageInfoCookiesPreference.x().getString(R.string.f56830_resource_name_obfuscated_RES_2131953000), Formatter.formatShortFileSize(pageInfoCookiesPreference.x(), j));
            } else {
                str = null;
            }
            chromeImageViewPreference.T(str);
            boolean z2 = pageInfoCookiesPreference.N0;
            if (i != 0) {
                z = true;
            }
            pageInfoCookiesPreference.N0 = z2 | z;
            pageInfoCookiesPreference.p1();
        }
    }
}
