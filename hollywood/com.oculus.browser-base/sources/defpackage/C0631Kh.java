package defpackage;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.LruCache;
import java.util.ArrayList;

/* renamed from: Kh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0631Kh extends AbstractC2032cb {
    public final LruCache i;
    public final String j;
    public final int k;
    public final Bitmap l;
    public final String m;
    public final float n;

    public C0631Kh(LruCache lruCache, Bitmap bitmap, String str, String str2, int i2, float f) {
        this.i = lruCache;
        this.j = str;
        this.k = i2;
        this.l = bitmap;
        this.m = str2;
        this.n = f;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        if (h()) {
            return null;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap d = AbstractC0752Mh.d(this.l, (float) this.k, false);
        AbstractC3364kK0.k("Android.PhotoPicker.BitmapScalerTask", SystemClock.elapsedRealtime() - elapsedRealtime);
        return d;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        if (!h()) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(bitmap);
            this.i.put(this.j, new HC0(arrayList, this.m, Boolean.FALSE, this.n));
        }
    }
}
