package defpackage;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* renamed from: Lh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0692Lh {
    public static Bitmap a(C0509Ih ih) {
        C4782sh shVar;
        C4442qh qhVar = ih.e;
        if (qhVar == null) {
            return null;
        }
        C0570Jh jh = ih.d;
        int i = jh.e;
        int i2 = jh.f;
        long j = ((long) i) * ((long) i2);
        if (i <= 0 || i2 <= 0 || j > 2305843009213693951L) {
            return null;
        }
        if (qhVar.f11638a == 0) {
            shVar = new C4782sh(null, ByteBuffer.wrap(qhVar.b));
        } else {
            C4612rh rhVar = qhVar.c;
            shVar = new C4782sh(rhVar.d, rhVar.d.B(0, (long) rhVar.e, FU0.c));
        }
        try {
            ByteBuffer byteBuffer = shVar.G;
            if (byteBuffer.capacity() <= 0) {
                shVar.close();
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(byteBuffer);
            shVar.close();
            return createBitmap;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static GT b(C0509Ih ih) {
        Bitmap a2 = a(ih);
        if (a2 == null) {
            return null;
        }
        GT gt = new GT(null);
        int width = a2.getWidth();
        int height = a2.getHeight();
        gt.c = a2;
        FT ft = gt.f8091a;
        ft.f8017a = width;
        ft.b = height;
        if (gt.b != null || a2 != null) {
            return gt;
        }
        throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
    }
}
