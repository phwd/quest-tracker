package defpackage;

import J.N;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: GO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class GO implements Runnable {
    public final FeedStreamSurface F;

    public GO(FeedStreamSurface feedStreamSurface) {
        this.F = feedStreamSurface;
    }

    public void run() {
        FeedStreamSurface feedStreamSurface = this.F;
        N.MRA3zmUr(feedStreamSurface.d, feedStreamSurface, new HO(feedStreamSurface));
    }
}
