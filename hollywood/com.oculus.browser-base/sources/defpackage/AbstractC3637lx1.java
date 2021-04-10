package defpackage;

import android.util.SparseArray;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: lx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class AbstractC3637lx1 implements AbstractC3808mx1 {
    public static AbstractC3808mx1 a(WebContents webContents) {
        SparseArray sparseArray = WebContentsAccessibilityImpl.F;
        return (WebContentsAccessibilityImpl) ((WebContentsImpl) webContents).v0(WebContentsAccessibilityImpl.class, AbstractC4662rx1.f11237a);
    }
}
