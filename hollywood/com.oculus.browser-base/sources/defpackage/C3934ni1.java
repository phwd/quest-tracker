package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: ni1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3934ni1 extends AbstractC2032cb {
    public final /* synthetic */ SX0 i;
    public final /* synthetic */ LargeIconBridge$LargeIconCallback j;
    public final /* synthetic */ C4105oi1 k;

    public C3934ni1(C4105oi1 oi1, SX0 sx0, LargeIconBridge$LargeIconCallback largeIconBridge$LargeIconCallback) {
        this.k = oi1;
        this.i = sx0;
        this.j = largeIconBridge$LargeIconCallback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Bitmap decodeFile = BitmapFactory.decodeFile(this.i.c);
        if (decodeFile == null) {
            String str = this.i.c;
        }
        return decodeFile;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null) {
            C4105oi1 oi1 = this.k;
            oi1.b.a(this.i.b, oi1.f, this.j);
            return;
        }
        this.j.onLargeIconAvailable(bitmap, -16777216, false, 0);
    }
}
