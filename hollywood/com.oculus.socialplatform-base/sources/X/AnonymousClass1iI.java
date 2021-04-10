package X;

import android.graphics.Bitmap;

/* renamed from: X.1iI  reason: invalid class name */
public class AnonymousClass1iI implements AbstractC00840Jw<Bitmap> {
    public final /* synthetic */ AnonymousClass1iK A00;

    public AnonymousClass1iI(AnonymousClass1iK r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC00840Jw
    public final void A8y(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        try {
            AnonymousClass1iK r8 = this.A00;
            synchronized (r8) {
                int A01 = C01110Pz.A01(bitmap2);
                boolean z = false;
                if (r8.A00 > 0) {
                    z = true;
                }
                C00740Ii.A04(z, "No bitmaps registered.");
                long j = (long) A01;
                long j2 = r8.A01;
                boolean z2 = false;
                if (j <= j2) {
                    z2 = true;
                }
                Object[] objArr = {Integer.valueOf(A01), Long.valueOf(j2)};
                if (z2) {
                    r8.A01 = j2 - j;
                    r8.A00--;
                } else {
                    throw new IllegalArgumentException(C00740Ii.A00("Bitmap size bigger than the total registered size: %d, %d", objArr));
                }
            }
        } finally {
            bitmap2.recycle();
        }
    }
}
