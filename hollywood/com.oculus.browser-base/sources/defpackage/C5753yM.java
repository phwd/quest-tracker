package defpackage;

import org.chromium.chrome.browser.explore_sites.ExploreSitesBackgroundTask;

/* renamed from: yM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5753yM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ExploreSitesBackgroundTask f11675a;

    public C5753yM(ExploreSitesBackgroundTask exploreSitesBackgroundTask) {
        this.f11675a = exploreSitesBackgroundTask;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Boolean bool = (Boolean) obj;
        this.f11675a.k();
    }
}
