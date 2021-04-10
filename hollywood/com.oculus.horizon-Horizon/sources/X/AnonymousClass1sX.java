package X;

import android.graphics.Bitmap;

/* renamed from: X.1sX  reason: invalid class name */
public class AnonymousClass1sX implements AnonymousClass1tZ<AnonymousClass1q1> {
    public final /* synthetic */ AnonymousClass1rV A00;

    public AnonymousClass1sX(AnonymousClass1rV r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1tZ
    public final int A4N(AnonymousClass1q1 r2) {
        Bitmap bitmap = ((AnonymousClass1qH) r2).A04;
        if (bitmap == null) {
            return 0;
        }
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bitmap.getByteCount();
        }
    }
}
