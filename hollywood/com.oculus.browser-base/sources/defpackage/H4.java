package defpackage;

import org.chromium.components.browser_ui.site_settings.AllSiteSettings;

/* renamed from: H4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class H4 implements QQ0 {

    /* renamed from: a  reason: collision with root package name */
    public final AllSiteSettings f8133a;

    public H4(AllSiteSettings allSiteSettings) {
        this.f8133a = allSiteSettings;
    }

    @Override // defpackage.QQ0
    public void onQueryTextChange(String str) {
        AllSiteSettings allSiteSettings = this.f8133a;
        String str2 = allSiteSettings.M0;
        boolean z = true;
        if (str2 != null ? str2.equals(str) : str == null || str.isEmpty()) {
            z = false;
        }
        allSiteSettings.M0 = str;
        if (z) {
            allSiteSettings.k1();
        }
    }
}
