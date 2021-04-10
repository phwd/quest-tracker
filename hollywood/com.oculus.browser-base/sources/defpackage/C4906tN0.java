package defpackage;

import J.N;
import android.text.TextUtils;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: tN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4906tN0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final GN0 f11340a;

    public C4906tN0(GN0 gn0) {
        this.f11340a = gn0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        GN0 gn0 = this.f11340a;
        String str = (String) obj;
        if (((C1078Rq0) gn0.p0).H != null) {
            String D = SelectionPopupControllerImpl.D(str, 1000);
            if (!TextUtils.isEmpty(D)) {
                Tab tab = gn0.F.W0.H;
                Um1.a(Profile.a(tab.l())).notifyEvent("web_search_performed");
                TemplateUrlService a2 = AbstractC0444Hf1.a();
                String Mweksmrf = N.Mweksmrf(a2.c, a2, D, null);
                String b = GeolocationHeader.b(Mweksmrf, tab);
                LoadUrlParams loadUrlParams = new LoadUrlParams(Mweksmrf, 0);
                loadUrlParams.f = b;
                loadUrlParams.c = 5;
                ((AbstractC0246Ea1) ((AbstractC0124Ca1) ((C1078Rq0) gn0.p0).H)).k.S(tab.a()).b(loadUrlParams, 4, tab);
            }
        }
    }
}
