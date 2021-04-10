package defpackage;

import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* renamed from: FA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class FA0 {
    public static WebContents a(RenderFrameHost renderFrameHost) {
        WebContents a2 = Bx1.a(renderFrameHost);
        if (a2 == null || a2.g()) {
            return null;
        }
        return a2;
    }
}
