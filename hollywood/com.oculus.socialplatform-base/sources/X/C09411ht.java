package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(21)
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ht  reason: invalid class name and case insensitive filesystem */
public final class C09411ht extends AbstractC09401hs<Bitmap> implements AnonymousClass0VL {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.1hr] */
    @Override // X.AbstractC09401hs
    @Nullable
    public final Bitmap A05(C09391hr<Bitmap> r3) {
        Bitmap bitmap = (Bitmap) super.A05(r3);
        if (bitmap != null) {
            bitmap.eraseColor(0);
        }
        return bitmap;
    }

    public C09411ht(AnonymousClass0JS r1, AnonymousClass1i0 r2, AnonymousClass1i3 r3) {
        super(r1, r2, r3);
    }
}
