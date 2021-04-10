package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import org.chromium.base.task.PostTask;

/* renamed from: rN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4565rN implements AbstractC4224pN {
    public final int F;

    public C4565rN(KN kn) {
        boolean z = kn.e;
        this.F = Math.min(kn.d, 32);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.AbstractC4224pN
    public void u(C0509Ih ih, FN fn) {
        Bitmap bitmap;
        Bitmap a2 = AbstractC0692Lh.a(ih);
        if (a2 == null) {
            AbstractC1220Ua0.a("FaceDetectionImpl", "Error converting Mojom Bitmap to Android Bitmap", new Object[0]);
            fn.a(new AN[0]);
            return;
        }
        C0570Jh jh = ih.d;
        int i = jh.e;
        int i2 = (i % 2) + i;
        int i3 = jh.f;
        if (i2 != i) {
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(a2, 0.0f, 0.0f, (Paint) null);
            bitmap = createBitmap;
        } else {
            bitmap = a2;
        }
        int[] iArr = new int[(i2 * i3)];
        bitmap.getPixels(iArr, 0, i2, 0, 0, i2, i3);
        PostTask.b(C3070if1.b, new RunnableC4395qN(this, i2, i3, Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.RGB_565), fn), 0);
    }
}
