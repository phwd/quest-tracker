package defpackage;

import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;
import org.chromium.components.media_router.BrowserMediaRouter;
import org.chromium.components.media_router.BrowserMediaRouterDialogController;
import org.chromium.content_public.browser.WebContents;

/* renamed from: tg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4949tg {

    /* renamed from: a  reason: collision with root package name */
    public final String f11358a;
    public final C0629Kg0 b;
    public final C3246jh0 c = BrowserMediaRouter.a();
    public final BrowserMediaRouterDialogController d;
    public OE e;

    public AbstractC4949tg(String str, C0629Kg0 kg0, BrowserMediaRouterDialogController browserMediaRouterDialogController) {
        this.f11358a = str;
        this.b = kg0;
        this.d = browserMediaRouterDialogController;
    }

    public void a(WebContents webContents) {
        if (this.c == null) {
            this.d.a();
            return;
        }
        KS a2 = ChromeMediaRouterClient.f10694a.a();
        if (a2 == null) {
            this.d.a();
            return;
        }
        OE b2 = b(a2);
        this.e = b2;
        if (b2 == null) {
            this.d.a();
        }
    }

    public abstract OE b(KS ks);
}
