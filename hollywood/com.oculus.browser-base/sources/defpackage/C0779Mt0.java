package defpackage;

import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.ui.base.ViewAndroidDelegate;

/* renamed from: Mt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0779Mt0 extends ViewAndroidDelegate {
    public final /* synthetic */ OverlayPanelContent f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0779Mt0(OverlayPanelContent overlayPanelContent, ViewGroup viewGroup) {
        super(viewGroup);
        this.f = overlayPanelContent;
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void setViewPosition(View view, float f2, float f3, float f4, float f5, int i, int i2) {
        super.setViewPosition(view, f2, f3, f4, f5, i, i2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i3 = marginLayoutParams.topMargin;
        OverlayPanelContent overlayPanelContent = this.f;
        marginLayoutParams.topMargin = overlayPanelContent.u + overlayPanelContent.t + i3;
    }
}
