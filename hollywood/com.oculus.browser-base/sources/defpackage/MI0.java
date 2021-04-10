package defpackage;

import org.chromium.chrome.browser.webapps.PwaBottomSheetController;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* renamed from: MI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MI0 extends AbstractC6022zx1 {
    public final /* synthetic */ PwaBottomSheetController G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MI0(PwaBottomSheetController pwaBottomSheetController, WebContents webContents) {
        super(webContents);
        this.G = pwaBottomSheetController;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && navigationHandle.f) {
            PwaBottomSheetController pwaBottomSheetController = this.G;
            ((C5638xj) pwaBottomSheetController.H).p(pwaBottomSheetController.I, true, 0);
        }
    }
}
