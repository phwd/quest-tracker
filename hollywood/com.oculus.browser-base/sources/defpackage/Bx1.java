package defpackage;

import org.chromium.content.browser.framehost.RenderFrameHostDelegate;
import org.chromium.content.browser.framehost.RenderFrameHostImpl;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Bx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Bx1 {
    public static WebContents a(RenderFrameHost renderFrameHost) {
        RenderFrameHostDelegate renderFrameHostDelegate = ((RenderFrameHostImpl) renderFrameHost).b;
        if (renderFrameHostDelegate == null || !(renderFrameHostDelegate instanceof WebContents)) {
            return null;
        }
        return (WebContents) renderFrameHostDelegate;
    }
}
