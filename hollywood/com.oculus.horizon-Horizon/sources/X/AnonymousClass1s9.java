package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1s9  reason: invalid class name */
public final class AnonymousClass1s9 implements AnonymousClass0Ox {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1sV
    public final Bitmap get(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1sV, X.AnonymousClass1ou
    public final void A86(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            return;
        }
        throw null;
    }
}
