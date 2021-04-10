package defpackage;

import J.N;
import android.util.LruCache;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;
import org.chromium.url.GURL;

/* renamed from: X60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X60 {

    /* renamed from: a  reason: collision with root package name */
    public final BrowserContextHandle f9194a;
    public long b = N.MwrhffLX();
    public LruCache c;

    public X60(BrowserContextHandle browserContextHandle) {
        this.f9194a = browserContextHandle;
    }

    public void a() {
        long j = this.b;
        if (j != 0) {
            N.MthPKzcb(j);
            this.b = 0;
        }
    }

    public boolean b(GURL gurl, int i, LargeIconBridge$LargeIconCallback largeIconBridge$LargeIconCallback) {
        LruCache lruCache = this.c;
        if (lruCache == null) {
            return N.M1pABITV(this.b, this.f9194a, gurl, i, largeIconBridge$LargeIconCallback);
        }
        W60 w60 = (W60) lruCache.get(gurl);
        if (w60 != null) {
            largeIconBridge$LargeIconCallback.onLargeIconAvailable(w60.f9129a, w60.b, w60.c, w60.d);
            return true;
        }
        return N.M1pABITV(this.b, this.f9194a, gurl, i, new V60(this, gurl, largeIconBridge$LargeIconCallback));
    }
}
