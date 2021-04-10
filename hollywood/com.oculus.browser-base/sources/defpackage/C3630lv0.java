package defpackage;

import androidx.preference.Preference;
import org.chromium.components.page_info.PageInfoCookiesPreference;

/* renamed from: lv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3630lv0 implements YE0 {
    public final PageInfoCookiesPreference F;

    public C3630lv0(PageInfoCookiesPreference pageInfoCookiesPreference) {
        this.F = pageInfoCookiesPreference;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.k1();
    }
}
