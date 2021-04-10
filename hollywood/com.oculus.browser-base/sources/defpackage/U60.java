package defpackage;

import android.graphics.Bitmap;
import android.util.LruCache;
import org.chromium.url.GURL;

/* renamed from: U60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U60 extends LruCache {
    public U60(X60 x60, int i) {
        super(i);
    }

    @Override // android.util.LruCache
    public int sizeOf(Object obj, Object obj2) {
        GURL gurl = (GURL) obj;
        Bitmap bitmap = ((W60) obj2).f9129a;
        return Math.max(1024, bitmap == null ? 0 : bitmap.getByteCount());
    }
}
