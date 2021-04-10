package defpackage;

import org.chromium.components.media_router.BrowserMediaRouterDialogController;
import org.chromium.components.media_router.MediaRouteControllerDialogManager$Fragment;

/* renamed from: Mf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0747Mf0 extends AbstractC4949tg {
    public final String f;
    public final AbstractC0750Mg0 g = new C0626Kf0(this);

    public C0747Mf0(String str, C0629Kg0 kg0, String str2, BrowserMediaRouterDialogController browserMediaRouterDialogController) {
        super(str, kg0, browserMediaRouterDialogController);
        this.f = str2;
    }

    @Override // defpackage.AbstractC4949tg
    public OE b(KS ks) {
        if (ks.J("androidx.mediarouter:MediaRouteControllerDialogFragment") != null) {
            return null;
        }
        MediaRouteControllerDialogManager$Fragment mediaRouteControllerDialogManager$Fragment = new MediaRouteControllerDialogManager$Fragment(this, this.g);
        this.c.a(this.b, this.g, 0);
        mediaRouteControllerDialogManager$Fragment.k1(ks, "androidx.mediarouter:MediaRouteControllerDialogFragment");
        ks.D(true);
        ks.L();
        return mediaRouteControllerDialogManager$Fragment;
    }
}
