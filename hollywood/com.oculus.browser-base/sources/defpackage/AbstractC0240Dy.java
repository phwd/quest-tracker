package defpackage;

import J.N;
import org.chromium.components.content_capture.ContentCaptureData;
import org.chromium.components.content_capture.ContentCaptureReceiverManager;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Dy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0240Dy {

    /* renamed from: a  reason: collision with root package name */
    public ContentCaptureReceiverManager f7927a;

    public AbstractC0240Dy(WebContents webContents) {
        e(webContents);
    }

    public abstract void a(HT ht, ContentCaptureData contentCaptureData);

    public abstract void b(HT ht, long[] jArr);

    public abstract void c(HT ht, ContentCaptureData contentCaptureData);

    public abstract void d(HT ht);

    public void e(WebContents webContents) {
        if (N.MRrWU$ia()) {
            if (webContents != null) {
                ContentCaptureReceiverManager contentCaptureReceiverManager = (ContentCaptureReceiverManager) N.MxegY8Dy(webContents);
                this.f7927a = contentCaptureReceiverManager;
                contentCaptureReceiverManager.b.add(this);
                return;
            }
            this.f7927a = null;
        }
    }

    public boolean f(String[] strArr) {
        return true;
    }
}
