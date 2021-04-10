package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;
import org.chromium.url.GURL;

/* renamed from: V60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V60 implements LargeIconBridge$LargeIconCallback {
    public final /* synthetic */ GURL F;
    public final /* synthetic */ LargeIconBridge$LargeIconCallback G;
    public final /* synthetic */ X60 H;

    public V60(X60 x60, GURL gurl, LargeIconBridge$LargeIconCallback largeIconBridge$LargeIconCallback) {
        this.H = x60;
        this.F = gurl;
        this.G = largeIconBridge$LargeIconCallback;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        this.H.c.put(this.F, new W60(bitmap, i, z, i2));
        this.G.onLargeIconAvailable(bitmap, i, z, i2);
    }
}
