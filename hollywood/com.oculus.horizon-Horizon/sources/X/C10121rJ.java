package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rJ  reason: invalid class name and case insensitive filesystem */
public final class C10121rJ implements AnonymousClass0Ox {
    public final Set<Bitmap> A00 = Collections.newSetFromMap(new IdentityHashMap());

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1sV
    public final Bitmap get(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        this.A00.add(createBitmap);
        return createBitmap;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1sV, X.AnonymousClass1ou
    public final void A86(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (bitmap2 != null) {
            this.A00.remove(bitmap2);
            bitmap2.recycle();
            return;
        }
        throw null;
    }
}
