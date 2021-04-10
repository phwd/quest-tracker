package defpackage;

import android.content.Context;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Ds  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class AbstractC0228Ds implements DA0 {
    public Context a(RenderFrameHost renderFrameHost) {
        WindowAndroid windowAndroid;
        WebContents a2 = FA0.a(renderFrameHost);
        if (a2 == null) {
            windowAndroid = null;
        } else {
            windowAndroid = a2.I();
        }
        if (windowAndroid == null) {
            return null;
        }
        return (Context) windowAndroid.f11022J.get();
    }
}
