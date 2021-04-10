package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* renamed from: FQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class FQ0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f8015a;

    public FQ0(Tab tab) {
        this.f8015a = tab;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f8015a.c(new LoadUrlParams(((GURL) obj).h(), 0));
    }
}
