package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1p5  reason: invalid class name */
public final class AnonymousClass1p5 implements AnonymousClass1ou<Bitmap> {
    public static AnonymousClass1p5 A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1ou
    public final void A86(Bitmap bitmap) {
        bitmap.recycle();
    }
}
