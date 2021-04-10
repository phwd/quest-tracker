package org.chromium.chrome.browser.contextmenu;

import J.N;
import org.chromium.base.Callback;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextMenuNativeDelegateImpl {

    /* renamed from: a  reason: collision with root package name */
    public final RenderFrameHost f10639a;
    public long b;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ImageCallbackResult {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f10640a;
        public String b;

        public ImageCallbackResult(byte[] bArr, String str) {
            this.f10640a = bArr;
            this.b = str;
        }
    }

    public ContextMenuNativeDelegateImpl(WebContents webContents, RenderFrameHost renderFrameHost, ContextMenuParams contextMenuParams) {
        this.f10639a = renderFrameHost;
        this.b = N.Mz9Ykykf(webContents, contextMenuParams);
    }

    public static ImageCallbackResult createImageCallbackResult(byte[] bArr, String str) {
        return new ImageCallbackResult(bArr, str);
    }

    public void a(int i, Callback callback) {
        long j = this.b;
        if (j != 0) {
            N.M4wUt4Cl(j, this, this.f10639a, new C2958hz(callback), 2048, 2048, i);
        }
    }

    public void b(boolean z) {
        long j = this.b;
        if (j != 0) {
            N.MyvrkkwD(j, this, z);
        }
    }
}
