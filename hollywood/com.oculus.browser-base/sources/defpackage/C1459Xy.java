package defpackage;

import J.N;
import android.content.Context;
import android.view.ViewStructure;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Xy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1459Xy extends AbstractViewGroup$OnHierarchyChangeListenerC1520Yy {
    public C1459Xy(Context context, WL wl, WebContents webContents) {
        super(context, wl, webContents);
    }

    public void onProvideVirtualStructure(ViewStructure viewStructure) {
        AbstractC3808mx1 d = d();
        if (d != null) {
            WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) d;
            if (webContentsAccessibilityImpl.G.a()) {
                viewStructure.setChildCount(0);
                return;
            }
            viewStructure.setChildCount(1);
            ViewStructure asyncNewChild = viewStructure.asyncNewChild(0);
            WebContentsImpl webContentsImpl = webContentsAccessibilityImpl.G;
            C4321px1 px1 = new C4321px1(webContentsAccessibilityImpl, asyncNewChild, false);
            webContentsImpl.r0();
            N.M16eLpU9(webContentsImpl.H, webContentsImpl, px1);
        }
    }
}
