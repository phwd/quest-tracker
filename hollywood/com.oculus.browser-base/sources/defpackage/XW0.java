package defpackage;

import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* renamed from: XW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XW0 implements QQ0 {

    /* renamed from: a  reason: collision with root package name */
    public final SingleCategorySettings f9212a;

    public XW0(SingleCategorySettings singleCategorySettings) {
        this.f9212a = singleCategorySettings;
    }

    @Override // defpackage.QQ0
    public void onQueryTextChange(String str) {
        SingleCategorySettings singleCategorySettings = this.f9212a;
        String str2 = singleCategorySettings.K0;
        boolean z = true;
        if (str2 != null ? str2.equals(str) : str == null || str.isEmpty()) {
            z = false;
        }
        singleCategorySettings.K0 = str;
        if (z) {
            singleCategorySettings.n1();
        }
    }
}
