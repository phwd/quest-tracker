package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1sJ  reason: invalid class name */
public final class AnonymousClass1sJ extends AnonymousClass1sL<Bitmap> {
    public static final boolean A00(@Nullable Bitmap bitmap) {
        Object[] objArr;
        String str;
        if (bitmap != null) {
            if (bitmap.isRecycled()) {
                objArr = new Object[]{bitmap};
                str = "Cannot reuse a recycled bitmap: %s";
            } else if (bitmap.isMutable()) {
                return true;
            } else {
                objArr = new Object[]{bitmap};
                str = "Cannot reuse an immutable bitmap: %s";
            }
            C01080Kb.A06("BitmapPoolBackend", str, objArr);
        }
        return false;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1sL
    public final void A03(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (A00(bitmap2)) {
            super.A03(bitmap2);
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1sL
    @Nullable
    public final Bitmap A02(int i) {
        Bitmap bitmap = (Bitmap) super.A02(i);
        if (bitmap == null || !A00(bitmap)) {
            return null;
        }
        bitmap.eraseColor(0);
        return bitmap;
    }
}
