package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1hx  reason: invalid class name and case insensitive filesystem */
public final class C09431hx implements AnonymousClass0VL {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC04990rf
    public final Bitmap get(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC04990rf, X.AbstractC00840Jw
    public final void A8y(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            return;
        }
        throw null;
    }
}
