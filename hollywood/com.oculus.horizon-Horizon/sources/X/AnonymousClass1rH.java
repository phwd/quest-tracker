package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(21)
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rH  reason: invalid class name */
public final class AnonymousClass1rH extends AnonymousClass1rI<Bitmap> implements AnonymousClass0Ox {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.1rM] */
    @Override // X.AnonymousClass1rI
    @Nullable
    public final Bitmap A05(C10151rM<Bitmap> r3) {
        Bitmap bitmap = (Bitmap) super.A05(r3);
        if (bitmap != null) {
            bitmap.eraseColor(0);
        }
        return bitmap;
    }

    public AnonymousClass1rH(AbstractC10671uj r1, C10471su r2, AbstractC10691uo r3) {
        super(r1, r2, r3);
    }
}
