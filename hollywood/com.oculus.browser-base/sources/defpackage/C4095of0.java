package defpackage;

import org.chromium.components.media_router.BrowserMediaRouterDialogController;
import org.chromium.components.media_router.MediaRouteChooserDialogManager$Fragment;

/* renamed from: of0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4095of0 extends AbstractC4949tg {
    public C4095of0(String str, C0629Kg0 kg0, BrowserMediaRouterDialogController browserMediaRouterDialogController) {
        super(str, kg0, browserMediaRouterDialogController);
    }

    @Override // defpackage.AbstractC4949tg
    public OE b(KS ks) {
        if (ks.J("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
            return null;
        }
        MediaRouteChooserDialogManager$Fragment mediaRouteChooserDialogManager$Fragment = new MediaRouteChooserDialogManager$Fragment(this);
        mediaRouteChooserDialogManager$Fragment.n1(this.b);
        mediaRouteChooserDialogManager$Fragment.k1(ks, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
        ks.D(true);
        ks.L();
        return mediaRouteChooserDialogManager$Fragment;
    }
}
