package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: GM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GM extends AbstractC0095Bm0 {

    /* renamed from: a  reason: collision with root package name */
    public final Q31 f8087a;

    public GM(Q31 q31) {
        this.f8087a = q31;
    }

    @Override // defpackage.AbstractC0095Bm0
    public Tab a(int i, LoadUrlParams loadUrlParams) {
        EM0.g(loadUrlParams.f10938a, 6, Boolean.valueOf(i == 8), (Tab) this.f8087a.get());
        return null;
    }
}
