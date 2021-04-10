package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Rv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Rv1 extends AbstractC6022zx1 {
    public Rv1(Sv1 sv1, WebContents webContents) {
        super(webContents);
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        RenderFrameHost N;
        if (navigationHandle.f && navigationHandle.f10940a && !navigationHandle.g) {
            GURL gurl = navigationHandle.e;
            WebContents webContents = (WebContents) this.F.get();
            if (!(webContents == null || (N = webContents.N()) == null)) {
                TemplateUrlService a2 = AbstractC0444Hf1.a();
                Objects.requireNonNull(a2);
                Object obj = ThreadUtils.f10596a;
                if (N.MF3JCGn0(a2.c, a2, gurl)) {
                    N.e();
                }
            }
        }
        destroy();
    }
}
