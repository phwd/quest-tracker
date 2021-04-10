package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0nA  reason: invalid class name and case insensitive filesystem */
public final class C03510nA implements AbstractC00840Jw<Bitmap> {
    public static C03510nA A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC00840Jw
    public final void A8y(Bitmap bitmap) {
        bitmap.recycle();
    }
}
